package com.xw.core.constant;

/**
 * @Author: 潘长河
 * @Date: 2019/12/9 10:32
 * @Description: 常量类 需要做初始化动作的在这里声明，例如集合
 */
public final class DocConstant {
    public static final String ACTIVIT_RESOURCE_TASK_CHECK = "";
    //内部组织职务类型sys_dict定义
    public static String post = "post";
    //基层组织类型sys_dict定义
    public static String org_primary = "org_primary";

    public static final String search_error = "查询失败";
    //新增成功定义
    public static final String add_success = "保存成功";

    public static final String add_error = "保存失败";

    public static final String update_success = "修改成功";

    public static final String update_error = "修改失败";

    public static final String delete_success = "删除成功";

    public static final String delete_error = "删除失败";

    public static final String publish_error = "发布失败";

    public static final String publish_success = "发布成功";

    public static final String canel_error = "撤销失败";

    public static final String canel_success = "撤销成功";


    public static final String list_size_error = "数量为0，不可保存";

    public static final String object_error = "对象不存在，不可保存";

    public static final String primary_key_error = "关键数据不存在，不可保存";

    // 上传文件定义
    public static final String upload_file_empty = "请选择需要上传的文件";

    public static final String upload_file_success = "上传文件成功";

    public static final String upload_file_error = "上传文件失败";

    public static final String download_file_emptyId = "请选择需要下载的文件";

    public static final String download_file_empty = "文件不存在";

    public static final String download_file_error = "下载文件失败";

    public static final String download_file_success = "下载文件成功";

    //会员审批
    public static final String application_submissions_success = "申请提交成功";

    public static final String application_submissions_error = "申请提交失败";

    public static final String member_approve_success = "审批提交成功";

    public static final String member_approve_error = "审批提交失败";



    //捐赠项目审批流程key
    public static final String ACTIVIT_DONATION_PROJECT_APPLY = "donationProjectApply";        //发布资源申请流程

    //捐款
    public static final String donation_fund = "donation_type01";
    //捐物
    public static final String donation_material = "donation_type10";
    //捐款和捐物
    public static final String donation_fund_material = "donation_type11";
    //审批通过，待发布状态
    public static final String project_status_wait = "project_status_wait";
    //已发布状态
    public static final String project_status_publish = "project_status_publish";
    //撤销状态
    public static final String project_status_canel = "project_status_canel";
    //待审批
    public static final String project_status_apply = "project_status_apply";
    //未提出申请
    public static final String project_status_before = "project_status_before";

    public static final String id_empty = "id 为空";
}
