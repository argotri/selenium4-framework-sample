package id.web.gosoft.selenium.trial.app;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;

@DefaultUrl("https://www.google.com/")
public class GooglePage extends PageObject {
    public void searchTerm(String term) {
        find(By.name("q")).typeAndEnter(term);
    }

    public String getBody() {
        return getDriver().findElement(By.tagName("body")).getText();
    }
}
