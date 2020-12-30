package com.test.entity;

import java.io.Serializable;

public class Te implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer bId;

    private String bName;

    private String remark;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getbId() {
        return bId;
    }

    public void setbId(Integer bId) {
        this.bId = bId;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Book{" +
        "id=" + id +
        ", bId=" + bId +
        ", bName=" + bName +
        ", remark=" + remark +
        "}";
    }
}
