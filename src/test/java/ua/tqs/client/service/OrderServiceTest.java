package ua.tqs.client.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import static org.assertj.core.api.Assertions.assertThat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class OrderServiceTest {

    @Mock(lenient = true)
    private RestTemplate restTemplate;

    @InjectMocks
    private OrderService orderService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void whenRegisterOrders_ReturnOK() throws JsonProcessingException {
        String body = "{\"products\":[{\"id\":3,\"quantity\":2},{\"id\":4,\"quantity\":1}],\"info\":{\"appid\":1,\"userId\":1,\"deliveryAddress\":\"Rua das Batatas\",\"deliverInPerson\":true,\"latitude\":50,\"longitude\":50}}";
        JsonNode bodyObj = objectMapper.readTree(body);
        String response = "{\"code\" : 201, \"message\" : \"Order saved.\"}";
        when(restTemplate.postForEntity(any(), any(), any())).thenReturn(new ResponseEntity<>(response, HttpStatus.CREATED));

        var order = orderService.registerOrder(1L, bodyObj);

        assertThat(order.getStatusCode()).isSameAs(HttpStatus.CREATED);
        assertThat(order.getBody()).isEqualTo(response);

        verify(restTemplate, VerificationModeFactory.times(1)).postForEntity(any(), any(), any());

    }

}