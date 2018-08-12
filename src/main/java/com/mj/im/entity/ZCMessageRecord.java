package com.mj.im.entity;

import java.util.Date;

public class ZCMessageRecord {
    private Long id;

    private String chatroomid;

    private Long frommemberid;

    private String fromnick;

    private String fromavator;

    private String fromusername;

    private String message;

    private String msgtype;

    private String msgtimestamp;

    private String memo2;

    private String prop5;

    private String prop6;

    private String prop7;

    private String prop8;

    private String adduser;

    private Date addtime;

    private String modifyuser;

    private Date modifytime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChatroomid() {
        return chatroomid;
    }

    public void setChatroomid(String chatroomid) {
        this.chatroomid = chatroomid;
    }

    public Long getFrommemberid() {
        return frommemberid;
    }

    public void setFrommemberid(Long frommemberid) {
        this.frommemberid = frommemberid;
    }

    public String getFromnick() {
        return fromnick;
    }

    public void setFromnick(String fromnick) {
        this.fromnick = fromnick;
    }

    public String getFromavator() {
        return fromavator;
    }

    public void setFromavator(String fromavator) {
        this.fromavator = fromavator;
    }

    public String getFromusername() {
        return fromusername;
    }

    public void setFromusername(String fromusername) {
        this.fromusername = fromusername;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public String getMsgtimestamp() {
        return msgtimestamp;
    }

    public void setMsgtimestamp(String msgtimestamp) {
        this.msgtimestamp = msgtimestamp;
    }

    public String getMemo2() {
        return memo2;
    }

    public void setMemo2(String memo2) {
        this.memo2 = memo2;
    }

    public String getProp5() {
        return prop5;
    }

    public void setProp5(String prop5) {
        this.prop5 = prop5;
    }

    public String getProp6() {
        return prop6;
    }

    public void setProp6(String prop6) {
        this.prop6 = prop6;
    }

    public String getProp7() {
        return prop7;
    }

    public void setProp7(String prop7) {
        this.prop7 = prop7;
    }

    public String getProp8() {
        return prop8;
    }

    public void setProp8(String prop8) {
        this.prop8 = prop8;
    }

    public String getAdduser() {
        return adduser;
    }

    public void setAdduser(String adduser) {
        this.adduser = adduser;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getModifyuser() {
        return modifyuser;
    }

    public void setModifyuser(String modifyuser) {
        this.modifyuser = modifyuser;
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }
}