package org.DariaRyabinina;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
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
    public static final Logger LOGG = LoggerFactory.getLogger(Less16.class);

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
        clickButton("button");

        clickButton("first");
        WebElement exellent = webDriver.findElement(By.xpath("//label[@id='result'][2]"));
        Assert.assertEquals(exellent.getText(), "Excellent!");
        WebElement button = webDriver.findElement(By.xpath("//input[@value='Click me too!']"));
        Assert.assertNotEquals(button, null);
        webDriver.findElement(By.xpath("//input[@value='Click me too!']")).click();
        assertBack();
        webDriver.findElement(By.xpath("//a")).click();

        clickButton("checkbox");
        clickCheckBox("one");
        String stringCheckBox = "one";
        clickCheckBox("two");
        stringCheckBox += " two";
        clickButton("go");
        String result = webDriver.findElement(By.id("result")).getText();
        Assert.assertEquals(result, stringCheckBox);

      //     WebElement webElementCheckBoxes= webDriver.findElement(By.xpath("//label"));
     /*for (int i = 0; i <3 ; i++) {
         webElementCheckBoxes=webElementCheckBoxes.findElement(By.xpath(".//@id and "));
         LOGG.info(webElementCheckBoxes.getText());
         //if(webElementCheckBoxes.getText()==)
     }*/


        String stringRadio;
        clickCheckBox("radio_three");
        stringRadio = webDriver.findElement(By.xpath("//label//input[@id='radio_three']")).getAttribute("value");
        clickButton("radio_go");
        result = webDriver.findElement(By.id("radio_result")).getText();
        LOGG.info(result);
        Assert.assertEquals(result, stringRadio);
        assertBack();
        webDriver.findElement(By.xpath("//a")).click();


    //    lesson17
        clickButton("select");
        cooseSelect("Select your hero:", "Linus Torvalds");
        cooseSelect("Select your programming languages:", "Java");
        cooseSelect("Select your programming languages:", "C++");
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

        //Less18
        webDriver.get("https://savkk.github.io/selenium-practice");
        clickButton("alerts");
        webDriver.findElement(By.xpath("//button[.='Get password']")).click();
        Alert alert1 = webDriver.switchTo().alert();
        String alertText = alert1.getText();
        alertText = alertText.replace("Your password:", "");
        LOGG.info(alertText);
        alert1.accept();
        webDriver.findElement(By.xpath("//button[.='Enter password']")).click();
        Alert alert2=webDriver.switchTo().alert();
        alert2.sendKeys(alertText);
// как переключиться на Prompt
//Assert.assertEquals(webDriver.findElement(By));
    }

    public void clickButton(String idButton) {
        webDriver.findElement(By.id(idButton)).click();
    }

    public void clickCheckBox(String nameBox) {
        webDriver.findElement(By.id(nameBox)).click();
    }

    public void cooseSelect(String selectTitle, String value) {
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

  /*  @AfterMethod
    public void closeDriver() {
        webDriver.quit();
    }
*/
}


