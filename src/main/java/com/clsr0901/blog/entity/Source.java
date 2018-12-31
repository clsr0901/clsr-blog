package com.clsr0901.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Source {
    /**
     * 文件ID
     */
    @ApiModelProperty(value = "文件ID", name = "id")
    private int id;
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID", name = "userId")
    private int userId;
    /**
     * 文件名称
     */
    @ApiModelProperty(value = "文件名称", name = "name")
    private String name;
    /**
     * 文件md5
     */
    @ApiModelProperty(value = "文件md5", name = "uid")
    private String uid;
    /**
     * 文件路径
     */
    @ApiModelProperty(value = "文件路径", name = "url")
    private String url;

  /**
     * 文件大小
     */
    @ApiModelProperty(value = "文件大小", name = "length")
    private Long length;

    /**
     * 文件mime
     */
    @ApiModelProperty(value = "文件mime", name = "mime")
    private String mime;

    /**
     * 文件类型： 0 文本txt， 1 图片audio, 2 音频audio，3 视频video, 4 其他文件zip
     */
    @ApiModelProperty(value = "文件类型： 0 文本txt， 1 图片audio, 2 音频audio，3 视频video, 4 其他文件zip", name = "type")
    private int type;

    /**
     *创建时间
     */
    @ApiModelProperty(value = "创建时间", name = "createtime")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private String createtime;

    public Source(int userId, String name, String uid, String url, Long length, String mime, int type) {
        this.userId = userId;
        this.name = name;
        this.uid = uid;
        this.url = url;
        this.length = length;
        this.mime = mime;
        this.type = type;
    }
}
