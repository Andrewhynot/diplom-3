package pageobjectmodels;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static constants.Constants.MAIN_URL;

public class ConstructorPageModel {

    private final WebDriver driver;

    public ConstructorPageModel(WebDriver driver) {
        this.driver = driver;
    }


    //---------------------------------Локаторы основной страницы------------------------------------------

    //Кнопка у авторизованного пользователя
    private final By makeOrderButton = By.xpath("//button[text()='Оформить заказ']");

    private final By createBurgerSpoiler = By.xpath("//h1[text()='Соберите бургер']"); //заголовок страницы для идентификации страницы.

    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");

    private final By privateAccountButton = By.xpath("//p[text()='Личный Кабинет']");

    private final By constructorHeaderButton = By.xpath("//p[text()='Конструктор']");

    private final By mainAppLogo = By.xpath("//div/a");

    private final By accountHeaderButton = By.xpath("//p[text()='Личный Кабинет']");

    private final By loginButtonConstructorPage = By.xpath("//button[text()='Войти в аккаунт']");  //Кнопка у неавторизованного пользователя

    private final By bunSection = By.xpath("//div/span[text()='Булки']");

    private final By sauceSection = By.xpath("//div/span[text()='Соусы']");

    private final By fillingSection = By.xpath("//div/span[text()='Начинки']");

    private final By focusedSection = By.xpath("//div[contains(@class, 'current')]/span");


    //---------------------------------Методы основной страницы------------------------------------------

    @Step("Открыть главную страницу приложения Stellar Burger")
    public void getMainPage() {

        driver.get(MAIN_URL);

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(createBurgerSpoiler));

    }


    @Step("Нажать на вкладку *Булки*")
    public void clickBunSection(){
        WebElement bunSectionElement = driver.findElement(bunSection);

        bunSectionElement.click();

    }

    @Step("Нажать на вкладку *Соусы*")
    public void clickSauseSection(){
        WebElement sauseSectionElement = driver.findElement(sauceSection);

        sauseSectionElement.click();

    }


    @Step("Нажать на вкладку *Начинки*")
    public void clickFillingSection(){
        WebElement fillingSectionElement = driver.findElement(fillingSection);

        fillingSectionElement.click();



    }


    @Step("Вытащить текст вкладки в фокусе")
    public String getFocusedElementText(){
        WebElement focusedSectionElement = driver.findElement(focusedSection);

        return focusedSectionElement.getText();

    }

}
