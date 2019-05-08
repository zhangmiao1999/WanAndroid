package com.example.wanandroid.http.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 张嘉河 on 2019/5/5.
 */
@Entity
public class Bean {
    @Id
    private Long id;
    @Property
    private String author;
    @Property
    private String title;
    @Property
    private String niceDate;
    @Property
    private String charName;
    @Property
    private String charSuperName;
    @Property
    private String link;
    @Generated(hash = 1436499783)
    public Bean(Long id, String author, String title, String niceDate,
            String charName, String charSuperName, String link) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.niceDate = niceDate;
        this.charName = charName;
        this.charSuperName = charSuperName;
        this.link = link;
    }
    @Generated(hash = 80546095)
    public Bean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAuthor() {
        return this.author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getNiceDate() {
        return this.niceDate;
    }
    public void setNiceDate(String niceDate) {
        this.niceDate = niceDate;
    }
    public String getCharName() {
        return this.charName;
    }
    public void setCharName(String charName) {
        this.charName = charName;
    }
    public String getCharSuperName() {
        return this.charSuperName;
    }
    public void setCharSuperName(String charSuperName) {
        this.charSuperName = charSuperName;
    }
    public String getLink() {
        return this.link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    
   
}
