package ru.yandex.practicum;
import io.qameta.allure.Description;
import org.junit.Test;
import ru.yandex.practicum.model.OrderModel;
import ru.yandex.practicum.steps.OrderSteps;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class OrdersListTests extends BaseAPITest {
    OrderSteps orderSteps = new OrderSteps();
    private OrderModel orderModel;

    //Проверь, что в тело ответа возвращается список заказов.
    @Test
    @Description("Проверка, что тело ответа не пустое, код ответа верный")
    public void ordersListTest() {
        orderSteps
                .getOrdersList()
                .statusCode(200)
                .body(is(notNullValue()));
    }
}
