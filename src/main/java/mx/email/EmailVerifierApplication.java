package mx.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;



@SpringBootApplication
public class EmailVerifierApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailVerifierApplication.class, args);
    }

}
