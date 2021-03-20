package com.sh.vhr.model;

import java.util.Date;

public class Msgcontent{

                /**
                * 
                */
                private Integer id;
                /**
                * 
                */
                private String title;
                /**
                * 
                */
                private String message;
                public Integer getId(){
                return id;
                }
                public void setId(Integer id){
                this.id=id;
                }
                public String getTitle(){
                    return title;
                }
                public void setTitle(String title){
                    this.title=title;
                }
                public String getMessage(){
                    return message;
                }
                public void setMessage(String message){
                    this.message=message;
                }
}