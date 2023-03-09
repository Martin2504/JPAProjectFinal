package com.sparta.mg.jpaproject.tools;

public enum AccessLevel {
    BASIC("R"),
    UPDATE("CRU"),
    ADMIN("CRUD");

    private String desc;
    AccessLevel(String desc) {
        this.desc=desc;
    }

    public String getDesc() {
        return desc;
    }
}
