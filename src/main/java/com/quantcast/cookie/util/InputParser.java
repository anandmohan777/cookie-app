package com.quantcast.cookie.util;

import com.opencsv.bean.CsvToBeanBuilder;
import com.quantcast.cookie.exception.ServiceException;
import com.quantcast.cookie.model.CookieLog;
import com.quantcast.cookie.model.Input;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.nio.file.Files.newBufferedReader;
import static java.nio.file.Paths.get;
import static java.time.LocalDate.parse;

public class InputParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(InputParser.class);

    public static Input parseInput(String[] args) throws ServiceException {
        final Options options = getOptions();
        final CommandLineParser parser = new DefaultParser();
        final Input input = new Input();

        try {
            CommandLine cmd = parser.parse(options, args);
            input.setFileName(cmd.getOptionValue("file"));
            input.setDate(parse(cmd.getOptionValue("date")));
            return input;
        } catch (ParseException e) {
            LOGGER.error(e.getMessage());
            printHelp(options);
            throw new ServiceException(e);
        }
    }

    public static List<CookieLog> readCsvCookieLog(String fileName) throws ServiceException {
        try {
            return new CsvToBeanBuilder<CookieLog>(newBufferedReader(get(fileName)))
                    .withType(CookieLog.class)
                    .build()
                    .parse();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }
    }

    private static Options getOptions() {
        final Options options = new Options();

        final Option input = new Option("f", "file", true, "input file path");
        input.setRequired(true);
        options.addOption(input);

        final Option output = new Option("d", "date", true, "date");
        output.setRequired(true);
        options.addOption(output);
        return options;
    }

    private static void printHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("help", options);
    }
}
