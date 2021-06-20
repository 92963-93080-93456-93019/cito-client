package ua.tqs.client.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.greaterThan;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClientRestControllerITest {
    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void setup() {
        mockMvc(mvc);
    }

    @Test
    public void whenRegisterOrder_thenReturnCreated() {

        String body = "{\"products\":[{\"id\":3,\"quantity\":2},{\"id\":4,\"quantity\":1}],\"info\":{\"appid\":1,\"userId\":1,\"deliveryAddress\":\"Rua das Batatas\",\"deliverInPerson\":true,\"latitude\":50,\"longitude\":50}}";


        given()
                .contentType("application/json")
                .body(body).post("clientApi/1/orders")
                .then()
                .assertThat()
                .statusCode(201);

    }

    @Test
    public void whenGetAllProducts_thenReturnOK() {

         given()
                .get("clientApi/1/products")
                .then()
                .assertThat()
                .and().statusCode(200)
                .and().body("size()",greaterThan(0));
    }

}