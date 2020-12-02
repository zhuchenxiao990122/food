package com.xw.core.constant;

/**
 * @Author: 潘长河
 * @Date: 2019/12/9 10:32
 * @Description: 常量接口 常量在这里声明
 */
public interface DocConstantSuper {


    //api参数类型
    String API_PARAMETER_TYPE_IN = "1";
    String API_PARAMETER_TYPE_OUT = "2";

    //接口是否发布
    String API_PUBLISH_ON = "1";
    String API_PUBLISH_OFF = "0";

    //是否需要验证码
    String NEED_VERIFY_CODE = "1";
    String NO_VERIFY_CODE = "0";

    //请求方式
    String CASS_METHOD_GET = "1";
    String CASS_METHOD_POST = "2";

    //APP类型
    String APP_API_TYPE1 = "1";//无需验证
    String APP_API_TYPE2 = "2";//用户名密码
    String APP_API_TYPE3 = "3";//用户名密码+验证码
    String APP_API_TYPE4 = "4";//Token

    //是否通过DaaS
    String BY_DAAS_ON = "1";
    String BY_DAAS_OFF = "0";


    //系统分类
    String APP_CLASSIFY1 = "1";//提供方
    String APP_CLASSIFY2 = "2";//使用方
    String APP_CLASSIFY3 = "3";//两者兼

    //Dass字段名
    String DASS_FIELD_LOGIN_NAME = "username";
    String DASS_FIELD_PWD = "password";
    String DASS_FIELD_VERIFY_CODE = "verifyCode";

    //删除标记
    String DELETE_ON = "1";
    String DELETE_OFF = "0";

    //注册网关超时
    Integer GATEWAY_REGISTER_TIMEOUT = 3000;

    //申请流程key
    String ACTIVIT_RESOURCE_APPLY = "resourceApply";    //发布资源申请流程
    String ACTIVIT_USE_RESOURCE = "myProcess_1";        //申请使用资源流程

    //流程任务key
    String ACTIVIT_RESOURCE_TASK_APPLY = "apply";        //发布资源申请流程
    String ACTIVIT_USE_RESOURCE_TASK_APPLY = "apply";    //申请使用资源流程
    String ACTIVIT_RESOURCE_TASK_CHECK = "check";        //发布资源申请流程
    String ACTIVIT_USE_RESOURCE_TASK_CHECK = "check";    //申请使用资源流程

    //生成编码使用的areaCode
    String DEFAULT_AREA_CODE = "330800";
    //生成编码使用的taskType
    String APPLY_TASK_TYPE = "1";
    String USE_TASK_TYPE = "2";
    //生成编码使用的codeType
    String CODE_TYPE_ITEM = "item_code";

    //参数字段
    String ARGS_PREFIX = "gw_";
    String ARGS_APP_KEY = ARGS_PREFIX + "appKey";
    String ARGS_SIGN = ARGS_PREFIX + "sign";
    String ARGS_REQUEST_TIME = ARGS_PREFIX + "requestTime";
    String ARGS_ACCOUNT_ID = ARGS_PREFIX + "accountId";


    /**
     * 会员类型
     * MEMBER_ADULT 成人会员
     * MEMBER_TEENAGER 青少年会员
     * MEMBER_TROUP  团体会员
     */
    String MEMBER_ADULT = "1";
    String MEMBER_TEENAGER = "2";
    String MEMBER_GROUP = "3";

    /**
     * 会员申请类别
     * MEMBER_APPLY_REGISTER：入会
     * MEMBER_APPLY_TRANSFER： 转会
     * MEMBER_APPLY_WITHDRAW： 退会
     */
    String MEMBER_APPLY_REGISTER = "00";
    String MEMBER_APPLY_TRANSFER = "01";
    String MEMBER_APPLY_WITHDRAW = "10";
    String MEMBER_APPLY_EXPEL = "11";

    /**
     * 未审批通过
     * NOT_APPROVED 未审批
     * APPROVE_PASS 审批通过
     * NOT_APPROVE_PASS 未审批通过
     */
    String NOT_APPROVED = "00";
    String APPROVE_PASS = "01";
    String NOT_APPROVE_PASS = "10";

    /**
     * 审批通过
     */
    String MEMBER_APPROVED = "0";

}
