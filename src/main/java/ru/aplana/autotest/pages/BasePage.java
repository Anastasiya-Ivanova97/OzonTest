package ru.aplana.autotest.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.aplana.autotest.steps.BaseSteps;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class BasePage {
    WebDriver driver = BaseSteps.getDriver();
    Wait wait;
    public static HashMap<String,String> products = new HashMap<>();

    public BasePage(){
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver,30);
    }

    public boolean isElementPresent(By locator) {

        try{
            driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
            WebElement element = driver.findElement(locator);
            return element.isDisplayed();
        } catch (Exception e) {
            driver.manage().timeouts().implicitlyWait(45,TimeUnit.SECONDS);
            e.printStackTrace();
            return false;
        }

    }



    public boolean isElementPresent(WebElement element, By locator) {
        try{
            driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
            WebElement elemen =  element.findElement(locator);
            return elemen.isDisplayed();
        } catch (Exception e) {
            driver.manage().timeouts().implicitlyWait(45,TimeUnit.SECONDS);
            e.printStackTrace();
            return false;
        }
    }

    public void click(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
        Wait<WebDriver> wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void input(WebElement element, String keys) {
        element.click();
        element.clear();
        element.sendKeys(Keys.chord(keys));
    }

    public static String getProduct(String name) {
        return products.get(name);
    }
    public static void setProducts(String name, String price) {
        products.put(name, price);
    }

}
