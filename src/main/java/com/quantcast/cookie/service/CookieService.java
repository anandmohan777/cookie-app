package com.quantcast.cookie.service;

import com.quantcast.cookie.exception.ServiceException;
import com.quantcast.cookie.model.CookieLog;
import com.quantcast.cookie.model.Input;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.OptionalLong;

import static com.quantcast.cookie.util.InputParser.readCsvCookieLog;
import static java.util.Map.Entry;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class CookieService implements ICookieService {
    @Override
    public void process(final Input input) throws ServiceException {
        final List<CookieLog> cookieLogs = readCsvCookieLog(input.getFileName());
        final Map<String, Long> groupByCookie = groupCookieForInputDate(input.getDate(), cookieLogs);
        final OptionalLong maxActiveCookieSize = getMaxActiveCookieSize(groupByCookie);
        maxActiveCookieSize.ifPresent(
                size -> printMostActiveCookies(groupByCookie, size)
        );
    }

    private Map<String, Long> groupCookieForInputDate(LocalDate inputDate, List<CookieLog> cookieLogs) {
        return cookieLogs
                .stream()
                .filter(key -> inputDate.isEqual(key.getTimestamp().toLocalDate()))
                .collect(groupingBy(CookieLog::getCookie, counting()));
    }

    private OptionalLong getMaxActiveCookieSize(Map<String, Long> groupByCookie) {
        return groupByCookie
                .values()
                .stream()
                .mapToLong(count -> count)
                .max();
    }

    private void printMostActiveCookies(Map<String, Long> groupByCookie, long maxActiveCookieSize) {
        groupByCookie
                .entrySet()
                .stream()
                .filter(v -> v.getValue().equals(maxActiveCookieSize))
                .map(Entry::getKey)
                .forEach(System.out::println);
    }
}
