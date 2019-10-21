package ru.aplana.autotest.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.util.*;

public class BasketPage extends BasePage implements Serializable {

    @FindBy(xpath = "//div[@class='cart-item']")
    List<WebElement> basketItems;

    @FindBy(xpath = "//span[@class='total-middle-header-title']")
    WebElement text1;

    @FindBy(xpath = "//span[@class='total-middle-header-text']")
    WebElement text2;

    @FindBy(xpath = "//span[contains(text(),\"Удалить выбранные\")]")
    WebElement deleteSelected;

    @FindBy(xpath = "//button[@class='button button blue']")
    WebElement submitButton;

    @FindBy(xpath = "//*[contains(text(),\"Корзина пуста\")]")
    WebElement emptyBasket;

    public void checkNames() {
        new WebDriverWait(driver, 90).until(new ExpectedCondition<Object>() {

            public Boolean apply(WebDriver webDriver) {
                try {
                    for (WebElement elem: basketItems) {
                        Assert.assertTrue(products.containsKey(elem.findElement(By.xpath("./descendant::a[@class='title']/span")).getText()));
                    }
                } catch (StaleElementReferenceException e) {
                    return false;
                }

                return true;
            }
        });
    }

    public void checkTotal(String title, Integer number) {
        wait.until(ExpectedConditions.visibilityOf(text1));
        Assert.assertTrue(text1.getText().contains(title)&&text2.getText().contains(number+" товар"));
    }

    public void deleteAll() {
        deleteSelected.click();
        wait.until(ExpectedConditions.visibilityOf(submitButton));
        submitButton.click();
    }

    public void checkIfEmpty() {
        Assert.assertTrue(emptyBasket.isDisplayed());
    }

    public void writeIntoFile() throws IOException {
        File file = new File("src/main/resources/items.txt");
        FileWriter writer = new FileWriter(file);
        for(Map.Entry<String, String> entry : products.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            writer.write(key+" = "+value.replaceAll("\\u20BD", "P")+"\n");
        }
        Collection<String> prices1 =  products.values();
        List<String> prices = new ArrayList<>();
        prices.addAll(prices1);
        Collections.sort(prices);

        Set<Map.Entry<String,String>> entrySet=products.entrySet();
        String key = null;
        String desiredObject=prices.get(prices.size()-1);//что хотим найти
        for (Map.Entry<String,String> pair : entrySet) {
            if (desiredObject.equalsIgnoreCase(pair.getValue())) {
               key = pair.getKey();// нашли наше значение и возвращаем  ключ
            }
        }
        writer.write("Товар с наибольшей ценой: "+key+" = "+products.get(key).replaceAll("\\u20BD", "P"));
        writer.flush();
        writer.close();
    }


}
