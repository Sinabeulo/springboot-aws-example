package com.jojoldu.book.springboot.config.auth;

import com.jojoldu.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers().frameOptions().disable()//h2-console 화면을 사용하기 위해 해당 옵션들을 disable 합니다.
                .and()
                    .authorizeRequests()//URL 별 권한 관리를 설정하는 옵션의 시작점입니다. 선언되어야만, antMatchers 옵션을 사용할 수 있습니다.
                    //권한 관리 대상을 지정하는 옵션이다. URL, HTTP 메서드별로 관리가 가능하다.
                    .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated()//anyRequest 설정된 값들 이외 나머지 URL들을 나타낸다.
                .and()
                    .logout()//logout 기능에 대한 설정 진입점입니다.
                        .logoutSuccessUrl("/")//성공 시 / 로 이동
                .and()
                    .oauth2Login()//로그인 기능에 대한 여러 설정의 진입점이다.
                        .userInfoEndpoint()//성공 후 사용자 정보를 가져올 때의 설정들을 담당합니다.
                            .userService(customOAuth2UserService);//소셜로그인 성공 시 후속 조치를 진행항 UserService 인터페이스 구현을 등록
    }
}
