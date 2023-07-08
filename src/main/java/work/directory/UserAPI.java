package work.directory;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static work.directory.Constants.*;

public class UserAPI {

    public UserAPI() {
        RestAssured.baseURI = API_URL;
    }

    @Step("Авторизация через API")
    public ValidatableResponse loginAPI(Login login) {
        return given().header("Content-type", "application/json")
                .and()
                .body(login)
                .when()
                .post(LOGIN)
                .then();
    }

    @Step("Регистрация через API")
    public ValidatableResponse registerUser(Login login) {
        return given().header("Content-type", "application/json")
                .and()
                .body(login)
                .when()
                .post(REGISTER)
                .then();
    }

    @Step("Удаление пользователя через API")
    public void deleteUser(String accessToken) {
        given() .header("Content-type", "application/json")
                .and()
                .header("authorization", accessToken)
                .delete(DELETE);
    }

}