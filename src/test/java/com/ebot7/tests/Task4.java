package com.ebot7.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Task4 {

    WebDriver driver = null;

    @BeforeMethod
    public void setup() {
        System.out.println("-------------Task 4------------");
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        driver.get("https://agoldoffish.wordpress.com/criminal-minds-opening-and-closing-quotes/");
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

    }

    @Test
    public void quotesByGideon() {
        System.out.println("Running Tests");
        List<WebElement> quotesGideon = driver.findElements(By.xpath("//p/strong[text()='Gideon']/ancestor::p"));
        int quotes_size = quotesGideon.size();

        String[] quotes = new String[quotes_size];
        for (int i = 0; i < quotes_size; i++) {
            quotes[i] = quotesGideon.get(i).getText();
        }

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.open()");

        ArrayList<String> al = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(al.get(1));
        driver.get("https://www-5eb9089a8e101d4880a5fbda.recruit.eb7.io/");
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        for (int i = 0; i < quotes_size; i++) {

            String[] arr = quotes[i].split("Gideon: ");

            driver.findElement(By.xpath("//button[contains(text(),'Add new quote')]")).click();
            driver.findElement(By.xpath("//input[@id=\"autorInput\"]")).sendKeys("Gideon");
            driver.findElement(By.xpath("//input[@id=\"quoteInput\"]")).sendKeys(arr[1]);
            driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();

        }

        driver.close();
        driver.switchTo().window(al.get(0));
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}

