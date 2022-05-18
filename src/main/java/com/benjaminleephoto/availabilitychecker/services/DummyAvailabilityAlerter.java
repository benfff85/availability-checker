package com.benjaminleephoto.availabilitychecker.services;

import com.benjaminleephoto.availabilitychecker.models.Availability;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
//@Component
public class DummyAvailabilityAlerter implements AvailabilityAlerter {

    @Override
    public void alert(Availability availability, String url) {
        log.info("Costco Baby Formula Availability: {} URL: {}", availability, url);
    }

}
