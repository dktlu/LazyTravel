package model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by tao on 2015/12/17.
 */
public class ScenicInfo implements Serializable {
    @JsonProperty("name")
    private String name;
    @JsonProperty("location")
    private LocationInfo location;
    @JsonProperty("telephone")
    private String telephone;
    @JsonProperty("star")
    private String star;
    @JsonProperty("url")
    private String url;
    @JsonProperty("abstract")
    private String abst;
    @JsonProperty("description")
    private String description;
    @JsonProperty("ticket_info")
    private TicketInfo ticket_info;

    public String getAbst() {
        return abst;
    }

    public void setAbst(String abst) {
        this.abst = abst;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocationInfo getLocation() {
        return location;
    }

    public void setLocation(LocationInfo location) {
        this.location = location;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TicketInfo getTicket_info() {
        return ticket_info;
    }

    public void setTicket_info(TicketInfo ticket_info) {
        this.ticket_info = ticket_info;
    }
}
