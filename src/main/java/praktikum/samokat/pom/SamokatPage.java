package praktikum.samokat.pom;

import org.openqa.selenium.*;

public class SamokatPage {
    private WebDriver webDriver;
    private String actualText;
    private String url = "https://qa-scooter.praktikum-services.ru/";
    private By bodyPage = By.cssSelector("body");

    public SamokatPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public SamokatPage openSite() {
        webDriver.get(url);
        return this;
    }

    public SamokatPage scrollToTheOrderButton(String xpathButton) {
        WebElement element = webDriver.findElement(By.xpath(xpathButton));
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView();", element);
        return this;
    }

    public SamokatPage clickOrderButton(String xpathButton1) {
        webDriver.findElement(By.xpath(xpathButton1)).click();
        return this;
    }

    public SamokatPage scrollToTheEndPage() {
        webDriver.findElement(bodyPage).sendKeys(Keys.CONTROL, Keys.END);
        return this;
    }

    public SamokatPage clickQuestionText(String idQuestionText) {
        webDriver.findElement(By.id(idQuestionText)).click();
        return this;
    }

    public String argActualText(String idText) {
        actualText = webDriver.findElement(By.xpath("//*[@id = '" + idText + "']/p")).getAttribute("innerHTML");
        return actualText;
    }
}