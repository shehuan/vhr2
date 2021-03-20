package com.sh.vhr.model;

import java.util.Date;

public class Oplog{

                /**
                * 
                */
                private Integer id;
                /**
                * 操作内容
                */
                private String operate;
                /**
                * 操作员ID
                */
                private Integer hrid;
                public Integer getId(){
                return id;
                }
                public void setId(Integer id){
                this.id=id;
                }
                public String getOperate(){
                    return operate;
                }
                public void setOperate(String operate){
                    this.operate=operate;
                }
                public Integer getHrid(){
                return hrid;
                }
                public void setHrid(Integer hrid){
                this.hrid=hrid;
                }
}