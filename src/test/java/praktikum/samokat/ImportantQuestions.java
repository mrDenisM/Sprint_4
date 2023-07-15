package praktikum.samokat;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import praktikum.samokat.pom.SamokatPage;

import java.util.concurrent.TimeUnit;

//Параметризованный тест
//Аннотация и раннер
@RunWith(Parameterized.class)
public class ImportantQuestions {
    private WebDriver webDriver;
    private final String idQuestionText;
    private final String expectedText;
    private final String idText;

    //Конструктор
    public ImportantQuestions(String idQuestionText, String expectedText, String idText) {
        this.idQuestionText = idQuestionText;
        this.expectedText = expectedText;
        this.idText = idText;
    }

    @Parameterized.Parameters
    public static Object[][] getQuestionText() {
        return new Object[][] {
                {"accordion__heading-0", "Сутки — 400 рублей. Оплата курьеру — наличными или картой.", "accordion__panel-0"},
                {"accordion__heading-1",
                        "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
                        "accordion__panel-1"},
                {"accordion__heading-2",
                        "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
                        "accordion__panel-2"},
                {"accordion__heading-3",
                        "Только начиная с завтрашнего дня. Но скоро станем расторопнее.", "accordion__panel-3"},
                {"accordion__heading-4",
                        "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
                        "accordion__panel-4"},
                {"accordion__heading-5",
                        "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
                        "accordion__panel-5"},
                {"accordion__heading-6",
                        "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
                        "accordion__panel-6"},
                {"accordion__heading-7",
                        "Да, обязательно. Всем самокатов! И Москве, и Московской области.", "accordion__panel-7"},
        };
    }

    @Before
    public void setUp(){
        //webDriver = new FirefoxDriver();
        webDriver = new ChromeDriver();

        webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

    @Test
    public void howMuchDoesItCost(){
        SamokatPage samokatPage = new SamokatPage(webDriver);

        samokatPage
                .openSite()
                .scrollToTheEndPage()
                .clickQuestionText(idQuestionText);

        //Проверяем, что текст виден после нажатия на кнопку вопроса
        Assert.assertTrue("Поле закрыто",
                webDriver.findElements(By.id(idQuestionText)).size() > 0);

        //Сравниваем, что ожидаемый текст и актуальный совпадают
        Assert.assertEquals("Тексты не соответствуют друг другу", expectedText, samokatPage.argActualText(idText));
    }

    @After
    public void teardown(){
        webDriver.quit();
    }
}