package cn.caam.gs;

import java.util.concurrent.Executor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import cn.caam.gs.common.handler.CustomerLocaleResolver;
import cn.caam.gs.common.util.DateCommonUtil;
import cn.caam.gs.common.util.JsonLogCommonUtil;
import cn.caam.gs.common.util.LogCommonUtil;
import cn.caam.gs.common.util.MessageSourceUtil;
import cn.caam.gs.common.util.StringCommonUtil;

@SpringBootApplication
@ComponentScan({"cn.caam.gs.*"})
@MapperScan("cn.caam.gs.domain.db.*")
@ServletComponentScan({"cn.caam.gs.*"})
@Import(InterceptorConfig.class)
public class ApplicationRun {
    
    @Bean
    public LocaleResolver localeResolver() {
        return new CustomerLocaleResolver();
    }
    
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }
    


    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
    
    
    @Bean
    public LogCommonUtil getLogCommonUtil() {
        return new LogCommonUtil();
    }
    
    @Bean
    public StringCommonUtil getStringCommonUtil() {
        return new StringCommonUtil();
    }
    
    @Bean
    public MessageSourceUtil getResourceUtil() {
        return new MessageSourceUtil();
    }

    @Bean
    public DateCommonUtil getDateCommonUtil() {
        return new DateCommonUtil();
    }
    
    @Bean
    public JsonLogCommonUtil getJsonLogCommonUtil() {
        return new JsonLogCommonUtil();
    }
    
    @Bean
    public RestTemplate rest(RestTemplateBuilder builder) {
        return builder.build();
    }

    /**
     * 非同期処理のスレッドプールの作成
     *
     * @return スレッドプールの実行部品
     */
    @Bean(name = "threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor() {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5); // 並列処理数を 5 に制限
        executor.setMaxPoolSize(10); //
        executor.setQueueCapacity(1000); // 非同期処理を待つキューの長さを 500 までに制限
        executor.setKeepAliveSeconds(60);
        executor.setAllowCoreThreadTimeOut(true);

        return executor;
    }

	public static void main(String[] args) {
		SpringApplication.run(ApplicationRun.class, args);
	}
}
