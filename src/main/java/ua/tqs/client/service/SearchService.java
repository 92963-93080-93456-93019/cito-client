package ua.tqs.client.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;
import ua.tqs.client.utils.Environment;

import java.net.URI;

@Service
public class SearchService {

    @Autowired
    private RestTemplate restTemplate;


    public ResponseEntity<Object> getAllProductsForClient(Long clientId, String query) {
        URI url = new UriTemplate(Environment.CITO_PRODUCTS_API_URL).expand(clientId, 1, query);
        return restTemplate.getForEntity(url, Object.class);
    }
}
