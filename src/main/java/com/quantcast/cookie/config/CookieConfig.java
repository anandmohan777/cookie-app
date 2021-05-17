package com.quantcast.cookie.config;

import com.quantcast.cookie.executor.CookieProcessExecutor;
import com.quantcast.cookie.executor.IProcessExecutor;
import com.quantcast.cookie.runner.CookieCommandLineRunner;
import com.quantcast.cookie.service.CookieService;
import com.quantcast.cookie.service.ICookieService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CookieConfig {
    @Bean
    public CommandLineRunner commandLineRunner(final ApplicationContext applicationContext,
                                               final IProcessExecutor processExecutor) {
        return new CookieCommandLineRunner(applicationContext, processExecutor);
    }

    @Bean
    public ICookieService cookieService() {
        return new CookieService();
    }

    @Bean
    public IProcessExecutor processExecutor(final ICookieService cookieService) {
        return new CookieProcessExecutor(cookieService);
    }
}
