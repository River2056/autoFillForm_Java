package com.kevin.auto.fill.forms.components;

import com.kevin.auto.fill.MainApp;
import com.kevin.auto.fill.functions.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class Dep {
    private String city;
    private String area;
    private String zip;
    private String address;

    public Dep() {
        this.city = "臺北市";
        this.area = "中正區";
        this.zip = "100";
        this.address = "建國路三段80巷21號";
    }

    public Dep(String city, String area, String zip, String address) {
        this.city = city;
        this.area = area;
        this.zip = zip;
        this.address = address;
    }

    public void fillAddress() {
        Boolean pageLoadFinish = Helper.waitForPageLoad();
        if(pageLoadFinish) {
            Select city = new Select(MainApp.driver.findElement(By.id("addrContainer_city")));
            city.selectByValue(this.city);

            Select area = new Select(MainApp.driver.findElement(By.id("addrContainer_cityArea")));
            area.selectByValue(this.area);

            MainApp.driver.findElement(By.id("addrContainer_addrDetail")).sendKeys(this.address);
        }
    }
}
