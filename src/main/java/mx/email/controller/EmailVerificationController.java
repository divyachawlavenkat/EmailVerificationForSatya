package mx.email.controller;

import mx.email.service.EmailVerificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailVerificationController {
    private final EmailVerificationService emailVerificationService;

    public EmailVerificationController(EmailVerificationService emailVerificationService) {
        this.emailVerificationService = emailVerificationService;
    }

    @GetMapping("/verify/{email}")
    public ResponseEntity<String> verifyEmail(@PathVariable String email) {
        boolean isValid = emailVerificationService.verifyEmail(email);

        if (isValid) {
            return ResponseEntity.ok("Email is valid.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is not valid.");
        }
    }
}
