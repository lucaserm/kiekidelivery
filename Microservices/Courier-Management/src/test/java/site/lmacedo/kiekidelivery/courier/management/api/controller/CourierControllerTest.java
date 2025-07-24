package site.lmacedo.kiekidelivery.courier.management.api.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import site.lmacedo.kiekidelivery.courier.management.domain.model.Courier;
import site.lmacedo.kiekidelivery.courier.management.domain.repository.CourierRepository;

import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CourierControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    private CourierRepository courierRepository;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
        RestAssured.basePath = "/api/v1/couriers";
    }

    @Test
    public void shouldReturn201() {
        String requestBody = """
        {
            "name": "João da Silva",
            "phone": "(11) 99999-9999"
        }
        """;

        RestAssured
            .given()
                .body(requestBody)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .post()
            .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", Matchers.notNullValue())
                .body("name", Matchers.equalTo("João da Silva"));
    }

    @Test
    public void shouldReturn200() {
        UUID courierId = courierRepository.saveAndFlush(
                Courier.brandNew("Maria da Silva", "(11) 98888-8888")
        ).getId();

        RestAssured
            .given()
                .pathParam("courierId", courierId)
                .accept(ContentType.JSON)
            .when()
                .get("/{courierId}")
            .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", Matchers.notNullValue())
                .body("name", Matchers.equalTo("Maria da Silva"))
                .body("phone", Matchers.equalTo("(11) 98888-8888"));
    }
}