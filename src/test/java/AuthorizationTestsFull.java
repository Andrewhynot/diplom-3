import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobjectmodels.AutorizationPageModel;
import steps.UserSteps;
import webdriverfactory.WebDriverFactory;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertTrue;


public class AuthorizationTestsFull {

    private UserSteps userSteps = new UserSteps();

    private String email;
    private String password;
    private String token;
    private WebDriver driver;

    @Before
    public void userCreation(){

        email = "test-naz-" + RandomStringUtils.randomAlphabetic(3).toLowerCase() + "@yandex.ru";
        password = RandomStringUtils.randomAlphabetic(6);

        userSteps.userRegistration(email, password)
                .then()
                .statusCode(200)
                .assertThat()
                .body("success", is(true))
                .body("user.email", is(email))
                .body("user.name", is(email));
    }

    @Test
    @DisplayName("Позитивный сценарий авторизации пользователя по нажатию на кнопку *Войти в аккаунт*")
    @Description("Проверяем, что успешно можно перейти на экран авторизации через кнопку *Войти в аккаунт* и выполнить авторизацию")
    public void userAuthorizationLoginButton(){

        driver = WebDriverFactory.getWebDriver();

        AutorizationPageModel autorizationPageModel = new AutorizationPageModel(driver);

        autorizationPageModel.getMainPage();
        autorizationPageModel.clickLogInButton();
        autorizationPageModel.fillMailFieldAuthPage(email);
        autorizationPageModel.fillPasswordFieldAuthPage(password);

        boolean result = autorizationPageModel.finalAuthorization();

        assertTrue(result);

    }

    @Test
    @DisplayName("Позитивный сценарий авторизации пользователя по нажатию на кнопку *Личный кабинет*")
    @Description("Проверяем, что успешно можно перейти на экран авторизации через кнопку *Личный кабинет* и выполнить авторизацию")
    public void userAuthorizationAccountButton(){

        driver = WebDriverFactory.getWebDriver();

        AutorizationPageModel autorizationPageModel = new AutorizationPageModel(driver);

        autorizationPageModel.getMainPage();
        autorizationPageModel.clickAccountButton();
        autorizationPageModel.fillMailFieldAuthPage(email);
        autorizationPageModel.fillPasswordFieldAuthPage(password);

        boolean result = autorizationPageModel.finalAuthorization();

        assertTrue(result);

    }

    @Test
    @DisplayName("Позитивный сценарий авторизации пользователя по нажатию на кнопку *Войти* на странице регистрации")
    @Description("Проверяем, что успешно можно перейти на экран авторизации через кнопку *Войти* на странице регистрации и выполнить авторизацию")
    public void userAuthorizationRegistPage(){

        driver = WebDriverFactory.getWebDriver();

        AutorizationPageModel autorizationPageModel = new AutorizationPageModel(driver);

        autorizationPageModel.getRegistPage();
        autorizationPageModel.clickEnterButton();
        autorizationPageModel.fillMailFieldAuthPage(email);
        autorizationPageModel.fillPasswordFieldAuthPage(password);

        boolean result = autorizationPageModel.finalAuthorization();

        assertTrue(result);

    }


    @Test
    @DisplayName("Позитивный сценарий авторизации пользователя по нажатию на кнопку *Войти* на странице восстановления пароля")
    @Description("Проверяем, что успешно можно перейти на экран авторизации через кнопку *Войти* на странице восстановления и выполнить авторизацию")
    public void userAuthorizationRestorePage(){

        driver = WebDriverFactory.getWebDriver();

        AutorizationPageModel autorizationPageModel = new AutorizationPageModel(driver);

        autorizationPageModel.getRestorePage();
        autorizationPageModel.clickEnterButton();
        autorizationPageModel.fillMailFieldAuthPage(email);
        autorizationPageModel.fillPasswordFieldAuthPage(password);

        boolean result = autorizationPageModel.finalAuthorization();

        assertTrue(result);

    }


    @After
    public void browserQuitAndDeleteUser() {

        driver.quit(); //закрываем браузер

        token = userSteps.userAuthorization(email, password) //выполняем авторизацию созданным через UI пользователем, сохраняем токен
                .then()
                .statusCode(200)
                .extract().path("accessToken");

        userSteps.userDelete(token); //удаляем созданного в системе пользователя
    }

}