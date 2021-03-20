package com.sh.vhr.model;

import java.util.Date;

public class Employeeec {

    /**
     *
     */
    private Integer id;
    /**
     * 员工编号
     */
    private Integer eid;
    /**
     * 奖罚原因
     */
    private String ecreason;
    /**
     * 奖罚分
     */
    private Integer ecpoint;
    /**
     * 奖罚类别，0：奖，1：罚
     */
    private Integer ectype;
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

    public String getEcreason() {
        return ecreason;
    }

    public void setEcreason(String ecreason) {
        this.ecreason = ecreason;
    }

    public Integer getEcpoint() {
        return ecpoint;
    }

    public void setEcpoint(Integer ecpoint) {
        this.ecpoint = ecpoint;
    }

    public Integer getEctype() {
        return ectype;
    }

    public void setEctype(Integer ectype) {
        this.ectype = ectype;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}