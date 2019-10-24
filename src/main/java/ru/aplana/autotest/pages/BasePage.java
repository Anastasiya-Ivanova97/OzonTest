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
            WebElement element = driver.findElement(locator);
            driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            driver.manage().timeouts().implicitlyWait(45,TimeUnit.SECONDS);
            e.printStackTrace();
            return false;
        }
    }

    public boolean isElementPresent(WebElement element, By locator) {
        try{
            WebElement elemen =  element.findElement(locator);
            driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
            wait.until(ExpectedConditions.visibilityOf(elemen));
            return true;
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
