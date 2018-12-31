package com.clsr0901.blog.controller;

import com.clsr0901.blog.entity.Result;
import com.clsr0901.blog.entity.Source;
import com.clsr0901.blog.service.UploadFileService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/upload")
@Slf4j
public class UploadFileController {

    @Autowired
    private UploadFileService service;

    /**
     * 分片上传文件
     *
     * @param request
     * @param response
     * @param md5
     * @param chunk
     * @param file
     * @param chunks
     */
    @RequestMapping("/chunk")
    @ApiOperation(value = "分片上传文件", notes = "分片上传文件接口/upload/chunk", tags = "UploadFileController", httpMethod = "POST")
    public Result<Integer> chunk(HttpServletRequest request, HttpServletResponse response,
                                 @ApiParam(name = "md5", value = "String", required = true) String md5,
                                 @ApiParam(name = "ext", value = "String", required = true) String ext,
                                 @ApiParam(name = "分片", value = "Integer", required = true) Integer chunk,
                                 @ApiParam(name = "上传文件", value = "MultipartFile对象", required = true) MultipartFile file,
                                 @ApiParam(name = "分片总数", value = "Integer", required = true) Integer chunks) {
        log.info("分片上传文件 md5={}, chunk={}, chunks={}, file={}", md5, chunk, chunks, file);
        return service.chunk(request, response, md5, ext, chunk, file, chunks);
    }

    /**
     * 合并文件
     *
     * @param md5
     * @param fileName
     * @throws Exception
     */
    @RequestMapping("/merge")
    @ResponseBody
    @ApiOperation(value = "合并文件", notes = "合并文件接口/upload/merge", tags = "UploadFileController", httpMethod = "POST")
    public Result<Source> mergeFile(@ApiParam(name = "md5", value = "String", required = true) String md5,
                                    @ApiParam(name = "ext", value = "String", required = true) String ext,
                                    @ApiParam(name = "文件名", value = "String", required = true) String fileName) {
        log.info("合并文件 md5={}, filename={}, ext={}", md5, fileName, ext);
        return service.mergeFile(md5, fileName, ext);
    }


    @PostMapping("/file")
    @ApiOperation(value = "单个文件上传", notes = "单个文件上传接口/upload/file", tags = "UploadFileController", httpMethod = "POST")
    public Result upload(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam("file") @ApiParam(name = "file", value = "MultipartFile对象", required = true) MultipartFile file,
                                   @RequestParam("md5") @ApiParam(name = "md5", value = "String", required = true) String md5) throws IOException {
        log.info("单个文件上传 file={}, md5={}", file, md5);
        return service.file(response, file, md5);
    }
}
