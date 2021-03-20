package com.sh.vhr.model;

import java.util.Date;

public class Appraise {

    /**
     *
     */
    private Integer id;
    /**
     *
     */
    private Integer eid;
    /**
     * 考评结果
     */
    private String appresult;
    /**
     * 考评内容
     */
    private String appcontent;
    /**
     * 备注
     */
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getAppresult() {
        return appresult;
    }

    public void setAppresult(String appresult) {
        this.appresult = appresult;
    }

    public String getAppcontent() {
        return appcontent;
    }

    public void setAppcontent(String appcontent) {
        this.appcontent = appcontent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}