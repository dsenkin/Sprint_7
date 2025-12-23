package ru.yandex.practicum;

import io.qameta.allure.Description;
//import org.junit.jupiter.api.DisplayName;
import ru.yandex.practicum.model.CourierModel;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.steps.CourierSteps;

import static org.hamcrest.CoreMatchers.is;

public class CreateCourierTests extends BaseAPITest {
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
    }

    //курьера можно создать, передаем все поля, код ответа верный;
    @Test
    @Description("Проверка успешного создания курьера при передаче всех полей, код ответа верный")
    public void testSuccessCreateCourier() {
        courierSteps
                .createCourier(courier)
                .statusCode(201)
                .body("ok", is(true));
    }

    //нельзя создать двух одинаковых курьеров;
    @Test
    @Description("Проверка ошибки создания курьера при использовании существующего логина, код ответа отличается (Этот логин уже используется. Попробуйте другой.) вместо (Этот логин уже используется)")
    public void testErrorCreateCourierWithExistingLogin() {
        courierSteps
                .createCourier(courier);
        courier.setPassword(RandomStringUtils.randomAlphabetic(11));
        courier.setFirstName(RandomStringUtils.randomAlphabetic(12));
        courierSteps
                .createCourier(courier)
                .statusCode(409)
                .body("message", is("Этот логин уже используется. Попробуйте другой."));
        courier.setPassword(password);
    }

    //курьера можно создать, передаем только обязательные поля, код ответа верный;
    @Test
    @Description("Проверка успешного создания курьера при передаче только обязательных полей, код ответа верный")
    public void testSuccessCreateCourierWithAllRequiredFields() {
        courier.setFirstName(null);
        courierSteps
                .createCourier(courier)
                .statusCode(201)
                .body("ok", is(true));
    }

    //если одного из полей(логин) нет, запрос возвращает ошибку;
    @Test
    @Description("Проверка ошибки при создании курьера без логина, код ответа верный")
    public void testErrorCreateCourierWithoutLogin() {
        courier.setLogin("");
        courierSteps
                .createCourier(courier)
                .statusCode(400)
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }

    //если одного из полей(пароль) нет, запрос возвращает ошибку.
    @Test
    @Description("Проверка ошибки при создании курьера без пароля, код ответа верный")
    public void testErrorCreateCourierWithoutPassword() {
        courier.setPassword("");
        courierSteps
                .createCourier(courier)
                .statusCode(400)
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }

    @After
    public void tearDown() {
        try {
             Integer id = courierSteps
                .login(courier)
                .extract().body().path("id");
             courier.setId(id);
             courierSteps.deleteCourier(courier);
        }
        catch (Exception e) {
            //e.printStackTrace();
        }
    }
}
