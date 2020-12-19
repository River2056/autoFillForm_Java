package com.kevin.auto.fill.forms;

import com.kevin.auto.fill.MainApp;
import com.kevin.auto.fill.forms.components.Dep;
import org.openqa.selenium.JavascriptExecutor;

public class FillForm {
    private String tenderWay;
    private Dep dep;

    public FillForm(String tenderWay) {
        dep = new Dep();
    }

    public void fill() {
        JavascriptExecutor js = (JavascriptExecutor) MainApp.driver;
        dep.fillAddress();
        js.executeScript("goto_form(tabs_next_name())"); // onclick="goto_form(tabs_next_name())"
    }
}
