package com.itheima.ssm.domain;

import java.io.Serializable;

/**
 * @author 盛林
 * @date 2018/12/3
 */
public class RoleandUser implements Serializable {
    private String uid;
    private String rid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }
}
