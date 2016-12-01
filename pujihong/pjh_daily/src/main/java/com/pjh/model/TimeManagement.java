package com.pjh.model;

import java.util.Date;

public class TimeManagement {
    private Integer id;

    private String title;

    private String description;

    private Date time;

    private Integer userid;

    private Integer statu;

    private Integer mailalert;
    //增加一列字符串时间 由于数据库查出来是 Thu Jul 22 00:58:32 CST 2010 格式的
    private String datetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getStatu() {
        return statu;
    }

    public void setStatu(Integer statu) {
        this.statu = statu;
    }

    public Integer getMailalert() {
        return mailalert;
    }

    public void setMailalert(Integer mailalert) {
        this.mailalert = mailalert;
    }

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
    
}