package model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tao on 2015/12/17.
 */
public class TicketInfo implements Serializable {
    @JsonProperty("price")
    private String price;
    @JsonProperty("open_time")
    private String open_time;
    @JsonProperty("attention")
    private List<AttentionInfo> attention;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOpen_time() {
        return open_time;
    }

    public void setOpen_time(String open_time) {
        this.open_time = open_time;
    }

    public List<AttentionInfo> getAttention() {
        return attention;
    }

    public void setAttention(List<AttentionInfo> attention) {
        this.attention = attention;
    }
}
