package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.service.CustomOAuth2UserService;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	private final CustomOAuth2UserService customOAuth2UserService;
	private final UserService userService;

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**", "img/**");//무시할 파일
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); 	
		http.authorizeRequests()
				.antMatchers("/","/login","/signup","/sample/all").permitAll()
				.antMatchers("/user").hasRole("USER")
				.antMatchers("/admin").hasRole("ADMIN")
				//.anyRequest().authenticated() // ������ ��û�� � �����̵� �־�� ���ٰ���
			 .and()
			 	.formLogin()
			 	.loginPage("/userLogin")
			 	.defaultSuccessUrl("/", true)//�α��� ������ ������
			 .and()
			 	.logout()
			 		.logoutUrl("/logout")
			 		.logoutSuccessUrl("/")//�α׾ƿ� ������ ������������ �̵�
			 		.invalidateHttpSession(true)//���� ���ֱ�
			.and()
				.oauth2Login()//oauth2login���� ����
					.userInfoEndpoint()//oauth2login���� ������ ����
						.userService(customOAuth2UserService);///customOAuth2UserService���� ó��
		
		http.httpBasic();
		
	}

	//�α����Ҷ� �ʿ��� ����
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());//���������� �������� ���񽺸� userService�� ����, �н����� ���ڵ� ����
	}
	
	

}
