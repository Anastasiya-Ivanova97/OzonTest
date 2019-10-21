package ru.aplana.autotest.steps;

import cucumber.api.java.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.aplana.autotest.util.TestProperties;

import java.util.concurrent.TimeUnit;

public class BaseSteps {

    public static WebDriver driver;
    public static TestProperties testProperties = TestProperties.getInstance();

    @cucumber.api.java.Before
    public static void init() {
        switch (testProperties.getProperty("browser")) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", testProperties.getProperty("chromedriver"));
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", testProperties.getProperty("webdriver.gecko.driver"));
                driver = new FirefoxDriver();
                break;
        }
        driver.manage().window().maximize();
        driver.get(testProperties.getProperty("url"));
        driver .manage().timeouts().implicitlyWait(Integer.parseInt(testProperties.getProperty("timeout.global")), TimeUnit.SECONDS);
        driver .manage().timeouts().pageLoadTimeout(Integer.parseInt(testProperties.getProperty("timeout.pageLoad")), TimeUnit.SECONDS);
    }


    public static void tearDown() {
        driver.quit();
    }



    public static WebDriver getDriver(){
        return driver;
    }
}