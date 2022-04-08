package id.web.gosoft.selenium.trial.steps;

import id.web.gosoft.selenium.trial.app.GooglePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.steps.ScenarioSteps;
import org.hamcrest.Matchers;

import static org.hamcrest.MatcherAssert.assertThat;

public class GoogleFeatureSteps extends ScenarioSteps {

    GooglePage googlePage;

    @Given("user is on google.com")
    public void userIsOnGoogleCom() {
        googlePage.open();
    }

    @When("user search {string}")
    public void userSearch(String searchTerm) {
        googlePage.searchTerm(searchTerm);
    }

    @Then("user should see {string} in the search result")
    public void userShouldSeeInTheSearchResult(String searchTerm) {
        assertThat("wrong" , googlePage.getBody(), Matchers.containsString(searchTerm));
    }
}
