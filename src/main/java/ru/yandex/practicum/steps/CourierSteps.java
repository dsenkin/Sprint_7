package ru.yandex.practicum.steps;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.practicum.model.CourierModel;
import static io.restassured.RestAssured.given;

public class CourierSteps {
    public static String CREATE_COURIER_ENDPOINT = "/api/v1/courier";
    public static String LOGIN_COURIER_ENDPOINT = "/api/v1/courier/login";
    public static String DELETE_COURIER_ENDPOINT = "/api/v1/courier/{id}";

    @Step("Создание курьера")
    public static ValidatableResponse createCourier(CourierModel courier){
        return given()
                .body(courier)
                .when().post(CREATE_COURIER_ENDPOINT)
                .then();
    }

    @Step("Логин курьера")
    public static ValidatableResponse login(CourierModel courier){
        return given()
                .body(courier)
                .when().post(LOGIN_COURIER_ENDPOINT)
                .then();
    }

    @Step("Удаление курьера")
    public static ValidatableResponse deleteCourier (CourierModel courier){
        return given()
                .pathParams("id", courier.getId())
                .when()
                .delete(DELETE_COURIER_ENDPOINT)
                .then();
    }
}
