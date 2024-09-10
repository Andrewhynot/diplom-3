import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobjectmodels.ConstructorPageModel;
import webdriverfactory.WebDriverFactory;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;



public class ConstructorTest {

    private WebDriver driver;


    @Test
    @DisplayName("Проверка функционала переключения вкладок конструктора бургера. Булочки.")
    @Description("Проверяем, что при тапе на вкладку *Булочки* фокус перемещается на эту вкладку")
    public void constructorSwitchBunSections(){

        driver = WebDriverFactory.getWebDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        ConstructorPageModel constructorPageModel = new ConstructorPageModel(driver);

        constructorPageModel.getMainPage();

        constructorPageModel.clickSauseSection();

        constructorPageModel.clickBunSection();

        String result = constructorPageModel.getFocusedElementText();

        assertEquals("Булки", result);


    }

    @Test
    @DisplayName("Проверка функционала переключения вкладок конструктора бургера. Соусы")
    @Description("Проверяем, что при тапе на вкладку *Cоусы* фокус перемещается на эту вкладку")
    public void constructorSwitchSauseSections(){

        driver = WebDriverFactory.getWebDriver();

        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

        ConstructorPageModel constructorPageModel = new ConstructorPageModel(driver);

        constructorPageModel.getMainPage();

        constructorPageModel.clickSauseSection();

        String result = constructorPageModel.getFocusedElementText();

        assertEquals("Соусы", result);

    }

    @Test
    @DisplayName("Проверка функционала переключения вкладок конструктора бургера. Начинки")
    @Description("Проверяем, что при тапе на вкладку *Начинки* фокус перемещается на эту вкладку")
    public void constructorSwitchFillingSections(){

        driver = WebDriverFactory.getWebDriver();

        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

        ConstructorPageModel constructorPageModel = new ConstructorPageModel(driver);

        constructorPageModel.getMainPage();

        constructorPageModel.clickFillingSection();

        String result = constructorPageModel.getFocusedElementText();

        assertEquals("Начинки", result);

    }


    @After
    public void shutdown(){
        driver.quit();
    }



}
