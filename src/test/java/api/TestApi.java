package api;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import java.util.Random;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

public class TestApi {
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-mesto.praktikum-services.ru";
    }

    @Test
    public void registrationAndAuth() {
        // Составили email
        Random random = new Random();
        String email = "something" + random.nextInt(10000000) + "@yandex.ru";
        String password = "12345";
        String json = "{\"email\": \"" + email + "\", \"password\": \"aaa\" }";// составь json, используя переменную email. Не забудь про экранизацию кавычек с помощью '/'


// POST запрос на регистрацию signup
        given()
                .header("Content-type", "application/json")
                .body(json)
                .when()
                .post("/api/signup")
                .then().assertThat().statusCode(201);

        Response response = given()
                .header("Content-type", "application/json")
                .body(json)
                .post("/api/signin");
        response.then().body("token", notNullValue()).and().statusCode(200);


    }
}



