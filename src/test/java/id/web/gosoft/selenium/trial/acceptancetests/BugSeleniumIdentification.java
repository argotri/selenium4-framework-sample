package id.web.gosoft.selenium.trial.acceptancetests;

import id.web.gosoft.selenium.trial.app.driver.CustomChromeDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BugSeleniumIdentification {

    private CustomChromeDriver customChromeDriver;
    private WebDriver webDriver;

    @Test
    public void test() {
        webDriver.get("https://reqres.in/");
        WebElement update = webDriver.findElement(By.xpath("//li[@data-id=\"patch\"]"));
        update.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Before
    public void setup(){
        customChromeDriver = new CustomChromeDriver();
        webDriver = customChromeDriver.newDriver();
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }
}
