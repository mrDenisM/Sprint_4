package praktikum.samokat.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CustomerInfo {
    private WebDriver webDriver;
    private By fieldName = By.xpath("//*[@id = 'root']/div/div[2]/div[2]/div[1]/input"); // Поле "Имя"
    private By fieldSurname = By.xpath("//*[@id = 'root']/div/div[2]/div[2]/div[2]/input"); // Поле "Фамилия"
    private By fieldAddress = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[3]/input"); // Поле "Адрес"
    private By clickFieldStation = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[4]/div/div/input"); // Поле "Станция"
    private By fieldPhone = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[5]/input"); // Поле "Телефон"
    private By goToFormRent = By.xpath("//*[@id='root']/div/div[2]/div[3]/button");

    public CustomerInfo(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public CustomerInfo inputFieldName(String nameCustomer) {
        webDriver.findElement(fieldName).sendKeys(nameCustomer);
        return this;
    }

    public CustomerInfo inputFieldSurname (String surnameCustomer) {
        webDriver.findElement(fieldSurname).sendKeys(surnameCustomer);
        return this;
    }

    public CustomerInfo inputFieldAddress (String addressCustomer) {
        webDriver.findElement(fieldAddress).sendKeys(addressCustomer);
        return this;
    }

    public CustomerInfo inputFieldStation (String xpathNameStation) {
        webDriver.findElement(clickFieldStation).click();
        webDriver.findElement(By.xpath(xpathNameStation)).click();
        return this;
    }
    public CustomerInfo inputFieldPhone (String phoneCustomer) {
        webDriver.findElement(fieldPhone).sendKeys(phoneCustomer);
        return this;
    }

    public CustomerInfo goToTheFormRent () {
        webDriver.findElement(goToFormRent).click();
        return this;
    }
}
