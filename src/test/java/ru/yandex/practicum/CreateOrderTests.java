package ru.yandex.practicum;

import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.AfterParam;
import ru.yandex.practicum.data.OrderTestData;
import ru.yandex.practicum.model.OrderModel;
import ru.yandex.practicum.steps.OrderSteps;
import java.util.List;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

@RunWith(Parameterized.class)
public class CreateOrderTests extends BaseAPITest {
    private OrderSteps orderSteps = new OrderSteps();
    private ValidatableResponse response;
    private Integer trackNumber;

    private List<String> color;

    public CreateOrderTests(List<String> color) {
        this.color = color;
    }

    @Parameterized.Parameters(name = "Цвет самоката - {0}")
    public static Object[][] dataGen() {
        return new Object[][]{
                {List.of("BLACK")},
                {List.of("GREY")},
                {List.of("BLACK", "GREY")},
                {List.of()}
        };
    }

    @Test
    @Description("Проверка успешного создания заказа с разными цветами самоката")
    public void createOrderTest() {
        OrderModel order = new OrderTestData();
        order.setColor(color);
        response = orderSteps.
                createOrder(order)
                .statusCode(201)
                .body("track", is(instanceOf(Integer.class)));
        trackNumber = response.extract().path("track");
        int a=0;
    }

    @After
    public void tearDown() {
        if (trackNumber != null) {
            try {
                orderSteps.cancelOrder(trackNumber);
            }
            catch (Exception ignored) {
            }
        }
    }
}