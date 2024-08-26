import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobjectmodels.RegistrationPageModel;
import steps.UserSteps;
import webdriverfactory.WebDriverFactory;

import static org.hamcrest.CoreMatchers.is;


public class RegistrationPositiveTest {

    private UserSteps userSteps = new UserSteps();

    private String userName;
    private String userMail;
    private String password;
    private String token;
    private WebDriver driver;


    @Test
    @DisplayName("Позитивный сценарий регистрации пользователя через фронтенд.")
    @Description("Проверяем, что при длине пароля 6 и выше пользователь может успешно зарегистрироваться в системе")
    public void userRegistrationFrontSuccess(){

        driver = WebDriverFactory.getWebDriver();

        RegistrationPageModel registrationPageModel = new RegistrationPageModel(driver);

        userName = "user-naz-" + RandomStringUtils.randomAlphabetic(3).toLowerCase();
        userMail = "test-naz-" + RandomStringUtils.randomAlphabetic(3).toLowerCase() + "@yandex.ru";
        password = RandomStringUtils.randomAlphabetic(6);

        registrationPageModel.getRegistPage();
        registrationPageModel.fillRegistrationNameField(userName);
        registrationPageModel.fillRegistrationMailField(userMail);
        registrationPageModel.fillRegistrationPasswordField(password);
        registrationPageModel.completeRegistration();

        userSteps.userAuthorization(userMail, password)     // проверяем, что пользователь создан в БД и авторизация проиходит успешно.
                .then()
                .statusCode(200)
                .assertThat()
                .body("success", is(true))
                .body("user.email", is(userMail))
                .body("user.name", is(userName));

    }

    @After
    public void browserQuitAndDeleteUser() {

        driver.quit(); //закрываем браузер

        token = userSteps.userAuthorization(userMail, password) //выполняем авторизацию созданным через UI пользователем, сохраняем токен
                .then()
                .statusCode(200)
                .extract().path("accessToken");

        userSteps.userDelete(token); //удаляем созданного в системе пользователя
    }

}
