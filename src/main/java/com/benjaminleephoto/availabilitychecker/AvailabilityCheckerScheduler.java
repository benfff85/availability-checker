package com.benjaminleephoto.availabilitychecker;

import com.benjaminleephoto.availabilitychecker.models.CheckerResponse;
import com.benjaminleephoto.availabilitychecker.services.AvailabilityAlerter;
import com.benjaminleephoto.availabilitychecker.services.AvailabilityChecker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import static com.benjaminleephoto.availabilitychecker.models.Availability.NOT_AVAILABLE;

@Slf4j
@Configuration
@EnableScheduling
public class AvailabilityCheckerScheduler {

    private final AvailabilityChecker checker;
    private final AvailabilityAlerter alerter;

    public AvailabilityCheckerScheduler(AvailabilityChecker checker, AvailabilityAlerter alerter) {
        this.checker = checker;
        this.alerter = alerter;
    }

    @Scheduled(fixedDelayString = "${checker.poll-frequency-in-millis}")
    public void test() {
        log.info("Running scheduled availability check");
        CheckerResponse response = checker.checkAvailability();
        log.info("Availability: {}", response.getAvailability());
        if(NOT_AVAILABLE != response.getAvailability()) {
            alerter.alert(response.getAvailability(), response.getUrl());
        }
        // TODO pause logic
    }
}
