package com.benjaminleephoto.availabilitychecker.services;

import com.benjaminleephoto.availabilitychecker.models.Availability;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailAlerter implements AvailabilityAlerter {

    private final JavaMailSender sender;

    public EmailAlerter(JavaMailSender sender) {
        this.sender = sender;
    }

    @Override
    public void alert(Availability availability, String url) {
        log.info("Alerting via email");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@abc.com");
        message.setTo("ben.ferenchak@gmail.com", "4842744202@vmobl.com");
        message.setSubject("Costco Baby Formula Availability: " + availability);
        message.setText("Costco Baby Formula Availability: " + availability + "\nURL: " + url);
        sender.send(message);
    }

}
