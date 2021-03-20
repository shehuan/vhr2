package com.sh.vhr.model;

import java.util.Date;

public class Employeetrain{

                /**
                * 
                */
                private Integer id;
                /**
                * 员工编号
                */
                private Integer eid;
                /**
                * 培训内容
                */
                private String traincontent;
                /**
                * 备注
                */
                private String remark;
                public Integer getId(){
                return id;
                }
                public void setId(Integer id){
                this.id=id;
                }
                public Integer getEid(){
                return eid;
                }
                public void setEid(Integer eid){
                this.eid=eid;
                }
                public String getTraincontent(){
                    return traincontent;
                }
                public void setTraincontent(String traincontent){
                    this.traincontent=traincontent;
                }
                public String getRemark(){
                    return remark;
                }
                public void setRemark(String remark){
                    this.remark=remark;
                }
}