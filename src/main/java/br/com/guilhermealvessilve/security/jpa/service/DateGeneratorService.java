package br.com.guilhermealvessilve.security.jpa.service;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@ApplicationScoped
public class DateGeneratorService {

    private static final int MAX_GENERATION_DATE = 50;

    public String generateDate() {
        final var year = new Random().nextInt(MAX_GENERATION_DATE);
        return DateTimeFormatter.ISO_DATE
                .format(LocalDate.now()
                .minusYears(year));
    }

}
