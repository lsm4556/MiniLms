package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.model.BannerInput;
import com.zerobase.fastlms.admin.model.CommonParam;
import com.zerobase.fastlms.course.dto.CourseDto;

import java.util.List;

public interface BannerService {
    
    List<BannerDto> list(CommonParam parameter);
    
    /**
     * 배너 신규 추가
     */
    boolean add(BannerInput parameter);
    
    /**
     * 배너 수정
     */
    boolean set(BannerInput parameter);
    
    /**
     * 배너 삭제
     */
    boolean del(String idList);

    BannerDto getById(long id);

}
