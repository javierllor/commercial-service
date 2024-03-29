package com.gft.commercial.e2e;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class PriceE2ETest {

    @Test
    @DisplayName("Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
    public void test1Success() {
        get("/prices/1/35455/2020-06-14-10.00.00").then().statusCode(200).assertThat()
                .body(
                        "brandId", equalTo(1),
                        "productId", equalTo(35455),
                        "priceList", equalTo(1),
                        "startDate", equalTo("2020-06-14-00.00.00"),
                        "endDate", equalTo("2020-12-31-23.59.59"),
                        "finalPrice", equalTo("35.5 EUR")
                );
    }

    @Test
    @DisplayName("Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
    public void test2Success() {
        get("/prices/1/35455/2020-06-14-16.00.00").then().statusCode(200).assertThat()
                .body(
                        "brandId", equalTo(1),
                        "productId", equalTo(35455),
                        "priceList", equalTo(2),
                        "startDate", equalTo("2020-06-14-15.00.00"),
                        "endDate", equalTo("2020-06-14-18.30.00"),
                        "finalPrice", equalTo("25.45 EUR")
                );
    }

    @Test
    @DisplayName("Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
    public void test3Success() {
        get("/prices/1/35455/2020-06-14-21.00.00").then().statusCode(200).assertThat()
                .body(
                        "brandId", equalTo(1),
                        "productId", equalTo(35455),
                        "priceList", equalTo(1),
                        "startDate", equalTo("2020-06-14-00.00.00"),
                        "endDate", equalTo("2020-12-31-23.59.59"),
                        "finalPrice", equalTo("35.5 EUR")
                );
    }

    @Test
    @DisplayName("Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)")
    public void test4Success() {
        get("/prices/1/35455/2020-06-15-10.00.00").then().statusCode(200).assertThat()
                .body(
                        "brandId", equalTo(1),
                        "productId", equalTo(35455),
                        "priceList", equalTo(3),
                        "startDate", equalTo("2020-06-15-00.00.00"),
                        "endDate", equalTo("2020-06-15-11.00.00"),
                        "finalPrice", equalTo("30.5 EUR")
                );
    }

    @Test
    @DisplayName("Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)")
    public void test5Success() {
        get("/prices/1/35455/2020-06-16-21.00.00").then().statusCode(200).assertThat()
                .body(
                        "brandId", equalTo(1),
                        "productId", equalTo(35455),
                        "priceList", equalTo(4),
                        "startDate", equalTo("2020-06-15-16.00.00"),
                        "endDate", equalTo("2020-12-31-23.59.59"),
                        "finalPrice", equalTo("38.95 EUR")
                );
    }

    @Test
    @DisplayName("Price not found")
    public void notFound() {
        get("/prices/99/35455/2020-06-16-21.00.00").then().statusCode(404).assertThat()
                .body(
                        "errorCode", equalTo("NOT_FOUND"),
                        "message", equalTo("Price not found.")
                );
    }

    @Test
    @DisplayName("Invalid Brand Id")
    public void wrongBrandId() {
        get("/prices/invalid/35455/2020-06-16-21.00.00").then().statusCode(400).assertThat()
                .body(
                        "errorCode", equalTo("VALIDATION_ERROR"),
                        "message", equalTo("Wrong Brand Id: For input string: \"invalid\"")
                );
    }

    @Test
    @DisplayName("Invalid Product Id")
    public void wrongProductId() {
        get("/prices/1/invalid/2020-06-16-21.00.00").then().statusCode(400).assertThat()
                .body(
                        "errorCode", equalTo("VALIDATION_ERROR"),
                        "message", equalTo("Wrong Product Id: For input string: \"invalid\"")
                );
    }

    @Test
    @DisplayName("Invalid Date")
    public void wrongDate() {
        get("/prices/1/35455/invalid").then().statusCode(400).assertThat()
                .body(
                        "errorCode", equalTo("VALIDATION_ERROR"),
                        "message", equalTo("Wrong Date: Unparseable date: \"invalid\"")
                );
    }

    @Test
    @DisplayName("Invalid All Parameters")
    public void wrongAllParameters() {
        get("/prices/invalid/invalid/invalid").then().statusCode(400).assertThat()
                .body(
                        "errorCode", equalTo("VALIDATION_ERROR"),
                        "message",
                        equalTo("Wrong Brand Id: For input string: \"invalid\". Wrong Product Id: For input string: \"invalid\". Wrong Date: Unparseable date: \"invalid\"")
                );
    }
}
