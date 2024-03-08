package com.zerobase.fastlms.admin.model;


import com.zerobase.fastlms.admin.entity.BannerTargetType;
import lombok.Data;

@Data
public class BannerInput {

    Long id;

    String bannerName;
    String targetUrl;

    String bannerTargetType;
    int sortValue;
    boolean visible;

    String filename;
    String urlFilename;
    
}
