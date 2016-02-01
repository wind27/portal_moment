package com.wind.entity.vo;

public class RequestParam {
    private static final long serialVersionUID = 1L;
    
    private long id;
    private long uid;
    private String uids;
    private String type;
    private int limit;
    private int skip;
    private String token;
    private ClientInfo client_info;
    
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getUid() {
        return uid;
    }
    public void setUid(long uid) {
        this.uid = uid;
    }
    public String getUids() {
        return uids;
    }
    public void setUids(String uids) {
        this.uids = uids;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getLimit() {
        return limit;
    }
    public void setLimit(int limit) {
        this.limit = limit;
    }
    public int getSkip() {
        return skip;
    }
    public void setSkip(int skip) {
        this.skip = skip;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public ClientInfo getClient_info() {
        return client_info;
    }
    public void setClient_info(ClientInfo client_info) {
        this.client_info = client_info;
    }
}
