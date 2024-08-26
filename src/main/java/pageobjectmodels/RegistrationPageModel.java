package pageobjectmodels;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static constants.Constants.*;

public class RegistrationPageModel {

    private final WebDriver driver;

    public RegistrationPageModel(WebDriver driver) {
        this.driver = driver;
    }

    //---------------------------------Локаторы страницы регистрации--------------------------------------------


    private final By registrationTitle = By.xpath("//h2[text()='Регистрация']");

    private final By registrationButton = By.xpath("//button[text()='Зарегистрироваться']");

    private final By registNameClickLabel = By.xpath("//label[text()='Имя']");

    private final By registEmailInputField = By.xpath("//label[text()='Email']");

    //поле инпута Имени или Почты после тапа, когда находится в фокусе
    private final By registNameOrEmailInputFocus = By.xpath("//div[contains(@class, 'input_status_active')]/input[@name='name']");

    private final By registPasswordInputField = By.xpath("//input[@type='password']");

    private final By errorMessage = By.xpath("//p[text()='Некорректный пароль']");

    private final By authorizationArticle = By.xpath("//h2[text()='Вход']");


    //---------------------------------Методы регистрации--------------------------------------------

    @Step("Открыть страницу регистрации пользователя приложения Stellar Burger")
    public void getRegistPage() {
        driver.get(REGISTER_URL);

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(registrationTitle));
    }

    @Step("Заполнить инпут Имя пользователя")
    public void fillRegistrationNameField(String name) {
       WebElement nameField = driver.findElement(registNameClickLabel);

      nameField.click();

       new WebDriverWait(driver, Duration.ofSeconds(6))
              .until(ExpectedConditions.visibilityOfElementLocated(registNameOrEmailInputFocus));

        WebElement inputName = driver.findElement(registNameOrEmailInputFocus);

        inputName.sendKeys(name);

    }

    @Step("Заполнить инпут Почта пользователя")
    public void fillRegistrationMailField(String mail) {
        WebElement mailField = driver.findElement(registEmailInputField);

        mailField.click();

       new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.visibilityOfElementLocated(registNameOrEmailInputFocus));

        WebElement inputMail = driver.findElement(registNameOrEmailInputFocus);

        inputMail.sendKeys(mail);
    }

    @Step("Заполнить инпут пароль пользователя")
    public void fillRegistrationPasswordField(String password) {

        WebElement passwordField = driver.findElement(registPasswordInputField);

        passwordField.sendKeys(password);

    }

    @Step("Нажать на кнопку Зарегистрироваться")
    public void completeRegistration() {

        WebElement registButton = driver.findElement(registrationButton);

        registButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(7))
                .until(ExpectedConditions.visibilityOfElementLocated(authorizationArticle));
    }

    @Step("Проверяем отображение ошибки валидации пароля по длине и проверяем, что не произошло перехода на страницу логина")
    public boolean validationPasswordErrorCheck() {

        WebElement errorMessageElement = driver.findElement(errorMessage);
        WebElement registrationTitleElement = driver.findElement(registrationTitle);

        WebElement registButton = driver.findElement(registrationButton);

        registButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(errorMessage));

        return errorMessageElement.isDisplayed() && registrationTitleElement.isDisplayed();

    }

}


