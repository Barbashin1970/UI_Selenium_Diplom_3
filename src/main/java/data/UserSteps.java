package data;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import pojo.Authorization;
import pojo.User;

import static data.UniformResourceLocator.*;
import static io.restassured.RestAssured.given;

public class UserSteps {

    public RequestSpecification getSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URL)
                .build();
    }

    @Step("Создаём нового пользователя")
    public ValidatableResponse createUser(User user) {
        return given().log().all()
                .spec(getSpec())
                .body(user)
                .when()
                .post(CREATE_NEW_USER_URL)
                .then();
    }

    @Step("Авторизация пользователя по e-mail и паролю")
    public ValidatableResponse loginUser(Authorization authorization) {
        return given().log().all()
                .spec(getSpec())
                .body(authorization)
                .when()
                .post(LOGIN_USER_URL)
                .then();
    }

    @Step("Удаляем профиль пользователя по его токену")
    public ValidatableResponse deleteUserOld(String accessToken) {
        return given().log().all()
                .spec(getSpec())
                .auth().oauth2(accessToken)
                .when()
                .delete(USER_DATA_URL)
                .then();
    }
    @Step("Удаляем профиль пользователя по его токену")
    public void deleteUser(String accessToken) {
        given().log().all()
                .spec(getSpec())
                .auth().oauth2(accessToken)
                .when()
                .delete(USER_DATA_URL)
                .then();
    }
}
