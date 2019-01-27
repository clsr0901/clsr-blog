package com.clsr0901.blog.service;

import com.clsr0901.blog.appEnum.ExceptionEnum;
import com.clsr0901.blog.entity.Message;
import com.clsr0901.blog.entity.Result;
import com.clsr0901.blog.entity.User;
import com.clsr0901.blog.exception.BException;
import com.clsr0901.blog.mapper.MessageMapper;
import com.clsr0901.blog.mapper.UserMapper;
import com.clsr0901.blog.util.ResultUtil;
import com.clsr0901.blog.vo.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MessageMapper messageMapper;

    public Result put(Message message) {
        if (userMapper.findById(message.getSourceUserId()) == null || userMapper.findById(message.getDestUserId()) == null)
            throw new BException(ExceptionEnum.USER_NOT_EXITS);
        messageMapper.insert(message);
        return ResultUtil.success(messageMapper.findById(message.getId()));
    }

    public Result delete(int messageId) {
        messageMapper.deleteById(messageId);
        return ResultUtil.success();
    }

    public Result<List<MessageVO>> findAllByDestUserId(int destUserId) {
        if (userMapper.findById(destUserId) == null)
            throw new BException(ExceptionEnum.USER_NOT_EXITS);
        return ResultUtil.success(messageMapper.findAllByDestUserId(destUserId));
    }
}
