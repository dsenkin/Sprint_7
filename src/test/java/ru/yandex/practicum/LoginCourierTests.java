package ru.yandex.practicum;

import io.qameta.allure.Description;
//import org.junit.jupiter.api.DisplayName;
import ru.yandex.practicum.model.CourierModel;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.steps.CourierSteps;

import static org.hamcrest.CoreMatchers.*;

public class LoginCourierTests extends BaseAPITest {

    CourierSteps courierSteps = new CourierSteps();
    private CourierModel courier;
    private String login;
    private String password;
    private String firstName;

    @Before
    public void setUp() {
        courier = new CourierModel();

        login = RandomStringUtils.randomAlphabetic(10);
        password = RandomStringUtils.randomAlphabetic(11);
        firstName = RandomStringUtils.randomAlphabetic(12);

        courier.setLogin(login);
        courier.setPassword(password);
        courier.setFirstName(firstName);

        CourierSteps.createCourier(courier);
    }

    //    курьер может авторизоваться
    //    успешный запрос возвращает id
    @Test
    //@DisplayName("Успешная авторизация курьера, возвращает id")
    @Description("Курьер может авторизоваться, при успешной авторизации выводится id")
    public void testSuccessLoginCourier() {
        courierSteps
                .login(courier)
                .statusCode(200)
                .body("id", notNullValue());
    }

    //    для авторизации нужно передать все обязательные поля;
    @Test
    //@DisplayName("Успешная авторизация курьера c использованием всех обязательных полей")
    @Description("Курьер может авторизоваться с использованием только логина и пароля")
    public void testSuccessLoginCourierWithAllRequiredFields() {
        courier.setFirstName(null);
        courierSteps
                .login(courier)
                .statusCode(200)
                .body("id", notNullValue());
    }

    //    система вернёт ошибку, если неправильно указать логин;
    @Test
    //@DisplayName("Ошибка при авторизация курьера, неправильно указан логин")
    @Description("Ошибка при попытке авторизации с неверным логином")
    public void testFailureLoginCourierWithWrongLogin() {
        courier.setLogin("wronglogin");
        courierSteps
                .login(courier)
                .statusCode(404)
                .body( "message", is("Учетная запись не найдена"));
        courier.setLogin(login);
    }

    //    система вернёт ошибку, если неправильно указать пароль;
    @Test
    //@DisplayName("Ошибка при авторизация курьера, неправильно указан пароль")
    @Description("Ошибка при попытке авторизации с неверным паролем")
    public void testFailureLoginCourierWithWrongPassword() {
        courier.setPassword("wrongpassword");
        courierSteps
                .login(courier)
                .statusCode(404)
                .body( "message", is("Учетная запись не найдена"));
        courier.setPassword(password);
    }

    //    система вернёт ошибку, если не указать логин;
    @Test
    //@DisplayName("Ошибка при авторизация курьера, не указан логин")
    @Description("Ошибка при попытке авторизации без логина")
    public void testFailureLoginCourierWithoutLogin() {
        courier.setLogin("");
        courierSteps
                .login(courier)
                .statusCode(400)
                .body( "message", is("Недостаточно данных для входа"));
        courier.setLogin(login);
    }

    //    система вернёт ошибку, если не указать пароль;
    @Test
    //@DisplayName("Ошибка при авторизация курьера, не указан пароль")
    @Description("Ошибка при попытке авторизации без пароля")
    public void testFailureLoginCourierWithoutPassword() {
        courier.setPassword("");
        courierSteps
                .login(courier)
                .statusCode(400)
                .body( "message", equalTo("Недостаточно данных для входа"));
        courier.setPassword(password);
    }

    @After
    public void tearDown() {
        try {
            Integer id = courierSteps
                    .login(courier)
                    .extract().body().path("id");
            courier.setId(id);
            courierSteps.deleteCourier(courier);
        } catch (Exception e) {

        }
    }
}
