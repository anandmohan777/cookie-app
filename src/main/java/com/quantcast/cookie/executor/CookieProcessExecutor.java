package com.quantcast.cookie.executor;

import com.quantcast.cookie.exception.ServiceException;
import com.quantcast.cookie.model.Input;
import com.quantcast.cookie.service.ICookieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.quantcast.cookie.model.ApplicationStatus.APPLICATION_FAILED;
import static com.quantcast.cookie.model.ApplicationStatus.SUCCESS;
import static com.quantcast.cookie.util.InputParser.parseInput;

public class CookieProcessExecutor implements IProcessExecutor {
    private static final Logger LOGGER = LoggerFactory.getLogger(CookieProcessExecutor.class);
    private final ICookieService cookieService;

    public CookieProcessExecutor(final ICookieService cookieService) {
        this.cookieService = cookieService;
    }

    @Override
    public int execute(String[] args) {
        try {
            final Input input = parseInput(args);
            cookieService.process(input);
            return SUCCESS.getValue();
        } catch (ServiceException | RuntimeException exp) {
            LOGGER.error("Application failed to progress", exp);
        }
        return APPLICATION_FAILED.getValue();
    }
}
