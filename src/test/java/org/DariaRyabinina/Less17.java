package org.DariaRyabinina;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class Less17 {
    private WebDriver webDriver;
    public static final Logger LOGG = LoggerFactory.getLogger(Less17.class);

    @BeforeClass
    public void downloadDriverManager() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void initDriver() {
        webDriver = new ChromeDriver();

    }

    @Test(testName = "Less17")
    public void Less17() {
        webDriver.get("https://savkk.github.io/selenium-practice/");
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        clickButton("select");
        chooseSelect("Select your hero:", "Linus Torvalds");
        chooseSelect("Select your programming languages:", "Java");
        chooseSelect("Select your programming languages:", "C++");
        clickButton("go");
        Assert.assertEquals(webDriver.findElement(By.xpath("//label[@name='result']")).getText(), "Linus Torvalds");
        Assert.assertEquals(webDriver.findElement(By.xpath("//label[@name='result'][2]")).getText(), "Java, C++");
        assertBack();
        webDriver.findElement(By.xpath("//a")).click();
        clickButton("form");
        enterValue("First Name:", "Vasia");
        enterValue("Last Name:", "Ivanov");
        enterValue("Email:", "ivanov@ya.ru");
        enterValue("Address:", "Moscow");
        webDriver.findElement(By.xpath("//label[.='Sex:']/following::input[1]")).click();
        enterValue("Avatar:", "C:\\Users\\mas-d\\Учеба\\rD2QaWX7-ms.JPG");
        webDriver.findElement(By.xpath("//label[.='Tell me something about yourself']/following::textarea")).sendKeys("Привет!");
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.findElement(By.xpath("//label[.='Tell me something about yourself']/following::input")).click();
        assertBack();

        webDriver.get("https://savkk.github.io/selenium-practice");
        clickButton("iframe");
        webDriver.switchTo().frame("code-frame");
        String kod = webDriver.findElement(By.id("code")).getText();
        LOGG.info(kod);
        kod = kod.replace("Your code is: ", "");
        LOGG.info(kod);
        webDriver.switchTo().defaultContent();
        webDriver.findElement(By.name("code")).sendKeys(kod);
        webDriver.findElement(By.name("ok")).click();
        assertBack();
        webDriver.findElement(By.xpath("//a")).click();
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
}
