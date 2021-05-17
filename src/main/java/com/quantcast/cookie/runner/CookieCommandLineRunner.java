package com.quantcast.cookie.runner;

import com.quantcast.cookie.executor.IProcessExecutor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import static java.lang.System.exit;

public class CookieCommandLineRunner implements CommandLineRunner {

    private final ApplicationContext applicationContext;
    private final IProcessExecutor processExecutor;

    public CookieCommandLineRunner(final ApplicationContext applicationContext,
                                   final IProcessExecutor processExecutor) {
        this.applicationContext = applicationContext;
        this.processExecutor = processExecutor;
    }

    @Override
    public void run(final String[] args) {
        terminateApplication(() -> processExecutor.execute(args));
    }

    private void terminateApplication(final ExitCodeGenerator exitCodeGenerator) {
        exit(SpringApplication.exit(applicationContext, exitCodeGenerator));
    }
}
