package ru.yandex.practicum.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.practicum.model.OrderModel;

import static io.restassured.RestAssured.given;

public class OrderSteps {

    public static String ORDERS = "/api/v1/orders";
    public static String CANCEL_ORDER = "/api/v1/orders/cancel";

    @Step("Получение списка заказов")
    public ValidatableResponse getOrdersList() {
        return given()
                .get(ORDERS)
                .then();
    }

    @Step("Создание заказа")
    public ValidatableResponse createOrder(OrderModel order) {
        return given()
                .body(order)
                .post(ORDERS)
                .then();
    }

    @Step("Отмена заказа с trackNumber = {trackNumber}")
    public ValidatableResponse cancelOrder(Integer trackNumber) {
        String requestBody = "{ \"track\": " + trackNumber + " }";
        return given()
                .body(requestBody)
                .when()
                .put(CANCEL_ORDER)
                .then();
    }
}
