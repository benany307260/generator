package ${group}.${artifact}.bean.dto;

import java.util.List;

import ${group}.${artifact}.bean.errorcode.ErrorCode;

public class ResponseBean {  
  
    private static final String OK = "ok";  
    private static final String ERROR = "error";  
  
    private Meta meta;  
    private Object data;
    private List<Object> dataList;

    public ResponseBean success() {  
        this.meta = new Meta(true, OK);  
        return this;  
    }  
  
    public ResponseBean success(Object data) {  
        this.meta = new Meta(true, OK);  
        this.data = data;  
        return this;  
    }
    
    public ResponseBean successMsg(String message) {  
        this.meta = new Meta(true, message);  
        this.data = data;  
        return this;  
    }
    
    public ResponseBean successInList(List dataList) {  
        this.meta = new Meta(true, OK);  
        this.dataList = dataList;  
        return this;  
    }
    
    public ResponseBean success(Object data, List dataList) {  
        this.meta = new Meta(true, OK);
        this.data = data;
        this.dataList = dataList;  
        return this;  
    }
  
    public ResponseBean failure() {  
        this.meta = new Meta(false, ERROR);  
        return this;  
    }
    
    public ResponseBean failure(Object data) {  
        this.meta = new Meta(false, ERROR);
        this.data = data;
        return this;  
    } 
  
    public ResponseBean failure(String message) {  
        this.meta = new Meta(false, message);  
        return this;  
    }
    
    public ResponseBean failure(String message, String code) {  
        this.meta = new Meta(false, message, code);  
        return this;  
    }
    
    public ResponseBean failureErrCode(ErrorCode errorCode) {  
        this.meta = new Meta(false, errorCode.getMessage(), errorCode.getCode());  
        return this;  
    }
    
    public ResponseBean failureErrCode(ErrorCode errorCode, String errMsg) {  
        this.meta = new Meta(false, errMsg, errorCode.getCode());  
        return this;  
    }
  
    public Meta getMeta() {  
        return meta;  
    }  
  
    public Object getData() {  
        return data;  
    }
    
    public List<Object> getDataList() {
		return dataList;
	}

	public void setDataList(List<Object> dataList) {
		this.dataList = dataList;
	}



  
} 