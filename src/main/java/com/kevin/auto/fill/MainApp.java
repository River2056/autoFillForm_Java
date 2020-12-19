package com.kevin.auto.fill;

import com.kevin.auto.fill.forms.FillForm;
import com.kevin.auto.fill.functions.Helper;
import com.kevin.auto.fill.selectTender.TenderWay;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class MainApp {
    public static WebDriver driver;
    private final Properties config;

    public MainApp() {
        InputStream configStream = ClassLoader.getSystemResourceAsStream("config.properties");
        config = new Properties();
        try {
            config.load(configStream);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Load properties fail!");
        }
        String chromeDriverPath = ClassLoader.getSystemResource("chromedriver").getPath();
        System.out.println(chromeDriverPath);
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver();
    }

    public static void main(String[] args) {
        MainApp app = new MainApp();
        driver.manage().window().maximize();
        driver.get(app.config.getProperty("url"));

        // 登入
        List<WebElement> inputElements = driver.findElements(By.tagName("input"));
        inputElements.forEach(e -> {
            if("idForLogin01".equals(e.getAttribute("id"))) e.sendKeys(app.config.getProperty("username"));
            if("uidExtForLogin01".equals(e.getAttribute("id"))) e.sendKeys(app.config.getProperty("ext"));
            if("password01".equals(e.getAttribute("id"))) e.sendKeys(app.config.getProperty("password"));
        });
        driver.findElement(By.id("imageLogin01")).click();

        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 6000);

        // 取消問卷
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().dismiss();

        // popup點確定
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("BlockUiQueue.next()");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        // 到新增招標公告
        driver.get(app.config.getProperty("createTender"));
        // 產生標案名稱
        String fileName = Helper.generateFileName();
        // 選擇招標方式
        TenderWay.selectTenderWay(app.config.getProperty("tenderWay"), fileName, app.config.getProperty("awardWay"), app.config.getProperty("multipleAward"));
        // 填表單
        Boolean pageLoadFinish = Helper.waitForPageLoad();
        if(pageLoadFinish) {
            FillForm fillForm = new FillForm(app.config.getProperty("tenderWay"));
            fillForm.fill();
        }
    }
}
