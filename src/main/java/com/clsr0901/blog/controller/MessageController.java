package com.clsr0901.blog.controller;

import com.clsr0901.blog.entity.Message;
import com.clsr0901.blog.entity.Result;
import com.clsr0901.blog.service.MessageService;
import com.clsr0901.blog.vo.MessageVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
@Slf4j
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PutMapping("/put")
    @ApiOperation(value = "新增留言", notes = "新增留言接口 /message/put", tags = "MessageController", httpMethod = "PUT")
    public Result<MessageVO> put(@RequestBody @ApiParam(name = "Message对象", value = "传入json格式", required = true)Message message){
        log.info("新增留言 message={}", message);
        return messageService.put(message);
    }

    @DeleteMapping("/delete/{messageId}")
    @ApiOperation(value = "根据id删除我的留言", notes = "根据id删除我的留言接口 /message/delete/{messageId}", tags = "MessageController", httpMethod = "DELETE")
    public Result delete(@PathVariable("messageId") @ApiParam(name = "id", value = "传入int格式", required = true)int messageId){
        log.info("根据id删除我的留言 messageId={}", messageId);
        return messageService.delete(messageId);
    }


    @GetMapping("/get/{destUserId}")
    @ApiOperation(value = "查询我的留言", notes = "查询我的留言接口 /message/get/{destUserId}", tags = "MessageController", httpMethod = "GET")
    public Result<List<MessageVO>> findAllByDestUserId(@PathVariable("destUserId") @ApiParam(name = "目标人id", value = "传入int格式", required = true)int destUserId){
        log.info("查询我的留言 destUserId={}", destUserId);
        return messageService.findAllByDestUserId(destUserId);
    }
}
