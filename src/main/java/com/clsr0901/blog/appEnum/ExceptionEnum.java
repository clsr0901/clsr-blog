package com.ktcatv.qtms.applicationEnum;

import lombok.Getter;

@Getter
public enum ExceptionEnum {
    USER_NOT_EXITS(-1, "用户不存在"),
    PASSWORD_ERROR(-2, "密码错误"),
    USER_PHONE_ERROR(-3, "电话格式错误"),
    USER_EMAIL_ERROR(-4, "邮箱格式错误"),
    USER_EMAIL_NOT_EMPTY(-5, "邮箱不能为空"),
    GROUP_NAME_REPEAT(-6, "用户组名称重复"),
    GROUP_NOT_EXITS(-7, "用户组不存在"),
    USER_PASSWORD_ERROR(-8, "用户密码不正确"),
    USER_STATUS_DISABLE(-9, "用户状态禁用"),
    USER_PASSWORD_TOO_SHORT(-10, "用户密码必须6位以上"),
    GROUP_NOT_YOU_CREATE(-11, "用户组不是你创建的"),
    TEMPLET_NAME_REPEAT(-12, "模板名称重复"),
    TEMPLET_NOT_EXITS(-13, "模板不存在"),
    TEMPLET_HAVE_USED(-14, "模板已经被使用"),
    ELEMENT_NOT_EXITS(-15, "节点不存在"),
    USER_ERROR(-16, "账户或密码错误"),
    ELEMENTGROUP_NOT_EXITS(-17, "节点组不存在"),
    QUESTIONSUBJECTSOURCE_NOT_EXITS(-18, "问题来源不存在"),
    QUESTIONCLASS_NOT_EXITS(-19, "问题类别不存在"),
    FEEDBACKREGION_NOT_EXITS(-20, "反馈领域不存在"),
    QUESTIONTYPE_NOT_EXITS(-21, "问题类型不存在"),
    QUESTIONLEVEL_NOT_EXITS(-22, "问题等级不存在"),
    PRODUCTSTAGE_NOT_EXITS(-23, "产品阶段不存在"),
    ROLLINDEX_NOT_GREATER_THAN_INDEX(-24, "回退ID必须小于流程ID"),
    PRODUCTSUBJECT_NOT_EXITS(-25, "来源项目不存在"),
    FIRST_PROCEDURE_MUST_CREATEQUESTION(-26, "流程第一个步骤必须为创建问题"),
    UPLOADFILE_ERROR(-27, "上传文件失败"),
    QUESTION_NOT_EXITS(-28, "问题不存在"),
    TEMPLETSTRING_ERROR(-29, "模板字符串错误"),
    EXAMINEQUESTION_NOT_EXITS(-30, "审核问题不存在"),
    PRODUCEINDEX_ERROR(-31, "不能编辑当前流程"),
    ANALYSISQUESTION_NOT_EXITS(-32, "分析问题不存在"),
    REVIEWQUESTION_NOT_EXITS(-33, "审核问题不存在"),
    QUESTIONMEASURE_NOT_EXITS(-34, "问题解决措施不存在"),
    CHECKACCEPTQUESTION_NOT_EXITS(-35, "验收问题不存在"),
    LASTPROCEDURE_MUST_CLOSEQUESTION(-36, "流程最后一个步骤必须为关闭问题"),
    CURRENTUSER_DO_NOT_OPERATION(-37, "当前用户不是指定操作人"),
    NONE_NEXT_OPERATOR(-38, "未指定下一步操作人"),
    UPLOAD_ERROR(-39, "上传文件错误"),
    UPLOAD_CREATE_DIRECTORY_ERROR(-40, "创建文件夹失败"),
    UPLOAD_MD5_VERIFY_ERROR(-41, "MD5校验失败"),
    HAVE_NOT_QUESTION(-42, "问题记录为空"),
    SUBJECT_NOT_EXITS(-43, "项目不存在"),
    DEPARTMENT_NOT_EXITS(-44, "部门不存在"),


    TOKEN_EXPIRED(417, "token失效"),

    ;
    private Integer code;
    private String msg;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
