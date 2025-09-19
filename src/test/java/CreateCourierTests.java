import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class CreateCourierTests extends BaseAPITest{

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    //курьера можно создать;
    @Test
    public void testCreateCourier() {
        RestAssured.given().post("/api/v1/courier").then().assertThat().statusCode(201);
    }

    //    нельзя создать двух одинаковых курьеров;
    @Test
    public void testCannotCreateTwoIdenticalCouriers() {

    }

    //    чтобы создать курьера, нужно передать в ручку все обязательные поля;
    @Test
    public void testCreateCourierWithAllRequiredFields() {

    }

    //    запрос возвращает правильный код ответа;
    @Test
    public void testCreateCourierReturnsRightRequestCode() {

    }
    //    успешный запрос возвращает ok: true;
    @Test
    public void testCreateCourierReturnsOkTrue() {

    }

    //    если одного из полей нет, запрос возвращает ошибку;
    @Test
    public void testCreateCourierWithoutOneOfRequiredFieldsReturnsError() {

    }

    //    если создать пользователя с логином, который уже есть, возвращается ошибка.
    @Test
    public void testCreateCourierWithExistingLoginReturnsError() {

    }

    @After
    public void tearDown() {


    }
}
