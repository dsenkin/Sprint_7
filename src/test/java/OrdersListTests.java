import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static io.restassured.RestAssured.given;

public class OrdersListTests {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @Test
    public void ordersListTest() {
        //Проверь, что в тело ответа возвращается список заказов.
        Response response = given()
                //.baseUri("https://qa-scooter.praktikum-services.ru/")
                .get("/api/v1/orders");
        System.out.println(response.body().asString());
    }
}
