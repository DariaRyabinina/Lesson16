package org.DariaRyabinina;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import org.openqa.selenium.*;

import java.util.concurrent.TimeUnit;

public class Less18 {
    private WebDriver webDriver;
    public static final Logger LOGG = LoggerFactory.getLogger(Less18.class);
    private WebDriver webDriver1;

    @BeforeClass
    public void downloadDriverManager() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void initDriver() {
        webDriver = new ChromeDriver();
        webDriver.get("https://savkk.github.io/selenium-practice/");

    }

    @Test(testName = "Less18_1")
    public void Less18_1() {
        clickButton("alerts");
        webDriver.findElement(By.xpath("//button[.='Get password']")).click();
        Alert alert1 = webDriver.switchTo().alert();
        String alertPass = alert1.getText();
        alertPass = alertPass.replace("Your password: ", "");
        LOGG.info(alertPass);
        alert1.accept();
        webDriver.findElement(By.xpath("//button[.='Enter password']")).click();

        Alert alert2 = webDriver.switchTo().alert();
        LOGG.info(alert2.getText());
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        LOGG.info(alertPass);
        alert2.sendKeys(alertPass);
        alert2.accept();
        Assert.assertEquals(webDriver.findElement(By.xpath("//button[.='Enter password']/following::label")).getText(), "Great!");
        webDriver.findElement(By.xpath("//button[@class ='return']")).click();
        Alert alert3 = webDriver.switchTo().alert();
        alert3.accept();

    }

    @Test(testName = "Less18_2")
    public void Less18_2() {
        clickButton("alerts");
        webDriver.findElement(By.xpath("//button[.='Get password']")).click();
        Alert alert1 = webDriver.switchTo().alert();
        String alertPass = alert1.getText();
        alertPass = alertPass.replace("Your password", "");
        LOGG.info(alertPass);
        alert1.accept();
        webDriver.findElement(By.xpath("//button[.='Enter password']")).click();

        Alert alert2 = webDriver.switchTo().alert();
        LOGG.info(alert2.getText());
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        LOGG.info(alertPass);
        alert2.sendKeys(alertPass);
        alert2.accept();
        try {
            WebElement webElement = webDriver.findElement(By.xpath("//label[.='Great!']"));
            Assert.fail();
        } catch (NoSuchElementException e) {
            Assert.assertTrue(true);

        }
    }

    @Test(testName = "Less18_3")
    public void Less18_3() {
        clickButton("table");

        selectCheckBox(2);
        selectCheckBox(3);
        webDriver.findElement(By.xpath("//table/following-sibling::input[1]")).click();
        enterValue("Contact", "123456");
        enterValue("Country", "Россия");
        enterValue("Company", "Ромашка");
        webDriver.findElement(By.xpath("//input[@value='Add']")).click();
        assertBack();
        clickBack();

    }

    @Test(testName = "Less18_4")
    public void Less18_4() {
        clickButton("iframe");
        webDriver.switchTo().frame("code-frame");
        String iframeKode = webDriver.findElement(By.id("code")).getText();
        iframeKode = iframeKode.replace("Your code is: ", "");
        webDriver.switchTo().defaultContent();
        webDriver.findElement(By.xpath("//input[@name='code']")).sendKeys(iframeKode);
        webDriver.findElement(By.xpath("//input[@name='ok']")).click();
        assertBack();
        clickBack();
    }


    public void clickButton(String idButton) {
        webDriver.findElement(By.id(idButton)).click();
    }

    public void clickCheckBox(String nameBox) {
        webDriver.findElement(By.id(nameBox)).click();
    }

    public void chooseSelect(String selectTitle, String value) {
        WebElement selectElement = webDriver.findElement(By.xpath("//label[.='" + selectTitle + "']/following-sibling::select[1]"));
        Select select = new Select(selectElement);
        select.selectByVisibleText(value);
    }

    public void enterValue(String name, String value) {
        webDriver.findElement(By.xpath("//label[.='" + name + "']/following::input")).sendKeys(value);

    }

    public void assertBack() {
        Assert.assertEquals(webDriver.findElement(By.xpath("//a")).getText(), "Great! Return to menu");
    }

    public void clickBack() {
        webDriver.findElement(By.xpath("//a")).click();
    }

    public void selectCheckBox(int rowIndex) {
        WebElement row = webDriver.findElement(By.xpath("//table//tr[" + rowIndex + "]"));
        row.findElement(By.xpath("//table//tr[" + rowIndex + "]//input")).click();

    }

}