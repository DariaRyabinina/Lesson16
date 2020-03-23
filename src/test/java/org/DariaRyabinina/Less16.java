package org.DariaRyabinina;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Less16 {
    private WebDriver webDriver;
    public static final Logger LOG = LoggerFactory.getLogger(Less16.class);

    @BeforeClass
    public void downloadDriverManager() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void initDriver() {
        webDriver = new ChromeDriver();
    }

    @Test
    public void Less16_1() {
        webDriver.get("https://savkk.github.io/selenium-practice/");
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement button = webDriver.findElement(By.id("button"));
        button.click();
        button = webDriver.findElement(By.id("first"));
        button.click();
        WebElement exellent = webDriver.findElement(By.xpath("//label[@id='result'][2]"));
        Assert.assertEquals(exellent.getText(), "Excellent!");
        button = webDriver.findElement(By.xpath("//input[@value='Click me too!']"));
        Assert.assertNotEquals(button, null);
        webDriver.findElement(By.xpath("//input[@value='Click me too!']")).click();
        button = webDriver.findElement(By.xpath("//a"));
        Assert.assertEquals(button.getText(), "Great! Return to menu");
        button.click();

        button = webDriver.findElement(By.id("checkbox"));
        LOG.info(button.getText());
        button.click();
        WebElement checkBox1 = webDriver.findElement(By.id("one"));
        checkBox1.click();
        WebElement checkBox2 = webDriver.findElement(By.id("two"));
        checkBox2.click();
        button = webDriver.findElement(By.id("go"));
        button.click();
        WebElement result = webDriver.findElement(By.id("result"));
        String stringResult = webDriver.findElement(By.id("result")).getText();
        Assert.assertEquals(result.getText(), checkBox1.getAttribute("id") + " " + checkBox2.getAttribute("id"));

        WebElement checkBox = webDriver.findElement(By.id("radio_three"));
        checkBox.click();
        button = webDriver.findElement(By.id("radio_go"));
        button.click();
        result = webDriver.findElement(By.id("radio_result"));
        LOG.info(result.getText());
        Assert.assertEquals(result.getText(), checkBox.getAttribute("value"));
        button = webDriver.findElement(By.xpath("//a"));
        Assert.assertEquals(button.getText(), "Great! Return to menu");
        LOG.info(button.getText());
        button.click();

    }
     @AfterMethod
public void closeDriver() {webDriver.quit();}
  }

