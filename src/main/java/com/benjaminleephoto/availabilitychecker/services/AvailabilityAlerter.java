package com.benjaminleephoto.availabilitychecker.services;

import com.benjaminleephoto.availabilitychecker.models.Availability;

public interface AvailabilityAlerter {
    void alert(Availability availability, String url);

}
