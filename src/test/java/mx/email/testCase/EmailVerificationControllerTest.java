package mx.email.testCase;

import mx.email.controller.EmailVerificationController;
import mx.email.service.EmailVerificationService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmailVerificationController.class)
public class EmailVerificationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmailVerificationService emailVerificationService;

    @Test
    public void testVerifyEmail_ValidEmail_ReturnsValidResponse() throws Exception {
        String validEmail = "example@example.com";
        Mockito.when(emailVerificationService.verifyEmail(validEmail)).thenReturn(true);

        mockMvc.perform(get("/api/email/verify/{email}", validEmail)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Email is valid."));
    }

    @Test
    public void testVerifyEmail_InvalidEmail_ReturnsInvalidResponse() throws Exception {
        String invalidEmail = "invalid@example.com";
        Mockito.when(emailVerificationService.verifyEmail(invalidEmail)).thenReturn(false);

        mockMvc.perform(get("/api/email/verify/{email}", invalidEmail)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Email is not valid."));
    }
}
