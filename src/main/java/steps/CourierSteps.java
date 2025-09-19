package steps;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import model.CourierModel;

import static io.restassured.RestAssured.given;

public class CourierSteps {
    public static String CREATE_COURIER_ENDPOINT = "/api/v1/courier";
    public static String LOGIN_COURIER_ENDPOINT = "/api/v1/courier/login";
    public static String DELETE_COURIER_ENDPOINT = "/api/v1/courier/:id";

    public static Response createCourier (CourierModel courierModel){
        return given().log().all()
                .contentType(ContentType.JSON)
                .body(courierModel)
                .when().post(CREATE_COURIER_ENDPOINT)
                .then()
                .log().all()
                .extract().response();
    }
}
