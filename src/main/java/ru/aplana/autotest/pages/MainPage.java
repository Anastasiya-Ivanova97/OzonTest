package ru.aplana.autotest.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    @FindBy(xpath = "//input[@class='search-input']")
    WebElement searchString;


    public void search(String searchFor) {
        searchString.click();
        searchString.sendKeys(Keys.chord(searchFor+Keys.ENTER));
    }
}
