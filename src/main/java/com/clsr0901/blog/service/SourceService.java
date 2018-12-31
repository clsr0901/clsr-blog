package com.clsr0901.blog.service;

import com.clsr0901.blog.appEnum.ExceptionEnum;
import com.clsr0901.blog.entity.Result;
import com.clsr0901.blog.entity.Source;
import com.clsr0901.blog.exception.BException;
import com.clsr0901.blog.mapper.SourceMapper;
import com.clsr0901.blog.mapper.UserMapper;
import com.clsr0901.blog.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class SourceService {

    @Value("${upload-path}")
    private String filePath;

    @Autowired
    private SourceMapper sourceMapper;
    @Autowired
    private UserMapper userMapper;

    public Result<List<Source>> get(int userId) {
        if (userMapper.findById(userId) == null)
            throw new BException(ExceptionEnum.USER_NOT_EXITS);
        return ResultUtil.success(sourceMapper.findByUserId(userId));
    }

    public Result deleteByUid(String uid) {
        Source source = sourceMapper.findByMd5(uid);
        if (source != null) {
            sourceMapper.deleteByMd5(uid);
            File file = new File(filePath + "/merge", source.getName());
            if (file.exists())
                file.delete();
        }
        return ResultUtil.success();
    }
}
