package praktikum.samokat;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import praktikum.samokat.pom.CustomerInfo;
import praktikum.samokat.pom.RentSamokatInfo;
import praktikum.samokat.pom.SamokatPage;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;

@RunWith(Parameterized.class)
public class OrderAScooter {
    private WebDriver webDriver;
    private final String xpathButton;
    private final String nameCustomer;
    private final String surnameCustomer;
    private final String addressCustomer;
    private final String xpathNameStation;
    private final String phoneCustomer;
    private final String dateRent;
    private final String rentalPeriod;
    private final String chekBoxColour;
    private final String сommentToCourier;

    //Конструктор
    public OrderAScooter(String xpathButton, String nameCustomer, String surnameCustomer, String addressCustomer, String xpathNameStation, String phoneCustomer,
                         String dateRent, String rentalPeriod, String chekBoxColour, String сommentToCourier) {
        this.xpathButton = xpathButton;
        this.nameCustomer = nameCustomer;
        this.surnameCustomer = surnameCustomer;
        this.addressCustomer = addressCustomer;
        this.xpathNameStation = xpathNameStation;
        this.phoneCustomer = phoneCustomer;
        this.dateRent = dateRent;
        this.rentalPeriod = rentalPeriod;
        this.chekBoxColour = chekBoxColour;
        this.сommentToCourier = сommentToCourier;
    }

    @Parameterized.Parameters
    public static Object[][] getButtonOrder() {
        return new Object[][]{
                {"//button[@class = 'Button_Button__ra12g']",
                "Иван",
                "Иванов",
                "Профсоюзная 104",
                "//*[@id='root']/div/div[2]/div[2]/div[4]/div/div[2]/ul/li[1]/button",
                "+79272613021",
                "31.07.2023",
                "//*[@id='root']/div/div[2]/div[2]/div[2]/div[2]/div[1]",
                "//*[@id='black']",
                "Домофон не работает"
                },
                {"//*[@id='root']/div/div/div[4]/div[2]/div[5]/button",
                "Петр",
                "Петров",
                "Академическая 80",
                "//*[@id='root']/div/div[2]/div[2]/div[4]/div/div[2]/ul/li[2]/button",
                "+79171886446",
                "28.07.2023",
                "//*[@id='root']/div/div[2]/div[2]/div[2]/div[2]/div[1]",
                "//*[@id='grey']",
                "Предварительно позвоните по телефону"
                },
        };
    }

    @Before
    public void setUp(){
        webDriver = new FirefoxDriver();
        //webDriver = new ChromeDriver();

        webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

    @Test
    public void orderScooterValidDataOK(){
        SamokatPage samokatPage = new SamokatPage(webDriver);
        CustomerInfo customerInfo = new CustomerInfo(webDriver);
        RentSamokatInfo rentSamokatInfo = new RentSamokatInfo(webDriver);

        //Переходим на форму "Для кого самокат"
        samokatPage
                .openSite()
                .scrollToTheOrderButton(xpathButton)
                .clickOrderButton(xpathButton);

        //Заполняем форму "Для кого самокат"
        customerInfo
                .inputFieldName(nameCustomer)
                .inputFieldSurname(surnameCustomer)
                .inputFieldAddress(addressCustomer)
                .inputFieldStation(xpathNameStation)
                .inputFieldPhone(phoneCustomer)
                .goToTheFormRent();

        //Переходим в форму "Про аренду"
        rentSamokatInfo
                .inputRentalPeriod(rentalPeriod)
                .inputDateRent(dateRent)
                .clickCheckBoxColour(chekBoxColour)
                .inputCommentToCourier(сommentToCourier)
                .clickReserveButton()
                .clickYesButton();

        Assert.assertTrue("Нет сообщение 'Заказ оформлен'",
                webDriver.findElements(By.xpath("//*[@id='root']/div/div[2]/div[5]/div[text() = 'Заказ оформлен']")).size() > 0);
    }

    @After
    public void teardown(){
        webDriver.quit();
    }
}
