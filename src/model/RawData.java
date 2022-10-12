
package model;

public class RawData {
    private String type;
    private Long clientId;
    private Long date;
    private String data;

    public RawData(String type, Long date, String data) {
        this.type = type;
        this.date = date;
        this.data = data;
    }

    public RawData(String type, Long clientId, Long date, String data) {
        this.type = type;
        this.clientId = clientId;
        this.date = date;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
}
