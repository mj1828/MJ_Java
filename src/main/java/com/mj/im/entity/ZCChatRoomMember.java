package com.mj.im.entity;

import java.util.Date;

public class ZCChatRoomMember extends ZCChatRoomMemberKey {
    private String membertype;

    private Integer gag;

    private Integer prohibit;

    private String prop1;

    private String prop2;

    private String prop3;

    private String prop4;

    private String adduser;

    private Date addtime;

    private String modifyuser;

    private Date modifytime;

    public String getMembertype() {
        return membertype;
    }

    public void setMembertype(String membertype) {
        this.membertype = membertype;
    }

    public Integer getGag() {
        return gag;
    }

    public void setGag(Integer gag) {
        this.gag = gag;
    }

    public Integer getProhibit() {
        return prohibit;
    }

    public void setProhibit(Integer prohibit) {
        this.prohibit = prohibit;
    }

    public String getProp1() {
        return prop1;
    }

    public void setProp1(String prop1) {
        this.prop1 = prop1;
    }

    public String getProp2() {
        return prop2;
    }

    public void setProp2(String prop2) {
        this.prop2 = prop2;
    }

    public String getProp3() {
        return prop3;
    }

    public void setProp3(String prop3) {
        this.prop3 = prop3;
    }

    public String getProp4() {
        return prop4;
    }

    public void setProp4(String prop4) {
        this.prop4 = prop4;
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