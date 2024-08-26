package pageobjectmodels;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static constants.Constants.*;

public class AutorizationPageModel {

    private final WebDriver driver;

    public AutorizationPageModel(WebDriver driver) {
        this.driver = driver;
    }

    //---------------------------------Локаторы страницы авторизации--------------------------------------------

    private final By authorizationArticle = By.xpath("//h2[text()='Вход']");

    private final By authorizationButton = By.xpath("//button[text()='Войти']");

    private final By authEmailInputField = By.xpath("//input[@name='name']");

    private final By authPasswordInputField = By.xpath("//input[@type='password']");

    //заголовок страницы конструктора для идентификации страницы.
    private final By createBurgerSpoiler = By.xpath("//h1[text()='Соберите бургер']");

    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");

    //Локатор войти индентичен для страницы восстановления пароля и страницы регистрации пользователя !
    private final By loginButtonRegistrationAndRestorePage = By.xpath("//a[text()='Войти']");

    private final By privateAccountButton = By.xpath("//p[text()='Личный Кабинет']");

    //Кнопка у авторизованного пользователя
    private final By makeOrderButton = By.xpath("//button[text()='Оформить заказ']");

    private final By registrationTitle = By.xpath("//h2[text()='Регистрация']");

    //---------------------------------Локаторы страницы восстановления пароля------------------------------------------

    private final By restorationArticle = By.xpath("//h2[text()='Восстановление пароля']");


    //---------------------------------Методы авторизации--------------------------------------------

    @Step("Открыть главную страницу приложения Stellar Burger")
    public void getMainPage() {
        driver.get(MAIN_URL);

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(createBurgerSpoiler));
    }

    @Step("Открыть страницу регистрации пользователя приложения Stellar Burger")
    public void getRegistPage() {
        driver.get(REGISTER_URL);

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(registrationTitle));
    }

    @Step("Открыть страницу восстановления приложения Stellar Burger")
    public void getRestorePage() {
        driver.get(RESTORE_URL);

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(restorationArticle));
    }

    @Step("Нажать на кнопку *Войти* на странице регистрации/восстановления. (Переход на страницу авторизации)")
    public void clickEnterButton() {
        WebElement LogInButtonElement = driver.findElement(loginButtonRegistrationAndRestorePage);

        LogInButtonElement.click();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(authorizationArticle));
    }

    @Step("Нажать на кнопку *Войти в аккаунт* на странице Конструктора")
    public void clickLogInButton() {
        WebElement LogInButtonElement = driver.findElement(loginButton);

        LogInButtonElement.click();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(authorizationArticle));
    }

    @Step("Нажать на кнопку *Личный кабинет* на странице Конструктора")
    public void clickAccountButton() {
        WebElement accountButtonElement = driver.findElement(privateAccountButton);

        accountButtonElement.click();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(authorizationArticle));
    }

    @Step("Заполнить поле имейл на странице авторизации пользователя")
    public void fillMailFieldAuthPage(String email) {
        WebElement emailInputAuthPage = driver.findElement(authEmailInputField);

        emailInputAuthPage.sendKeys(email);
    }

    @Step("Заполнить поле пароль на странице авторизации пользователя")
    public void fillPasswordFieldAuthPage(String password) {
        WebElement passInputAuthPage = driver.findElement(authPasswordInputField);

        passInputAuthPage.sendKeys(password);
    }

    @Step("Нажать на кнопку Войти на странице авторизации. Проверяет, что пользователь перешел на страницу Конструктора как авторизованный")
    public boolean finalAuthorization() {
        WebElement enterButtonElement = driver.findElement(authorizationButton);

        enterButtonElement.click();

        WebElement orderButton = driver.findElement(makeOrderButton);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(makeOrderButton));

        return orderButton.isDisplayed();

    }

}
