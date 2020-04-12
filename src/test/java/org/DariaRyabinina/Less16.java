package org.DariaRyabinina;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class Less16 {
    private WebDriver webDriver;
    public static final Logger LOGG = LoggerFactory.getLogger(Less16.class);

    @BeforeClass
    public void downloadDriverManager() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void initDriver() {
        webDriver = new ChromeDriver();
    }

    @AfterMethod
    public void closeDriver() {
        webDriver.quit();
    }

    @Test(testName = "Less16")
    public void Less16() {
        webDriver.get("https://savkk.github.io/selenium-practice/");
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        clickButton("button");
        clickButton("first");
        WebElement exellent = webDriver.findElement(By.xpath("//label[@id='result'][2]"));
        Assert.assertEquals(exellent.getText(), "Excellent!");
        WebElement button = webDriver.findElement(By.xpath("//input[@value='Click me too!']"));
        Assert.assertNotEquals(button, null);
        button.click();
        assertBack();
        webDriver.findElement(By.linkText("Great! Return to menu")).click();
        Cookie cookie1 = webDriver.manage().getCookieNamed("button");
        Assert.assertNotEquals(cookie1.toString(), "done");

        clickButton("checkbox");
        clickCheckBox("one");
        String stringCheckBox = "one";
        clickCheckBox("two");
        stringCheckBox += " two";
        clickButton("go");
        String result = webDriver.findElement(By.id("result")).getText();
        Assert.assertEquals(result, stringCheckBox);

        String stringRadio;
        clickCheckBox("radio_three");
        stringRadio = webDriver.findElement(By.xpath("//label//input[@id='radio_three']")).getAttribute("value");
        clickButton("radio_go");
        result = webDriver.findElement(By.id("radio_result")).getText();
        LOGG.info(result);
        Assert.assertEquals(result, stringRadio);
        assertBack();
        webDriver.findElement(By.linkText("Great! Return to menu")).click();
        Cookie cookie2 = webDriver.manage().getCookieNamed("checkboxes");
        Assert.assertNotEquals(cookie2.toString(), "done");
    }


    public void clickButton(String idButton) {
        webDriver.findElement(By.id(idButton)).click();
    }

    public void clickCheckBox(String nameBox) {
        webDriver.findElement(By.id(nameBox)).click();
    }

    public void enterValue(String name, String value) {
        webDriver.findElement(By.xpath("//label[.='" + name + "']/following::input")).sendKeys(value);

    }

    public void assertBack() {
        Assert.assertEquals(webDriver.findElement(By.xpath("//a")).getText(), "Great! Return to menu");
    }

}


