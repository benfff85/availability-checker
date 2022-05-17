package com.benjaminleephoto.availabilitychecker.services;

import com.benjaminleephoto.availabilitychecker.models.Availability;
import com.benjaminleephoto.availabilitychecker.models.CheckerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static com.benjaminleephoto.availabilitychecker.models.Availability.*;

@Slf4j
@Component
public class RestTemplateCostoAvailabilityChecker implements AvailabilityChecker {

    private final RestTemplate restTemplate;
    private final String url = "https://www.costco.com/kirkland-signature-procare-with-dual-hmo's%2c-non-gmo-infant-formula---42-oz%2c-2-pack.product.100810650.html";

    public RestTemplateCostoAvailabilityChecker(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        log.info("Created RestTemplateCostcoAvailabilityChecker with URL: {}", url);
    }

    public CheckerResponse checkAvailability() {
        try {
            log.info("Running check via RestTemplateCostoAvailabilityChecker");
            String response = restTemplate.getForObject(url, String.class);
            assert response != null;
            Availability availability = response.contains("Out of Stock") ? NOT_AVAILABLE : AVAILABLE;
            return CheckerResponse.builder().availability(availability).url(url).build();
        } catch (Exception e) {
            log.error("Error while checking availability", e);
            return CheckerResponse.builder().availability(UNKNOWN).url(url).exception(e).build();
        }
    }
}
