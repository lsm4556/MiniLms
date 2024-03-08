package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.entity.Banner;
import com.zerobase.fastlms.admin.mapper.BannerMapper;
import com.zerobase.fastlms.admin.model.BannerInput;
import com.zerobase.fastlms.admin.model.CommonParam;
import com.zerobase.fastlms.admin.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BannerServiceImpl implements BannerService {
    
    private final BannerRepository bannerRepository;

    private final BannerMapper bannerMapper;

    @Override
    public List<BannerDto> list(CommonParam parameter) {
        long totalCount = bannerMapper.selectListCount(parameter);

        List<BannerDto> list = bannerMapper.selectList(parameter);
        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for (BannerDto x : list) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }

        return list;
    }
    
    @Override
    public boolean add(BannerInput parameter) {
        
        Banner banner = Banner.builder()
                .bannerName(parameter.getBannerName())
                .targetUrl(parameter.getTargetUrl())
                .bannerTargetType(parameter.getBannerTargetType())
                .sortValue(parameter.getSortValue())
                .visible(parameter.isVisible())
                .filename(parameter.getFilename())
                .urlFilename(parameter.getUrlFilename())
                .regDt(LocalDateTime.now())
                .build();
        bannerRepository.save(banner);
        
        return true;
    }
    
    @Override
    public boolean set(BannerInput parameter) {
        
        Optional<Banner> optionalBanner = bannerRepository.findById(parameter.getId());
        if (optionalBanner.isPresent()) {
            Banner banner = optionalBanner.get();
            banner.setBannerName(parameter.getBannerName());
            banner.setTargetUrl(parameter.getTargetUrl());
            banner.setBannerTargetType(parameter.getBannerTargetType());
            banner.setSortValue(parameter.getSortValue());
            banner.setVisible(parameter.isVisible());
            banner.setFilename(parameter.getFilename());
            banner.setUrlFilename(parameter.getUrlFilename());
            bannerRepository.save(banner);
        }
        
        return true;
    }

    @Override
    public BannerDto getById(long id) {
        return bannerRepository.findById(id).map(BannerDto::of).orElse(null);
    }

    @Override
    public boolean del(String idList) {

        if (idList != null && !idList.isEmpty()) {
            String[] ids = idList.split(",");
            for (String x : ids) {
                long id = 0L;
                try {
                    id = Long.parseLong(x);
                } catch (Exception e) {
                }

                if (id > 0) {
                    bannerRepository.deleteById(id);
                }
            }
        }

        return true;
    }
}
