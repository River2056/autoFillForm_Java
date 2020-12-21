package com.kevin.auto.fill.forms;

import com.kevin.auto.fill.MainApp;
import com.kevin.auto.fill.forms.components.BuyInfo;
import com.kevin.auto.fill.forms.components.Dep;
import com.kevin.auto.fill.functions.Helper;
import org.openqa.selenium.JavascriptExecutor;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class FillForm {
    private String tenderWay;
    private String fileName;
    private Dep dep;
    private BuyInfo buyInfo;

    public FillForm(String tenderWay, String fileName) {
        this.tenderWay = tenderWay;
        this.fileName = fileName;
        dep = new Dep();
    }

    public void fill() {
        JavascriptExecutor js = (JavascriptExecutor) MainApp.driver;
        // 機關頁籤
        Boolean pageLoadFinish = Helper.waitForPageLoad();
        if(pageLoadFinish) {
            dep.fillAddress();
            js.executeScript("goto_form(tabs_next_name())"); // onclick="goto_form(tabs_next_name())"

            // 採購頁籤
            buyInfo = new BuyInfo(tenderWay, fileName);
            buyInfo.fillBuyInfoPage();
            js.executeScript("goto_form(tabs_next_name())");
        }
    }
}
