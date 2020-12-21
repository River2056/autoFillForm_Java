package com.kevin.auto.fill.forms.components;

import com.kevin.auto.fill.MainApp;
import com.kevin.auto.fill.functions.Helper;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BuyInfo {
    private String tenderWay;
    private String fileName;
    private List<WebElement> inputElements;
    private List<WebElement> selectElements;
    private List<WebElement> textareaElements;

    public BuyInfo(String tenderWay, String fileName) {
        this.tenderWay = tenderWay;
        this.fileName = fileName;
    }

    public void fillBuyInfoPage() {
        inputElements = MainApp.driver.findElements(By.tagName("input"));
        MainApp.driver.findElement(By.id("tenderName")).sendKeys(fileName);
        MainApp.driver.findElement(By.id("dynamicCPC_input")).sendKeys("5111");
        MainApp.driver.findElement(By.tagName("body")).click();
        MainApp.driver.findElement(By.id("planNo")).sendKeys("12345");
        MainApp.driver.findElement(By.name("fkTpamBuildType")).click();
        MainApp.driver.findElement(By.id("input_procurementAmount")).sendKeys("990000");
        MainApp.driver.findElement(By.name("fkTpamHowBid")).click();
        MainApp.driver.findElement(By.name("isAffectSec")).click();
        MainApp.driver.findElement(By.id("input_budget")).sendKeys("550000");
        MainApp.driver.findElement(By.id("budgetIsPdt")).click();
        MainApp.driver.findElement(By.id("input_estimatedProcurement")).sendKeys("550000");
        MainApp.driver.findElement(By.id("fuRite")).click();
        MainApp.driver.findElements(By.id("isGrant")).get(1).click();
        MainApp.driver.findElements(By.id("isSpecialBudget")).get(1).click();
        MainApp.driver.findElements(By.id("isExTender")).get(1).click();

//        inputElements.forEach(e -> {
//            String identity = StringUtils.isEmpty(e.getAttribute("id")) ? (StringUtils.isEmpty("name") ? e.getAttribute("class") : e.getAttribute("name")) : e.getAttribute("id");
//            if("text".equals(e.getAttribute("type")) && e.isDisplayed()) {
//                if(identity.startsWith("tenderName")) e.sendKeys(fileName);
//                if(identity.startsWith("dynamic") && identity.endsWith("_input")) e.sendKeys("5111");
//                if(identity.startsWith("planNo")) e.sendKeys("12345");
//            }
//            if("radio".equals(e.getAttribute("type")) && e.isDisplayed()) {
//                if(identity.startsWith("fkTpamBuildType") && "1".equals(e.getAttribute("value"))) e.click();
//            }
//        });

//        selectElements = MainApp.driver.findElements(By.tagName("select"));
//        MainApp.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//        selectElements.forEach(e -> {
//            String identity = StringUtils.isEmpty(e.getAttribute("id")) ? (StringUtils.isEmpty("name") ? e.getAttribute("class") : e.getAttribute("name")) : e.getAttribute("id");
//            if(e.isDisplayed() && e.isEnabled()) {
//                Select select = new Select(e);
//                select.selectByIndex(1);
//            }
//        });
//
//        textareaElements = MainApp.driver.findElements(By.tagName("textarea"));
//        MainApp.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//        textareaElements.forEach(e -> {
//            if(e.isEnabled() && e.isDisplayed()) {
//                e.sendKeys("測試測試測試\n測試測試測試\n測試測試測試");
//            }
//        });
    }
}
