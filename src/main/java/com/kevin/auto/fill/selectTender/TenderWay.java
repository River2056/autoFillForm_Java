package com.kevin.auto.fill.selectTender;

import com.kevin.auto.fill.MainApp;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TenderWay {
    public static List<WebElement> inputElements;
    public static List<WebElement> selectElements;

    static {
        selectElements = MainApp.driver.findElements(By.tagName("select"));
    }

    public static void selectTenderWay(String tenderWay, String fileName, String... radioButtons) {
        selectElements.forEach(e -> {
            String identity = StringUtils.isEmpty(e.getAttribute("id")) ? (StringUtils.isEmpty(e.getAttribute("name")) ? e.getAttribute("class") : e.getAttribute("name")) : e.getAttribute("id");
            if(identity.startsWith("fkPmsTenderWay")) {
                Select select = new Select(e);
                select.selectByValue(tenderWay);
            }
        });
        MainApp.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        inputElements = MainApp.driver.findElements(By.tagName("input"));
        inputElements.forEach(e -> {
            try {
                String identity = StringUtils.isEmpty(e.getAttribute("id")) ? (StringUtils.isEmpty(e.getAttribute("name")) ? e.getAttribute("class") : e.getAttribute("name")) : e.getAttribute("id");
                // 輸入標案名稱
                // commonUtil_castnumber5inputid
                if(identity.startsWith("commonUtil_castnumber") && identity.endsWith("inputid")) {
                    e.sendKeys(fileName);
                }
                if(!"12".equals(tenderWay) && !"5".equals(tenderWay) && !"10".equals(tenderWay)) {
                    if("fkPmsAwardWay".equals(identity) && radioButtons[0].equals(e.getAttribute("value"))) e.click();
                    if("isMultipleAward".equals(identity) && radioButtons[1].equals(e.getAttribute("value"))) e.click();
                }
            } catch(Exception ex) {
                ex.printStackTrace();
                System.out.printf("Error: %s%n", ex.getMessage());
                System.out.printf("element: %s%n", e);
                System.out.printf("element id: %s%n", e.getAttribute("id"));
                System.out.printf("element location: %s%n", e.getLocation());
            }
        });
        JavascriptExecutor js = (JavascriptExecutor) MainApp.driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        MainApp.driver.findElement(By.tagName("body")).click();

        // 按新增
        MainApp.driver.findElement(By.id("submit_button")).click();

        // 公開招標選最低標會跳提示 => 按掉
        WebElement popupYesBtn = MainApp.driver.findElement(By.id("yes"));
        if(popupYesBtn != null && popupYesBtn.isDisplayed()) {
            popupYesBtn.click();
        }
    }
}
