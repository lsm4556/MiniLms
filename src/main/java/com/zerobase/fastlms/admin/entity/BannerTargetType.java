package com.zerobase.fastlms.admin.entity;

public enum BannerTargetType {
    SELF("_self"),
    BLANK("_blank");

    private final String value;

    BannerTargetType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
