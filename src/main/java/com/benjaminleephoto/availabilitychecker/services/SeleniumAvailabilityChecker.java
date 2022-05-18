package com.benjaminleephoto.availabilitychecker.services;

import com.benjaminleephoto.availabilitychecker.models.Availability;
import com.benjaminleephoto.availabilitychecker.models.CheckerResponse;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;

import static com.benjaminleephoto.availabilitychecker.models.Availability.*;

@Slf4j
@Component
public class SeleniumAvailabilityChecker implements AvailabilityChecker {

    private final ChromeDriver driver;
    private static final String URL = "https://www.costco.com/kirkland-signature-procare-with-dual-hmo's%2c-non-gmo-infant-formula---42-oz%2c-2-pack.product.100810650.html";

    public SeleniumAvailabilityChecker(ChromeDriver driver) {
        this.driver = driver;
    }

    @Override
    public CheckerResponse checkAvailability() {
        log.info("Checking availability via Selenium");
        try {
            driver.get(URL);
            WebElement overlay = driver.findElementByXPath("//*[@id='productImageOverlay']/img[1]");
            Availability availability = overlay.isDisplayed() ? NOT_AVAILABLE : AVAILABLE;
            return CheckerResponse.builder().availability(availability).url(URL).build();
        } catch (Exception e) {
            log.error("Error while checking availability", e);
            return CheckerResponse.builder().availability(UNKNOWN).url(URL).exception(e).build();
        }
    }
}
