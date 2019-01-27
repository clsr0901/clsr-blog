package com.clsr0901.blog.service;

import com.clsr0901.blog.appEnum.ExceptionEnum;
import com.clsr0901.blog.entity.Result;
import com.clsr0901.blog.entity.Source;
import com.clsr0901.blog.entity.User;
import com.clsr0901.blog.exception.BException;
import com.clsr0901.blog.mapper.SourceMapper;
import com.clsr0901.blog.mapper.UserMapper;
import com.clsr0901.blog.util.IPUtil;
import com.clsr0901.blog.util.ResultUtil;
import com.clsr0901.blog.util.UploadUtil;
import com.clsr0901.blog.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
public class UploadFileService {
    @Value("${upload-path}")
    private String filePath;

    @Value("${blog-env}")
    private String blogEnv;

    @Value("${port}")
    private String port;

    @Autowired
    private SourceMapper sourceMapper;
    @Autowired
    private UserMapper userMapper;

    public Result<Integer> chunk(HttpServletRequest request, HttpServletResponse response, String md5, Integer chunk,
                                 MultipartFile file, Integer chunks) {
        if (file == null || md5 == null || chunk == null || chunks == null) {
            throw new BException(ExceptionEnum.USER_NOT_EXITS);
        }
        Source source = sourceMapper.findByMd5(md5);
        if (source != null) {
            return ResultUtil.success(chunks);
        }
        File parentFileDir = null;
        try {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (isMultipart) {
                // 临时目录用来存放所有分片文件
                String tempFileDir = filePath + md5;
                parentFileDir = new File(tempFileDir);
                if (!parentFileDir.exists()) {
                    parentFileDir.mkdirs();
                } else {
                    if (parentFileDir.listFiles().length > chunk) {
                        return ResultUtil.success(parentFileDir.listFiles().length);
                    }
                }
                // 分片处理时，前台会多次调用上传接口，每次都会上传文件的一部分到后台
                File tempPartFile = new File(parentFileDir, md5 + "_" + chunk + ".part");
                FileUtils.copyInputStreamToFile(file.getInputStream(), tempPartFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BException(ExceptionEnum.UPLOAD_ERROR);
        }
        if (parentFileDir == null || !parentFileDir.isDirectory()) {
            throw new BException(ExceptionEnum.UPLOAD_CREATE_DIRECTORY_ERROR);
        }
        return ResultUtil.success(parentFileDir.listFiles().length);
    }

    public Result<Source> mergeFile(String md5, String fileName, String ext) {
        Source source = sourceMapper.findByMd5(md5);
        if (source != null) {
            return ResultUtil.success(source);
        }
        // 得到 destTempFile 就是最终的文件
        try {
            File parentFileDir = new File(filePath + md5);
            if (parentFileDir.isDirectory()) {
                File destTempFile = new File(filePath + "/merge", fileName);
                if (!destTempFile.exists()) {
                    //先得到文件的上级目录，并创建上级目录，在创建文件,
                    destTempFile.getParentFile().mkdir();
                    try {
                        //创建文件
                        destTempFile.createNewFile(); //上级目录没有创建，这里会报错
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new BException(ExceptionEnum.UPLOAD_CREATE_DIRECTORY_ERROR);
                    }
                }
                for (int i = 0; i < parentFileDir.listFiles().length; i++) {
                    File partFile = new File(parentFileDir, md5 + "_" + i + ".part");
                    FileOutputStream destTempfos = new FileOutputStream(destTempFile, true);
                    //遍历"所有分片文件"到"最终文件"中
                    FileUtils.copyFile(partFile, destTempfos);
                    destTempfos.close();
                }
                // 删除临时目录中的分片文件
                FileUtils.deleteDirectory(parentFileDir);
                log.info("destTempfos={}", destTempFile);
                String mime = getContentType(destTempFile.getAbsolutePath());
                if (blogEnv.equals("windows")) {
                    /**
                     * Windows下的文件保存
                     */
                    sourceMapper.insert(new Source(UserUtil.getCurrentUser(userMapper).getId(), fileName, md5, "http://localhost:8080/source/merge/" + fileName,
                            destTempFile.length(), mime, getType(mime)));
                } else {
                    /**
                     * linu下的文件保存
                     */
                    sourceMapper.insert(new Source(UserUtil.getCurrentUser(userMapper).getId(), fileName, md5,
                            "http://132.232.92.140:" + port +
                                    "/source/merge/" + fileName,
                            destTempFile.length(), mime, getType(mime)));
                }
                return ResultUtil.success(sourceMapper.findByMd5(md5));
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("上传文件失败 e={}", e);
            throw new BException(ExceptionEnum.UPLOAD_ERROR);
        }
        return null;
    }

    public String getContentType(String filename) {
        String type = null;
        Path path = Paths.get(filename);
        try {
            type = Files.probeContentType(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return type;
    }

    //上传附件
    public Result file(HttpServletResponse response, MultipartFile file, String md5) throws IOException {
        if (file == null || StringUtils.isEmpty(md5) || md5.equals("undefined"))
            response.sendError(417, "参数不合法");
        Source attachment = sourceMapper.findByMd5(md5);
        if (attachment != null)
            return ResultUtil.success(attachment.getName());
        String fileName = file.getOriginalFilename();
        String orginalName = fileName.substring(0, fileName.lastIndexOf("."));
        String ext = fileName.substring(fileName.lastIndexOf("."));
        File destFile = null;
        try {
            destFile = UploadUtil.uploadFile(file.getBytes(), filePath,
                    orginalName + "_" + md5 + ext);
        } catch (Exception e) {
            throw new BException(ExceptionEnum.UPLOAD_CREATE_DIRECTORY_ERROR);
        }
        if (destFile == null)
            throw new BException(ExceptionEnum.UPLOAD_SAVE_FILE_ERROR);
//        if (!guid.toLowerCase().equals(MD5Util.md5File(destFile.getAbsolutePath())))
//            response.sendError(503, "MD5校验失败");
        log.info("上传附件 destFile={}", destFile);
        sourceMapper.insert(new Source(UserUtil.getCurrentUser(userMapper).getId(), destFile.getName(), md5, destFile.getPath(),
                destFile.length(), file.getContentType(), getType(file.getContentType())));
        return ResultUtil.success(destFile.getName());
    }

    private int getType(String ext) {
        if (ext.contains("text") || ext.contains("txt")) {
            return 0;
        } else if (ext.contains("image")) {
            return 1;
        } else if (ext.contains("audio")) {
            return 2;
        } else if (ext.contains("video")) {
            return 3;
        } else {
            return 4;
        }
    }
}
