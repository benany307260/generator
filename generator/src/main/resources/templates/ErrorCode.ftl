package ${group}.${artifact}.bean.errorcode;
/** 
 * 类名：管理系统错误码<br> 
 * 类说明：<br> 
 *  
 */  
public enum ErrorCode {  
  
    @Error(msg="请先登录系统")  
    err_1000001,  
    
    @Error(msg="没有权限操作")  
    err_1000002, 
    
    @Error(msg="登录超时")  
    err_1000003,  
    
    @Error(msg="新增用户信息失败")  
    err_1000004,
    
    @Error(msg="修改用户信息失败")  
    err_1000005,
    
    @Error(msg="用户状态不合法")  
    err_1000006,
    
    @Error(msg="修改用户状态失败")  
    err_1000007,

    @Error(msg="查询用户列表失败")  
    err_1000008,
    
    @Error(msg="请求参数不合法")  
    err_1000009,
    
    @Error(msg="用户名不合法")  
    err_1000010,
    
    @Error(msg="用户类型不合法")  
    err_1000011,
    
    @Error(msg="用户名称不合法")  
    err_1000012,
    
    @Error(msg="手机号不合法")  
    err_1000013,
    
    @Error(msg="查询日历信息失败")  
    err_1000014,
    
    @Error(msg="导入日历数据失败")  
    err_1000015,
    
    @Error(msg="修改日期类型失败")  
    err_1000016,
    
    @Error(msg="日期类型不合法")  
    err_1000017,
    
    @Error(msg="日期不合法")  
    err_1000018,
    
    @Error(msg="上传文件格式不正确，必须为xls或xlsx")  
    err_1000019,
    
    @Error(msg="上传文件格式不正确")  
    err_1000020,
    
    @Error(msg="新增供应商失败")  
    err_1000021,
    
    @Error(msg="修改供应商信息失败")  
    err_1000022,
    
    @Error(msg="修改供应商状态失败")  
    err_1000023,
    
    @Error(msg="查询供应商列表失败")  
    err_1000024,
    
    @Error(msg="导出供应商记录失败")  
    err_1000025,
    
    @Error(msg="公司全称不合法")  
    err_1000026,
    
    @Error(msg="公司简称不合法")  
    err_1000027,
    
    @Error(msg="供应商状态不合法")  
    err_1000028,
    
    @Error(msg="供应商数据导出失败")  
    err_1000029,
    
    @Error(msg="新增人员信息失败")  
    err_1000030,
    
    @Error(msg="修改人员信息失败")  
    err_1000031,
    
    @Error(msg="修改人员状态失败")  
    err_1000032,
    
    @Error(msg="查询人员列表失败")  
    err_1000033,
    
    @Error(msg="入职时间不能为空")  
    err_1000034,
    
    @Error(msg="人员类型不合法")  
    err_1000035,
    
    @Error(msg="岗位信息不合法")  
    err_1000036,
    
    @Error(msg="身份证信息不合法")  
    err_1000037,
    
    @Error(msg="所属供应商不能为空")  
    err_1000038,
    
    @Error(msg="项目信息不合法")  
    err_1000039,
    
    @Error(msg="级别信息不合法")  
    err_1000040,
    
    @Error(msg="项目经理不能为空")  
    err_1000041,
    
    @Error(msg="人员状态不合法")  
    err_1000042,
    
    @Error(msg="修改人员状态失败")  
    err_1000043,
    
    @Error(msg="请选择供应商")  
    err_1000044,
    
    @Error(msg="配置用户管辖供应商失败")  
    err_1000045,
 
    @Error(msg="删除用户管辖供应商失败")  
    err_1000046,
    
    @Error(msg="查询用户管辖供应商列表失败")  
    err_1000047,
    
    @Error(msg="导入成本中心科目失败")  
    err_1000048,
    
    @Error(msg="修改成本中心科目信息失败")  
    err_1000049,
    
    @Error(msg="查询成本中心科目信息失败")  
    err_1000050,
    
    @Error(msg="成本中心科目状态不合法")  
    err_1000051,
    
    @Error(msg="修改成本中心科目状态失败")  
    err_1000052,
    
    @Error(msg="基本人员数据导出失败")  
    err_1000053,
    
    @Error(msg="查询考勤记录失败")  
    err_1000054,
    
    @Error(msg="导出考勤记录失败")  
    err_1000055,
    
    @Error(msg="导入考勤记录失败")  
    err_1000056,
    
    @Error(msg="审批状态不合法")  
    err_1000057,
    
    @Error(msg="审批失败")  
    err_1000058,
    
    @Error(msg="查询审批记录失败")  
    err_1000059,
    
    @Error(msg="导出审批记录失败")  
    err_1000060,
    
    @Error(msg="请说明审批意见")  
    err_1000061,
    
    @Error(msg="用户账号已存在")  
    err_1000062,
    
    @Error(msg="用户无权限操作")  
    err_1000063,
    
    @Error(msg="提交晋升申请失败")  
    err_1000064,
    
    @Error(msg="查询晋升申请列表失败")  
    err_1000065,
    
    @Error(msg="修改晋升申请失败")  
    err_1000066,
    
    @Error(msg="删除晋升申请失败")  
    err_1000067,
    
    @Error(msg="获取工作日天数失败")  
    err_1000068,
    
    @Error(msg="用户账号已存在")  
    err_1000069,
    
    @Error(msg="供应商全称已存在")  
    err_1000070,
    
    @Error(msg="设置培训时间失败")  
    err_1000071,
    
    @Error(msg="导入人员姓名在人员基础信息中不存在") 
    err_1000072,
    
    @Error(msg="姓名不合法")  
    err_1000073,
    
    /***2开头错误码***/
    
    @Error(msg="参数不正确")  
    err_2000001, 
    
    @Error(msg="角色已被使用")  
    err_2000002, 
    
    @Error(msg="系统角色不能删除")  
    err_2000003, 
    
    @Error(msg="导出失败")  
    err_2000004, 
    
    @Error(msg="无数据可导出")  
    err_2000005, 
    
    @Error(msg="日期与其他申请的日期存在重叠")  
    err_2000006,
    
    @Error(msg="不满足条件，无法操作")  
    err_2000007,
    
    /***2开头错误码***/
    
      
    @Error(msg="系统异常")  
    err_1009999;  
    
    /** 
     * 返回错误码 
     */  
    public String getCode(){  
        return this.name();  
    }  
      
    /** 
     * 返回错误信息 
     */  
    public String getMessage(){  
        Error error = null;  
        try {  
            error = this.getClass().getField(this.getCode()).getAnnotation(Error.class);  
        } catch (Exception e) {  
            return null;  
        }  
        return error.msg();  
    }  
  
}