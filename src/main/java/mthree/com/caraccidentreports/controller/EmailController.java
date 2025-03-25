package mthree.com.caraccidentreports.controller;

import mthree.com.caraccidentreports.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public String sendEmail(@RequestParam String to,
                            @RequestParam String subject,
                            @RequestParam String body) {
        emailService.sendSimpleEmail(to, subject, body);
        return "Email sent successfully to: " + to;
    }
}