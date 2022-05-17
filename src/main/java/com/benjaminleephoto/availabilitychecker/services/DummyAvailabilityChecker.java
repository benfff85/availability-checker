package com.benjaminleephoto.availabilitychecker.services;

import com.benjaminleephoto.availabilitychecker.models.CheckerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.benjaminleephoto.availabilitychecker.models.Availability.AVAILABLE;

@Slf4j
//@Component
public class DummyAvailabilityChecker implements AvailabilityChecker{

    @Override
    public CheckerResponse checkAvailability() {
        log.info("Running check via DummyAvailabilityChecker");
        return CheckerResponse.builder().availability(AVAILABLE).url("https://www.costco.com").build();
    }

}
