package com.zerobase.fastlms.configuration;

import com.zerobase.fastlms.course.model.ServiceResult;
import com.zerobase.fastlms.admin.dto.LoginHistoryDto;
import com.zerobase.fastlms.member.service.MemberService;
import com.zerobase.fastlms.util.RequestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
public class UserAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final MemberService memberService;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {

        ServiceResult result = this.memberService.insertLoginHistory(
                LoginHistoryDto.builder()
                        .userId(authentication.getName())
                        .ipAddress(RequestUtils.getClientIP(request))
                        .userAgent(RequestUtils.getUserAgent(request))
                        .build());

        if (!result.isResult()) {
            // 오류가 발생한 경우 오류 페이지로 리다이렉트
            response.sendRedirect(
                    "/common/error?message=" + URLEncoder.encode(result.getMessage(),
                            StandardCharsets.UTF_8.toString()));
        }

        response.sendRedirect("/");
    }


}
