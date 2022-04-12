package id.web.gosoft.selenium.trial.app.driver;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import net.thucydides.core.webdriver.DriverSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.NetworkInterceptor;
import org.openqa.selenium.devtools.v97.network.Network;
import org.openqa.selenium.remote.http.Filter;
import org.openqa.selenium.remote.http.HttpResponse;

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

        Filter reportStatusCodes = next -> req -> {
            HttpResponse res = next.execute(req);
            if(req.getUri().contains("users/2")){
                System.out.println(req.getMethod() + " " + req.getUri() + " " + res.getStatus());
            }
            return res;
        };
        NetworkInterceptor networkInterceptor = new NetworkInterceptor(chromeDriver, reportStatusCodes);

        return chromeDriver;
    }

    @Override
    public boolean takesScreenshots() {
        return false;
    }
}
