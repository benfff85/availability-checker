package com.benjaminleephoto.availabilitychecker.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckerResponse {

    private Availability availability;
    private String url;
    private Exception exception;

}
