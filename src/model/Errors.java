
package model;

public class Errors {
    private String code;
    private String errorContent;

    public Errors() {
    }

    public Errors(String code, String errorContent) {
        this.code = code;
        this.errorContent = errorContent;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorContent() {
        return errorContent;
    }

    public void setErrorContent(String errorContent) {
        this.errorContent = errorContent;
    }
    
}
