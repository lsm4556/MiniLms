package com.zerobase.fastlms.admin.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginHistoryDto {
    private String userId;
    private String ipAddress;
    private String userAgent;
    private LocalDateTime loginDt;

    //추가컬럼
    long totalCount;
    long seq;

    public String getLoginDtText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        return loginDt != null ? loginDt.format(formatter) : "";

    }
}
