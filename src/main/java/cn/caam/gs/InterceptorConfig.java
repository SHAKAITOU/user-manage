package cn.caam.gs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class InterceptorConfig implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(new UserLoginInterceptor())
		.addPathPatterns("/**")
		.excludePathPatterns("/error", "/favicon.ico")
		.excludePathPatterns("/static/**","/i18n/**","/templates/**")
		.excludePathPatterns("/css/**","/img/**","/js/**","/sound/**","/template/**","/ttl/**","/webfonts/**")
		.excludePathPatterns("/admin","/admin/init","/admin/userLogin","/admin/logout","/admin/refeshImg")
		.excludePathPatterns("/admin/*.js.map")
	    .excludePathPatterns("/login","/login/init","/login/userLogin","/login/sendAuthCode","/login/logout","/login/refeshImg")
	    .excludePathPatterns("/login/userRegist/**", "/login/userLogin/sendAuthCode")
	    .excludePathPatterns("/passwordForget", "/passwordForget/**")
	    .excludePathPatterns("/passwordChange", "/passwordChange/**")
	    .excludePathPatterns("/common", "/common/**");
	}
}
