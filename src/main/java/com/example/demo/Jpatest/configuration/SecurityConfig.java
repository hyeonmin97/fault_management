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
		web.ignoring().antMatchers("/css/**", "/js/**", "img/**");//������ ����
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/login","/signup","/user","/sample/all").permitAll()
				.antMatchers("/","/sample/member").hasRole("USER")
				.antMatchers("/admin").hasRole("ADMIN")
				//.anyRequest().authenticated() // ������ ��û�� � �����̵� �־�� ���ٰ���
			 .and()
			 	.formLogin()
			 	.loginPage("/login")
			 	//.defaultSuccessUrl("/sample/all", true)//�α��� ������ ������
			 .and()
			 	.logout()
			 		.logoutSuccessUrl("/")//�α׾ƿ� ������ ������������ �̵�
			 		.invalidateHttpSession(true);//���� ���ֱ�
		http.csrf().disable(); 	
	}

	//�α����Ҷ� �ʿ��� ����
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());//���������� �������� ���񽺸� userService�� ����, �н����� ���ڵ� ����
	}
	
	

}
