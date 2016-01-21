package model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by tao on 2015/12/18.
 */
public class BooksInfo {
    @JsonProperty("books")
    private List<BookTravelInfo> books;
    @JsonProperty("count")
    private String count;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<BookTravelInfo> getBooks() {
        return books;
    }

    public void setBooks(List<BookTravelInfo> books) {
        this.books = books;
    }
}
