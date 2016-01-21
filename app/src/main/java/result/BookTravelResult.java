package result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import model.BookTravelInfo;
import model.BooksInfo;
import model.ScenicInfo;

/**
 * Created by tao on 2015/12/18.
 */
public class BookTravelResult {
    @JsonIgnore
    public static final int SUCCESS = 0;
    @JsonProperty("ret")
    private String ret;
    @JsonProperty("errcode")
    private int errcode;
    @JsonProperty("errmsg")
    private String errmsg;
    @JsonProperty("ver")
    private String ver;
    @JsonProperty("data")
    private BooksInfo data;

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public BooksInfo getData() {
        return data;
    }

    public void setData(BooksInfo data) {
        this.data = data;
    }
}
