package ru.aplana.autotest.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.aplana.autotest.steps.BaseSteps;

import java.util.HashMap;

public class BasePage {
    WebDriver driver = BaseSteps.getDriver();
    Wait wait;
    public static HashMap<String,String> products = new HashMap<>();

    public BasePage(){
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver,30);
    }

    public void click(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
        Wait<WebDriver> wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public static String getProduct(String name) {
        return products.get(name);
    }
    public static void setProducts(String name, String price) {
        products.put(name, price);
    }

}
