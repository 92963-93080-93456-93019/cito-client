package ua.tqs.client.controller;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.tqs.client.service.OrderService;
import ua.tqs.client.service.SearchService;

@Tag(name = "Client Deliveries", description = "the Client Deliveries API")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/clientApi")
public class ClientRestController {

    @Autowired
    private SearchService searchService;

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "{clientId}/orders", produces = MediaType.APPLICATION_JSON_VALUE   )
    public ResponseEntity<Object> registerOrder(@PathVariable Long clientId, @RequestBody JsonNode payload) {
        return orderService.registerOrder(clientId, payload);
    }

    @Operation(summary = "Gets all products.")
    @GetMapping(value = "{clientId}/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllProductsForClient(@PathVariable Long clientId, String query) {
        return searchService.getAllProductsForClient(clientId, query);
    }
}
