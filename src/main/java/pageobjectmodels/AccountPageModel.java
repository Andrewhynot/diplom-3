package pageobjectmodels;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static constants.Constants.*;

public class AccountPageModel {

    private final WebDriver driver;

    public AccountPageModel(WebDriver driver) {
        this.driver = driver;
    }

    //---------------------------------Локаторы страницы личного кабинета--------------------------------------------

    private final By profileButton = By.xpath("//a[text()='Профиль']");

    private final By historyButton = By.xpath("//a[text()='История заказов']");

    private final By exitButton = By.xpath("//button[text()='Выход']");

    private final By accountSpoiler = By.xpath("//p[contains(text(),'изменить') and contains(text(), 'данные')]");

    private final By authorizationArticle = By.xpath("//h2[text()='Вход']");

    private final By authorizationButton = By.xpath("//button[text()='Войти']");

    private final By authEmailInputField = By.xpath("//input[@name='name']");

    private final By authPasswordInputField = By.xpath("//input[@type='password']");

    private final By privateAccountButton = By.xpath("//p[text()='Личный Кабинет']");

    //заголовок страницы конструктора для идентификации страницы.
    private final By createBurgerSpoiler = By.xpath("//h1[text()='Соберите бургер']");

    //Кнопка у авторизованного пользователя
    private final By makeOrderButton = By.xpath("//button[text()='Оформить заказ']");

    private final By constructorHeaderButton = By.xpath("//p[text()='Конструктор']");

    private final By mainAppLogo = By.xpath("//div/a");

    //---------------------------------Методы страницы личного кабинета--------------------------------------------

    @Step("Открыть страницу авторизации пользователя приложения Stellar Burger")
    public void getLoginPage() {

        driver.get(LOGIN_URL);

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

        new WebDriverWait(driver, Duration.ofSeconds(7))
                .until(ExpectedConditions.visibilityOfElementLocated(makeOrderButton));

        WebElement orderButton = driver.findElement(makeOrderButton);

        return orderButton.isDisplayed();

    }

    @Step("Перейти в личный кабинет будучи авторизованным в системе")
    public boolean getInPrivateAccount() {

        WebElement accountButtonElement = driver.findElement(privateAccountButton);

        accountButtonElement.click();

        new WebDriverWait(driver, Duration.ofSeconds(7))
                .until(ExpectedConditions.visibilityOfElementLocated(profileButton));

        WebElement profileButtonElement = driver.findElement(profileButton);

        WebElement historyButtonElement = driver.findElement(historyButton);

        WebElement exitButtonElement = driver.findElement(exitButton);

        WebElement accountSpoilerElement = driver.findElement(accountSpoiler);

        return (profileButtonElement.isDisplayed() && historyButtonElement.isDisplayed() && exitButtonElement.isDisplayed() && accountSpoilerElement.isDisplayed());

    }

    @Step("Нажать на логотип приложения")
    public boolean goToConstructorPageByLogoClick() {

        WebElement logo = driver.findElement(mainAppLogo);

        logo.click();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(makeOrderButton));

        WebElement orderButtonElement = driver.findElement(makeOrderButton);

        WebElement createSpoilerElement = driver.findElement(createBurgerSpoiler);

        return (orderButtonElement.isDisplayed() && createSpoilerElement.isDisplayed());

    }

    @Step("Нажать на вкладку Конструктор")
    public boolean goToConstructorPageBySectionClick() {

        WebElement constructorButton = driver.findElement(constructorHeaderButton);

        constructorButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(makeOrderButton));

        WebElement orderButtonElement = driver.findElement(makeOrderButton);

        WebElement createSpoilerElement = driver.findElement(createBurgerSpoiler);

        return (orderButtonElement.isDisplayed() && createSpoilerElement.isDisplayed());

    }

    @Step("Нажать на кнопку Выход")
    public boolean logoutByClickExitButton() {

        WebElement exitButtonElement = driver.findElement(exitButton);

        exitButtonElement.click();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(authorizationArticle));

        WebElement authHeader = driver.findElement(authorizationArticle);

        WebElement loginButtonElement = driver.findElement(authorizationButton);

        return (authHeader.isDisplayed() && loginButtonElement.isDisplayed());
    }
}
