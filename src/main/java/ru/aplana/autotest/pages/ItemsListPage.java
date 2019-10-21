package ru.aplana.autotest.pages;


import org.openqa.selenium.*;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ItemsListPage extends BasePage {

    public ItemsListPage() {
    }

    WebElement element;
    List<WebElement> available = new ArrayList<>();
    String temp=null;



    @FindBy(xpath = "//span[contains(text(), \"Высокий рейтинг\")]/parent::label")
    WebElement rating;

    @FindBy(xpath = "//div[@class='cookies-info']/button")
    WebElement cookiesButton;

    @FindBy(xpath = "//div[@class=\"tile-wrapper\"]")
   List<WebElement> items;

    @FindBy(xpath = "//span[contains(text(),\"Корзина\")]/ancestor::a")
    WebElement cartButton;

    @FindBy(xpath = "//span[@data-test-id = 'filter-block-brand-show-all']")
    List<WebElement> brandviewAll;

    @FindBy(xpath = "//div[@class = \"input-wrap search-input\"]/input")
    List<WebElement> inputView;

    @FindBy(xpath = "//label[@class = \"checkbox-label\"]")
    List<WebElement> brandView;

    @FindBy(xpath = "//button/span[text()='В корзину']")
    List<WebElement> buttonsAvailable;


    public void findOption(String option, String p) {
        switch (option) {
            case "Цена до":
                element = driver.findElement(By.xpath("//div[contains(text(), " + option.substring(0, 3) + ")]/following-sibling::form/descendant::input[2]"));
                element.click();
                element.sendKeys(Keys.CONTROL + Keys.chord("a") + Keys.BACK_SPACE + Keys.chord(p));
                break;
            case "Оперативная память":
                new WebDriverWait(driver, 90).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver webDriver) {
                        try {
                            element = driver.findElement(By.xpath("//div[contains(text(), '" + option + "')]/parent::div/descendant::span[contains(text(), '" + p + "')]"));
                            // wait.until(ExpectedConditions.visibilityOf(element));
                            element.click();
                        } catch (StaleElementReferenceException e) {
                            return false;
                        }

                        return true;
                    }
                });
                break;
            case "Бренды":

                if(brandviewAll.size()!=0) {
                    brandviewAll.get(0).click();
                }
                if (!inputView.isEmpty()) {
                    click(inputView.get(0));
                    inputView.get(0).clear();
                    inputView.get(0).sendKeys(Keys.chord(p));
                    wait.until(ExpectedConditions.elementToBeClickable(inputView.get(0)));
                }
                if (!brandView.isEmpty()) {
                    for (WebElement brand: brandView) {
                        if (brand.getText().equalsIgnoreCase(p)) {
                            click(brand);
                            return;
                        }
                    }
                }
                break;
               /* element = driver.findElement(By.xpath("//div[contains(text(), '" + option + "')]/parent::div/descendant::span[contains(text(), '" + p + "')]"));
                // wait.until(ExpectedConditions.visibilityOf(element));
                element.click();
                break; */
        }
    }

    public void closeCookieBanner() {
        wait.until(ExpectedConditions.visibilityOf(cookiesButton));
        cookiesButton.click();
    }


    public WebElement getRating() {
        return rating;
    }

    public void selectItems() {

        System.out.println(items.size());
       // wait.until(ExpectedConditions.stalenessOf(items)));
        new WebDriverWait(driver, 120).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                try {
                    for (WebElement el : items) {
                     if(el.findElement(By.xpath(".//button")).getText().contains("В корзину")) {
                             available.add(el);
                      }
                    }
                } catch (StaleElementReferenceException e) {
                    return false;
                }

                return true;
            }
        });
      /*  for (WebElement el:items) {

            if(el.findElement(By.xpath("./descendant::button/span")).getText().equalsIgnoreCase("В корзину")) {
                available.add(el);
            }
        } */
        System.out.println(available.size());

    }

        public void selectOdd() {

       new WebDriverWait(driver, 120).until(new ExpectedCondition<Object>() {

                public Boolean apply(WebDriver webDriver) {
                    try {
                        for (WebElement ell: available) {
                            element = ell.findElement(By.xpath("./descendant::button"));
                            if(available.indexOf(ell)%2==0) {
                                element.click();
                                products.put(ell.findElement(By.xpath("./descendant::a")).getText(), ell.findElement(By.xpath("./descendant::span[@data-test-id='tile-price']")).getText());
                            }
                        }
                    } catch (StaleElementReferenceException e) {
                        return false;
                    }

                    return true;
                }
            });
            // products.put(available.get(i).findElement(By.xpath("./descendant::a")).getText(), available.get(i).findElement(By.xpath("./descendant::span[@data-test-id='tile-price']")).getText());
            System.out.println(products);

        }

        public void selectEven() {
            new WebDriverWait(driver, 120).until(new ExpectedCondition<Object>() {

                public Boolean apply(WebDriver webDriver) {
                    try {
                        for (WebElement ell: available) {
                            element = ell.findElement(By.xpath("./descendant::button"));
                            if(available.indexOf(ell)%2!=0) {
                                element.click();
                                products.put(ell.findElement(By.xpath("./descendant::a")).getText(), ell.findElement(By.xpath("./descendant::span[@data-test-id='tile-price']")).getText());
                            }
                        }
                    } catch (StaleElementReferenceException e) {
                        return false;
                    }

                    return true;
                }
            });
            // products.put(available.get(i).findElement(By.xpath("./descendant::a")).getText(), available.get(i).findElement(By.xpath("./descendant::span[@data-test-id='tile-price']")).getText());
            System.out.println(products);

        }


    public WebElement getCartButton() {
        return cartButton;
    }
}




