package com.ebot7.tests;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.apache.log4j.Logger;
import java.util.concurrent.TimeUnit;

public class Task3 {
    WebDriver driver = null;
    Logger log = Logger.getLogger("Task3.class");

    @BeforeMethod
    public void setup() {
        System.out.println("-------------Task 4------------");
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver();
        log.info("Browser is launched");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        driver.get("https://www-5eb9089a8e101d4880a5fbda.recruit.eb7.io/");
        log.info("Opening URL");
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);

    }

    @Test(priority = 1)
    public void plagiarismCheck(){
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        int i;
        for(i =0; i < 2; i++) {

            driver.findElement(By.xpath("//button[contains(text(),'Add new quote')]")).click();
            driver.findElement(By.xpath("//input[@id=\"autorInput\"]"))
                    .sendKeys("Reid");
            driver.findElement(By.xpath("//input[@id=\"quoteInput\"]"))
                    .sendKeys("This is a plagiarism test. Three consecutive words aren't allowed.");
            driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
        }
        Assert.assertNotEquals(i,1, "Second Iteration passed with exact same input. Plagiarism check not implemented");
        log.info("Plagiarism check not implemented");
    }

    @Test(priority = 2)
    public void minLengthValidation() throws Exception{
        try {
            String str = "123";
            System.out.println("The length of the quote should be between 10 and 200 characters.");
            System.out.println("Length of input string =" + str.length());
            log.info("Testing minimum length");
            driver.findElement(By.xpath("//button[contains(text(),'Add new quote')]")).click();
            driver.findElement(By.xpath("//input[@id=\"autorInput\"]"))
                    .sendKeys("Reid");
            driver.findElement(By.xpath("//input[@id=\"quoteInput\"]"))
                    .sendKeys(str);
            driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
            log.info("Minimum length validation has failed");
            System.out.println("Still in try block. No exception raised.");
        }
        catch (Exception e){
            System.out.println("An exception was raised");
        }

    }

    @AfterMethod
    public void tearDown(){
        log.info("Closing the browser");
        driver.close();
    }

}
