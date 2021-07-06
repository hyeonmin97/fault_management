package com.example.demo.Jpatest.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.Jpatest.security.service.UserService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final UserService userService;

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**", "img/**");//무시할 폴더
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/login","/signup","/user","/sample/all").permitAll()
				.antMatchers("/","/sample/member").hasRole("USER")
				.antMatchers("/admin").hasRole("ADMIN")
				//.anyRequest().authenticated() // 나머지 요청은 어떤 권한이든 있어야 접근가능
			 .and()
			 	.formLogin()
			 	.loginPage("/login")
			 	//.defaultSuccessUrl("/sample/all", true)//로그인 성공후 페이지
			 .and()
			 	.logout()
			 		.logoutSuccessUrl("/")//로그아웃 성공시 메인페이지로 이동
			 		.invalidateHttpSession(true);//세션 없애기
		http.csrf().disable(); 	
	}

	//로그인할때 필요한 정보
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());//유저정보를 가져오는 서비스를 userService로 지정, 패스워드 인코드 지정
	}
	
	

}
