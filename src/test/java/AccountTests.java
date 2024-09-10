import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobjectmodels.AccountPageModel;
import steps.UserSteps;
import webdriverfactory.WebDriverFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;

public class AccountTests {

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
    @DisplayName("Проверка входа авторизованного пользователя на страницу личного кабинета")
    @Description("Проверяем, что при выполнении авторизации пользотватель может перейти в личный кабинет, нажав на кнопку ЛК")
    public void enterIntoAccountPage(){
        driver = WebDriverFactory.getWebDriver();

        AccountPageModel accountPageModel = new AccountPageModel(driver);

        accountPageModel.getLoginPage();
        accountPageModel.fillMailFieldAuthPage(email);
        accountPageModel.fillPasswordFieldAuthPage(password);
        boolean result1 = accountPageModel.finalAuthorization();
        boolean result2 = accountPageModel.getInPrivateAccount();

        assertTrue(result1 && result2);

    }

    @Test
    @DisplayName("Проверка перехода из личного кабинета на страницу Конструктора бургера по логотипу в хэддере")
    @Description("Проверяем, что при нажатии на логотип приложения происходит переход на страницу Конструктора")
    public void returnFromAccountToConstructorByLogo(){
        driver = WebDriverFactory.getWebDriver();

        AccountPageModel accountPageModel = new AccountPageModel(driver);

        accountPageModel.getLoginPage();
        accountPageModel.fillMailFieldAuthPage(email);
        accountPageModel.fillPasswordFieldAuthPage(password);
        boolean result1 = accountPageModel.finalAuthorization();
        boolean result2 = accountPageModel.getInPrivateAccount();
        boolean result3 = accountPageModel.goToConstructorPageByLogoClick();

        assertTrue(result1 && result2 && result3);

    }

    @Test
    @DisplayName("Проверка перехода из личного кабинета на страницу Конструктора бургера по нажатии на вкладку Конструктор")
    @Description("Проверяем, что при нажатии на вкладку Конструктор происходит переход на страницу Конструктора")
    public void returnFromAccountToConstructorBySection(){
        driver = WebDriverFactory.getWebDriver();

        AccountPageModel accountPageModel = new AccountPageModel(driver);

        accountPageModel.getLoginPage();
        accountPageModel.fillMailFieldAuthPage(email);
        accountPageModel.fillPasswordFieldAuthPage(password);
        boolean result1 = accountPageModel.finalAuthorization();
        boolean result2 = accountPageModel.getInPrivateAccount();
        boolean result3 = accountPageModel.goToConstructorPageBySectionClick();

        assertTrue(result3);


    }

    @Test
    @DisplayName("Проверка возможности выхода из личного аккаунта")
    @Description("Проверяем, при нажатии на кнопку Выход мы разлогиниваемся из приложения")
    public void logoutFromAccount(){
        driver = WebDriverFactory.getWebDriver();

        AccountPageModel accountPageModel = new AccountPageModel(driver);

        accountPageModel.getLoginPage();
        accountPageModel.fillMailFieldAuthPage(email);
        accountPageModel.fillPasswordFieldAuthPage(password);
        boolean result1 = accountPageModel.finalAuthorization();
        boolean result2 = accountPageModel.getInPrivateAccount();
        boolean result3 = accountPageModel.logoutByClickExitButton();

        assertTrue(result1 && result2 && result3);

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


/*

ПЕРЕХОД В ЛИЧНЫЙ КАБИНЕТ
1)Проверь переход по клику на «Личный кабинет».

ПЕРЕХОД ИЗ ЛИЧНОГО КАБИНЕТА В КОНСТРУКТОР
1) Проверь переход по клику на «Конструктор» и
2) на логотип Stellar Burgers


ВЫХОД ИЗ АККАУНТА
1) Проверь выход по кнопке «Выйти» в личном кабинете.


========================================================
 Проверь переход по клику на «Личный кабинет».

 @before
 через апи зарегистрировать юзера в системе.
 открыть страницу конструктора
Ожидание
Проверить отображение Собрать бургер
нажать на кнопку войти в аккаунт
Проверить отображение заголорвка ВХОД
Нажать на поле имейл
ввести сгенерированный мейл
нажать на поле пароль
ввести сгенерированный пароль
Нажать на кнопку Войти
Ожидание
Проверить что отображается кнопка оформить заказ

@кейс

нажать на кнопку войти в аккаунт
ожидание
проверить отображение accountSpoiler By.xpath("//p[contains(text(),'изменить') and contains(text(), 'данные')]");
проверить отображение profileButton = By.xpath("//a[text()='Профиль']");
проверить отображение historyButton


@after
Авторизоваться через АПИ, выдернуть токен, удалить пользователя.


private final By profileButton = By.xpath("//a[text()='Профиль']");

    private final By historyButton = By.xpath("//a[text()='История заказов']");

    private final By exitButton = By.xpath("//button[text()='Выход']");

    private final By accountSpoiler = By.xpath("//p[contains(text(),'изменить') and contains(text(), 'данные')]");

    private final By nameInput = By.name("Name");

    private final By loginInput = By.xpath("//ul[contains(@class, 'Profile')]//li[2]//input");

========================================================
 Проверь переход по клику на «Конструктор»

@before
 через апи зарегистрировать юзера в системе.
 открыть страницу конструктора
Ожидание
Проверить отображение Собрать бургер
нажать на кнопку войти в аккаунт
Проверить отображение заголорвка ВХОД
Нажать на поле имейл
ввести сгенерированный мейл
нажать на поле пароль
ввести сгенерированный пароль
Нажать на кнопку Войти
Ожидание
Проверить что отображается кнопка оформить заказ
нажать на кнопку войти в аккаунт
ожидание
проверить отображение accountSpoiler By.xpath("//p[contains(text(),'изменить') and contains(text(), 'данные')]");
проверить отображение profileButton = By.xpath("//a[text()='Профиль']");
проверить отображение historyButton

@case
Нажать на кнопку Конструктор.
Проверить отображение заголовка собрать бургер
проверить отображение кнопки оформить заказ


@after
Авторизоваться через АПИ, выдернуть токен, удалить пользователя.

========================================================
на логотип Stellar Burgers

@before
 через апи зарегистрировать юзера в системе.
 открыть страницу конструктора
Ожидание
Проверить отображение Собрать бургер
нажать на кнопку войти в аккаунт
Проверить отображение заголорвка ВХОД
Нажать на поле имейл
ввести сгенерированный мейл
нажать на поле пароль
ввести сгенерированный пароль
Нажать на кнопку Войти
Ожидание
Проверить что отображается кнопка оформить заказ
нажать на кнопку войти в аккаунт
ожидание
проверить отображение accountSpoiler By.xpath("//p[contains(text(),'изменить') and contains(text(), 'данные')]");
проверить отображение profileButton = By.xpath("//a[text()='Профиль']");
проверить отображение historyButton

@case
Нажать на Логотип.
Проверить отображение заголовка собрать бургер
проверить отображение кнопки оформить заказ


@after
Авторизоваться через АПИ, выдернуть токен, удалить пользователя.

========================================================
 Проверь выход по кнопке «Выйти» в личном кабинете.

 @before
 через апи зарегистрировать юзера в системе.
 открыть страницу конструктора
Ожидание
Проверить отображение Собрать бургер
нажать на кнопку войти в аккаунт
Проверить отображение заголорвка ВХОД
Нажать на поле имейл
ввести сгенерированный мейл
нажать на поле пароль
ввести сгенерированный пароль
Нажать на кнопку Войти
Ожидание
Проверить что отображается кнопка оформить заказ
нажать на кнопку войти в аккаунт
ожидание
проверить отображение accountSpoiler By.xpath("//p[contains(text(),'изменить') and contains(text(), 'данные')]");
проверить отображение profileButton = By.xpath("//a[text()='Профиль']");
проверить отображение historyButton


@case
Нажать на кнопку Выйти.
Проверить отображение заголовка Вход
проверить отображение кнопки Войти

========================================================
 */