package com.quantcast.cookie.service;

import com.quantcast.cookie.exception.ServiceException;
import com.quantcast.cookie.model.Input;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static java.time.LocalDate.parse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
public class CookieServiceTest {
    private CookieService cookieService;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        cookieService = new CookieService();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void execute_whenGivenArgumentDatePresent_thenPrintCookie() throws ServiceException {
        final Input input = new Input("src/test/resources/cookie_log.csv", parse("2018-12-09"));

        cookieService.process(input);

        assertThat(outputStreamCaptor.toString().trim()).contains("AtY0laUfhglK3lC7");
    }

    @Test
    public void execute_whenGivenArgumentDateContainsMoreThanOneActiveCookie_thenPrintCookies() throws ServiceException {
        final Input input = new Input("src/test/resources/cookie_log.csv", parse("2018-12-08"));

        cookieService.process(input);

        assertThat(outputStreamCaptor.toString().trim()).contains("SAZuXPGUrfbcn5UA");
        assertThat(outputStreamCaptor.toString().trim()).contains("4sMM2LxV07bPJzwf");
        assertThat(outputStreamCaptor.toString().trim()).contains("fbcn5UAVanZf6UtG");
    }

    @Test
    public void execute_whenGivenArgumentDateNotPresent_thenReturnsEmpty() throws ServiceException {
        final Input input = new Input("src/test/resources/cookie_log.csv", parse("2018-12-10"));

        cookieService.process(input);

        assertThat(outputStreamCaptor.toString().trim()).isEmpty();
    }

    @Test
    public void execute_whenFilePathDoesNotExist_thenThrowsServiceException() {
        final Input input = new Input("src/test/resources/cookie_log1.csv", parse("2018-12-09"));

        assertThatThrownBy(() -> cookieService.process(input)).isInstanceOf(ServiceException.class);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
