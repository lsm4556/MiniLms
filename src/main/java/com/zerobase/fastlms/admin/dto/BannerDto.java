package com.zerobase.fastlms.admin.dto;

import com.zerobase.fastlms.admin.entity.Banner;
import com.zerobase.fastlms.admin.entity.BannerTargetType;
import com.zerobase.fastlms.admin.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BannerDto {

    Long id;
    String bannerName;
    LocalDateTime regDt;

    String targetUrl;
    String bannerTargetType;
    int sortValue;
    boolean visible;

    String filename;
    String urlFilename;

    //추가컬럼
    long totalCount;
    long seq;

    public static List<BannerDto> of(List<Banner> banners) {
        if (banners != null) {
            List<BannerDto> bannerList = new ArrayList<>();
            for (Banner x : banners) {
                bannerList.add(of(x));
            }
            return bannerList;
        }

        return null;
    }

    public static BannerDto of(Banner banner) {
        return BannerDto.builder()
                .id(banner.getId())
                .urlFilename(banner.getUrlFilename())
                .targetUrl(banner.getTargetUrl())
                .sortValue(banner.getSortValue())
                .bannerTargetType(banner.getBannerTargetType())
                .visible(banner.isVisible())
                .bannerName(banner.getBannerName())
                .regDt(banner.getRegDt())
                .build();
    }

    public String getRegDtText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        return regDt != null ? regDt.format(formatter) : "";
    }

}
