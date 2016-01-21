package result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseResult {
	@JsonIgnore
	public static final int SUCCESS = 0;
	@JsonProperty("error")
	private int code;//返回状态信息 0成功
	@JsonProperty("status")
	private String status;//返回状态信息 0成功
	@JsonProperty("date")
	private String date;//返回状态信息 0成功
	@JsonProperty("result")
	private String msg;//附加信息

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
