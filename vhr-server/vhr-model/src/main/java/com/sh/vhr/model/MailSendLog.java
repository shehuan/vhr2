package com.sh.vhr.model;

import java.util.Date;

public class MailSendLog{

                /**
                * 
                */
                private String msgid;
                /**
                * 
                */
                private Integer empid;
                /**
                * 0发送中，1发送成功，2发送失败
                */
                private Integer status;
                /**
                * 
                */
                private String routekey;
                /**
                * 
                */
                private String exchange;
                /**
                * 重试次数
                */
                private Integer count;
                public String getMsgid(){
                    return msgid;
                }
                public void setMsgid(String msgid){
                    this.msgid=msgid;
                }
                public Integer getEmpid(){
                return empid;
                }
                public void setEmpid(Integer empid){
                this.empid=empid;
                }
                public Integer getStatus(){
                return status;
                }
                public void setStatus(Integer status){
                this.status=status;
                }
                public String getRoutekey(){
                    return routekey;
                }
                public void setRoutekey(String routekey){
                    this.routekey=routekey;
                }
                public String getExchange(){
                    return exchange;
                }
                public void setExchange(String exchange){
                    this.exchange=exchange;
                }
                public Integer getCount(){
                return count;
                }
                public void setCount(Integer count){
                this.count=count;
                }
}