package com.selenium.simulation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Selenium {
    private WebDriver driver;
    public String call(){
        System.setProperty("webdriver.chrome.driver", "SpringBoot/src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://www.naver.com");
        String getTitle = driver.getTitle();
        driver.quit();
        return getTitle;
    }

}
