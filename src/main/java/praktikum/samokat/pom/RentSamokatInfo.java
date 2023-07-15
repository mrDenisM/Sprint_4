package praktikum.samokat.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RentSamokatInfo {
    private WebDriver webDriver;
    private By fieldRentalPeriod = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[2]/div[1]/div[1]"); // Поле "Когда привезти самокат"
    private By fieldDateRent = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[1]/div[1]/div/input"); // Поле "Когда привезти самокат"
    private By fieldComment = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[4]/input"); // Поле "Комментарий для курьера"
    private By reserveButton = By.xpath("//*[@id='root']/div/div[2]/div[3]/button[2]"); // Кнопка "Заказать"
    private By yesButton = By.xpath("//*[@id='root']/div/div[2]/div[5]/div[2]/button[2]"); // Форма "Подтвердить заказ?". Кнопка "Да"

    public RentSamokatInfo (WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public RentSamokatInfo inputRentalPeriod (String rentalPeriod) {
        webDriver.findElement(fieldRentalPeriod).click();
        webDriver.findElement(By.xpath(rentalPeriod)).click();
        return this;
    }
    public RentSamokatInfo inputDateRent (String dateRent) {
        webDriver.findElement(fieldDateRent).sendKeys(dateRent);
        return this;
    }
    public RentSamokatInfo clickCheckBoxColour (String chekBoxColour) {
        webDriver.findElement(By.xpath(chekBoxColour)).click();
        return this;
    }

    public RentSamokatInfo inputCommentToCourier (String сommentToCourier) {
        webDriver.findElement(fieldComment).sendKeys(сommentToCourier);
        return this;
    }

    public RentSamokatInfo clickReserveButton () {
        webDriver.findElement(reserveButton).click();
        return this;
    }

    public RentSamokatInfo clickYesButton () {
        webDriver.findElement(yesButton).click();
        return this;
    }
}
