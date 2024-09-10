import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobjectmodels.RegistrationPageModel;
import steps.UserSteps;
import webdriverfactory.WebDriverFactory;

import static org.junit.Assert.assertTrue;

public class RegistrationNegativeTest {

    private UserSteps userSteps = new UserSteps();

    private String userName;
    private String userMail;
    private String password;
    private WebDriver driver;


    @Test
    @DisplayName("Негативный сценарий регистрации пользователя через фронтенд.")
    @Description("Проверяем, что при длине пароля менее 6 символов пользователь НЕ может зарегистрироваться в системе")
    public void userRegistrationFrontError() {

        driver = WebDriverFactory.getWebDriver();

        RegistrationPageModel registrationPageModel = new RegistrationPageModel(driver);

        userName = "user-naz-" + RandomStringUtils.randomAlphabetic(3).toLowerCase();
        userMail = "test-naz-" + RandomStringUtils.randomAlphabetic(3).toLowerCase() + "@yandex.ru";
        password = RandomStringUtils.randomAlphabetic(4);

        registrationPageModel.getRegistPage();
        registrationPageModel.fillRegistrationNameField(userName);
        registrationPageModel.fillRegistrationPasswordField(password);
        registrationPageModel.fillRegistrationMailField(userMail);


        assertTrue(registrationPageModel.validationPasswordErrorCheck());  //проверяем отображение валидации поля Пароль на фронте + отсутствие редиректа на страницу авторизации

        userSteps.userAuthorization(userMail, password) // проверяем, что пользователь не был создан в БД и авторизация невозможна.
                .then()
                .statusCode(401);
    }

    @After
    public void shutdown(){
        driver.quit();
    }

}
