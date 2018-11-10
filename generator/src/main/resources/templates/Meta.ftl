package ${group}.${artifact}.bean.dto;
public class Meta {  

    private boolean success;  
    private String message;
    private String code;

    public Meta(boolean success) {  
        this.success = success;  
    }  

    public Meta(boolean success, String message) {  
        this.success = success;  
        this.message = message;  
    }
    
    public Meta(boolean success, String message, String code) {  
        this.success = success;
        this.message = message;
        this.code = code;
    }  

    public boolean isSuccess() {  
        return success;  
    }  

    public String getMessage() {  
        return message;  
    }
    
    public String getCode() {
    	return code;
    }
}