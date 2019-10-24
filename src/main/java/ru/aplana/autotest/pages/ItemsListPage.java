package ru.aplana.autotest.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ItemsListPage extends BasePage {

    int count=0;

    public ItemsListPage() {
    }

    WebElement element;
    List<WebElement> available = new ArrayList<>();
    String temp = null;

    @FindBy(xpath = "//span[contains(text(), \"Высокий рейтинг\")]/parent::label")
    WebElement rating;

    @FindBy(xpath = "//div[@class=\"cookies-info\"]/button")
    WebElement cookiesButton;

    @FindBy(xpath = "//*[@class=\"tile-wrapper\"]")
    List<WebElement> items;

    @FindBy(xpath = "//span[contains(text(),\"Корзина\")]/ancestor::a")
    WebElement cartButton;

    @FindBy(xpath = "//span[@data-test-id = 'filter-block-brand-show-all']")
    List<WebElement> brandviewAll;

    @FindBy(xpath = "//div[@class = \"input-wrap search-input\"]/input")
    List<WebElement> inputView;

    @FindBy(xpath = "")
    List<WebElement> brandView;

    @FindBy(xpath = "//button/span[text()='В корзину']")
    List<WebElement> buttonsAvailable;


    String key = "";
    String value="";
    public void findOption(String option, String p) {
        switch (option) {
            case "Цена до":
                choosePrice(option, p);
                break;
            case "Оперативная память":
                chooseParameter(option, p);
                break;
            case "Бренды":
                chooseBrands(option, p);
                count++;
                break;
        }
    }

    public void choosePrice(String option, String p) {
        element = driver.findElement(By.xpath("//div[contains(text(), " + option.substring(0, 3)
                + ")]/following-sibling::form/descendant::input[2]"));
        element.click();
        element.sendKeys(Keys.CONTROL + Keys.chord("a") + Keys.BACK_SPACE + Keys.chord(p));
    }

    public void chooseParameter(String option, String p) {
        new WebDriverWait(driver, 45).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                try {
                    if (isElementPresent(By.xpath("//div[contains(text(), 'Оперативная память')]/parent::div/descendant::span[contains(text(), '3 ГБ')]"))) {
                        try {
                            element = driver.findElement(By.xpath("//div[contains(text(), '" + option.substring(0,10)
                                    + "')]/parent::div/descendant::span[contains(text(), '" + p + "')]"));
                            element.click();
                            return true;
                        } catch (StaleElementReferenceException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    return false;
                }

                return false;
            }
        });
    }
    public void chooseBrands(String option, String p) {
        new WebDriverWait(driver, 45).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                try {
            if (isElementPresent(By.xpath("//span[@data-test-id = 'filter-block-brand-show-all']"))&&!isElementPresent(By.xpath("//div[@class = 'parandja']"))) {
                element = driver.findElement(By.xpath("//span[@data-test-id = 'filter-block-brand-show-all']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);
        }
        if (isElementPresent(By.xpath("//div[@class = 'input-wrap search-input']/input"))&&!isElementPresent(By.xpath("//div[@class = 'parandja']"))) {
            element = driver.findElement(By.xpath("//div[@class = 'input-wrap search-input']/input"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);
            element.clear();
            element.sendKeys(Keys.chord(p));
        }
        if (isElementPresent(By.xpath("//*[contains(text(),'"+p+"')]/parent::label"))&&!isElementPresent(By.xpath("//div[@class = 'parandja']"))) {
                element = driver.findElement(By.xpath("//*[contains(text(),'"+p+"')]/parent::label"));
            WebDriverWait wait1 = new WebDriverWait(driver, 10);
            wait1.until(ExpectedConditions.elementToBeClickable(element));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);
            if(count>0){
                if(isElementPresent(By.xpath("//*[text()=' найдено 9 товаров']"))){
                    WebElement element = driver.findElement(By.xpath("//*[text()=' найдено 9 товаров']"));
                    wait1.until(ExpectedConditions.visibilityOf(element));
                }
            }
            element = driver.findElement(By.xpath("//div[@class = 'input-wrap search-input']/input"));
            wait1.until(ExpectedConditions.elementToBeClickable(element));
        }
        return true;
                } catch (StaleElementReferenceException e) {
                    e.printStackTrace();
                    return false;
                }

    }
});

       /* Actions actions = new Actions(driver);
        actions.moveToElement(inputView.get(0)).click().build().perform();

        inputView.get(0).clear();
        inputView.get(0).sendKeys(Keys.chord(p)); */

     //   actions.moveToElement(inputView.get(0)).click().build().perform();
     //   element = driver.findElement(By.xpath("//*[contains(text(),'Beats')]/parent::label"));

       // WebDriverWait wait = new WebDriverWait(driver, 10);
      //  WebElement el_brand = wait.until(ExpectedConditions.elementToBeClickable(element));
      //  click(el_brand);
    }

    public void closeCookieBanner() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement el = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class=\"cookies-info\"]/button")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", el);
    }

    public WebElement getRating() {
        return rating;
    }
    /*
    public boolean existsElement(WebElement el) {
        try {
            el.findElement(By.xpath(".//*[text()='В корзину']"));
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    private boolean existsElementOpenBrend(WebDriver el) {
        try {
            el.findElement(By.xpath("//span[@data-test-id=\"filter-block-brand-show-all\"]"));
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    } */

    public void fillMap(WebElement element) {

        new WebDriverWait(driver, 45).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                try {
        if (isElementPresent(element, By.xpath(".//*[@data-test-id='tile-name']")) && isElementPresent(element, By.xpath(".//*[@data-test-id='tile-price']"))&& isElementPresent(By.xpath("//*[@data-test-id='tile-price']"))&& isElementPresent(By.xpath("//*[@data-test-id='tile-name']"))) {
            if (isElementPresent(element, By.xpath(".//*[@data-test-id='tile-name']"))) {
                 key = element.findElement(By.xpath(".//*[@data-test-id='tile-name']")).getText();
                if (isElementPresent(element, By.xpath(".//*[@data-test-id='tile-price']"))) {
                    value = element.findElement(By.xpath(".//*[@data-test-id='tile-price']")).getText();
                }
            }
            products.put(key, value);
        }
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }

            }
        });
    }

    public void selectsItems(String diff) {
        new WebDriverWait(driver, 45).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                try {
        for (WebElement el : items) {
               if(isElementPresent(el,By.xpath(".//*[text()='В корзину']"))&&isElementPresent(By.xpath("//*[text()='В корзину']"))) {
                   switch (diff) {
                       case "четных":
                           if (items.indexOf(el) % 2 != 0) {
                              // System.out.println("1");
                               buy(el);
                               fillMap(el);
                           }
                           break;
                       case "нечетных":
                           if (items.indexOf(el) % 2 == 0) {
                               buy(el);
                               fillMap(el);

                           }
                           break;
                   }

               }
            }
            return true;
                } catch (StaleElementReferenceException e) {
                    e.printStackTrace();
                    return false;
                }

            }
        });
          /*  new WebDriverWait(driver, 30).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver webDriver) {
                    try {
                        System.out.println(items.size());
                        for (WebElement el : items) {

                            if (existsElement(el)) {
                                element = el
                                        .findElement(By.xpath(".//*[text()='В корзину']"));


                                if (items.indexOf(el) % 2 != 0) {
                                    // ((JavascriptExecutor)driver).executeScript("arguments[0].click()", element);
                                    buy();
                                }
                            }
                        }

                    } catch (StaleElementReferenceException e) {
                        return false;
                    }

                    return true;
                }
            });
 */
        }

        private void buy (WebElement elem) {
            new WebDriverWait(driver, 45).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver webDriver) {

                    try {
                        if(isElementPresent(elem,By.xpath(".//*[text()='В корзину']/parent::button"))) {
                            element = elem.findElement(By.xpath(".//*[text()='В корзину']/parent::button"));
                            ((JavascriptExecutor) driver).executeScript("arguments[0].click()",
                                    element);
                            return true;
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        return false;
                    }
                    return false;

                }
            });
        }
/*
        public void selectEvenItems () {
            new WebDriverWait(driver, 30).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver webDriver) {
                    try {
                        System.out.println(items.size());
                        for (WebElement el : items) {

                            if (existsElement(el)) {
                                element = el
                                        .findElement(By.xpath(".//*[text()='В корзину']"));
                                if (items.indexOf(el) % 2 != 0) {
                                    // ((JavascriptExecutor)driver).executeScript("arguments[0].click()", element);
                                   // buy();
                                }
                            }
                        }

                    } catch (StaleElementReferenceException e) {
                        return false;
                    }

                    return true;
                }
            });

        }

        public void selectOddItems () {
            new WebDriverWait(driver, 30).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver webDriver) {
                    try {
                        System.out.println(items.size());
                        for (WebElement el : items) {

                            if (existsElement(el)) {
                                element = el
                                        .findElement(By.xpath(".//*[text()='В корзину']"));
                                if (items.indexOf(el) % 2 == 0) {
                                    // ((JavascriptExecutor)driver).executeScript("arguments[0].click()", element);
                                  //  buy();
                                }
                            }
                        }

                    } catch (StaleElementReferenceException e) {
                        return false;
                    }

                    return true;
                }
            });
        }
*/
    public WebElement getCartButton() {
        return cartButton;
    }
}
