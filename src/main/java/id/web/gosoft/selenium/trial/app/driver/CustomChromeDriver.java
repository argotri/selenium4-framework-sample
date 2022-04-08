package id.web.gosoft.selenium.trial.app.driver;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import net.thucydides.core.webdriver.DriverSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v97.network.Network;

import java.util.Optional;

public class CustomChromeDriver implements DriverSource {
    @Override
    public WebDriver newDriver() {
        ChromeDriverManager.chromedriver().setup();
        ChromeDriver chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
        DevTools chromeDevTools = chromeDriver.getDevTools();
        chromeDevTools.createSession();
        //add listener to intercept request and continue
        chromeDevTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        chromeDevTools.addListener(Network.requestWillBeSent(), requestSent -> {
            System.out.println("Request ID => " + requestSent.getRequestId());
            System.out.println("Request URL => " + requestSent.getRequest().getUrl());
            System.out.println("Request Method => " + requestSent.getRequest().getMethod());
            System.out.println("Request Headers => " + requestSent.getRequest().getHeaders().toString());
            System.out.println("-------------------------------------------------");
        });

        chromeDevTools.addListener(Network.responseReceived(), responseReceieved -> {
            System.out.println("Response Request ID => " + responseReceieved.getRequestId());
            System.out.println("Response Url => " + responseReceieved.getResponse().getUrl());
            System.out.println("Response Status => " + responseReceieved.getResponse().getStatus());
            System.out.println("Response Headers => " + responseReceieved.getResponse().getHeaders().toString());
            System.out.println("Response MIME Type => " + responseReceieved.getResponse().getMimeType().toString());
            System.out.println("------------------------------------------------------");

        });


        return chromeDriver;
    }

    @Override
    public boolean takesScreenshots() {
        return false;
    }
}
