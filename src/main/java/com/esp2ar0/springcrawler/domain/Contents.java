package com.esp2ar0.springcrawler.domain;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Contents {

    @Id
    private String title;

    private String price;

    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
