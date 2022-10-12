package data;

import java.io.Serializable;

public class RawData implements Serializable{

	private static final long serialVersionUID = -1669949946588244176L;
	private String type;
	private Long clientId;
	private Long date;
	private String data;
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
	public RawData(String type, Long clientId, Long date, String data) {
		super();
		this.type = type;
		this.clientId = clientId;
		this.date = date;
		this.data = data;
	}
	public RawData() {
		super();
	}
	
	
}
