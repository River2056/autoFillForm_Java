package com.kevin.auto.fill.functions;

import com.kevin.auto.fill.MainApp;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Calendar;
import java.util.function.Function;

public class Helper {
    public static String generateFileName() {
        Calendar calendar = Calendar.getInstance();
        return String.format("test_%s%s%s_%s", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH), calendar.getTime().getTime());
    }

    public static Boolean waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(MainApp.driver, 3000);
        JavascriptExecutor js = (JavascriptExecutor) MainApp.driver;
        return wait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                System.out.println("Current page status: " + String.valueOf(js.executeScript("return document.readyState")));
                return String.valueOf(js.executeScript("return document.readyState")).equals("complete");
            }
        });
    }
}
