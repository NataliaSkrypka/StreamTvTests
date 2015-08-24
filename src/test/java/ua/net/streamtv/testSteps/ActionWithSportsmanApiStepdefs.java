package ua.net.streamtv.testSteps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.net.streamtv.steps.ApiSteps;
import ua.net.streamtv.utils.TestConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyOrNullString;

/**
 * Created by nskrypka on 8/22/15.
 */
@ContextConfiguration(classes = TestConfiguration.class)
public class ActionWithSportsmanApiStepdefs {

    @Autowired
    ApiSteps apiSteps;
    private String sessionId;
    private String sporsmanId;
    private Map<String, String> sportsman = new HashMap<>();

    @Given("^login through API$")
    public void login_through_API() throws Throwable {
        sessionId = apiSteps.login();
    }

    @When("^add sportsman through API$")
    public void add_sportsman_through_API(List<Map<String, String>> sportsmen) throws Throwable {
        sporsmanId = apiSteps.addSportsman(sessionId, sportsmen.get(0));
    }

    @Then("^sportsman was added successfully through API$")
    public void sportsman_was_added_successfully_through_API() throws Throwable {
        JSONObject sportsmanActual = apiSteps.readSportsman(sessionId, sporsmanId);
        for (String key : sportsman.keySet()) {
            assertThat("Value in column " + key + " is not as expected", sportsmanActual.get(key).toString(), equalTo(sportsman.get(key)));
        }
    }

    @Given("^search for ([\\w, \\+]+) sportsman$")
    public void search_for_sportsman(String searchParameter) throws Throwable {
        sporsmanId = apiSteps.searchForSportsman(sessionId, searchParameter);
    }

    @When("^update found sportsman through API :$")
    public void update_found_sportsman_through_API_(List<Map<String, String>> sportsmen) throws Throwable {
        Map<String, String> sportsmanInfo = new HashMap<>();
        sportsmanInfo.putAll(sportsmen.get(0));
        sportsmanInfo.put("id_wrestler", sporsmanId);
        apiSteps.updateSportsman(sessionId, sportsmanInfo);
    }

    @Then("^sportsman was updated successfully through API$")
    public void sportsman_was_updated_successfully_through_API() throws Throwable {
        sportsman_was_added_successfully_through_API();
    }

    @When("^delete found sportsman$")
    public void delete_found_sportsman() throws Throwable {
        apiSteps.deleteSportsman(sessionId, sporsmanId);
    }

    @Then("^there is no more sportman with ([\\w, \\+]+) fio$")
    public void there_is_no_more_sportman_with_fio(String fio) throws Throwable {
        sporsmanId = apiSteps.searchForSportsman(sessionId, fio);
        assertThat("Sportsman " + fio + " is still present after deleting", sporsmanId, isEmptyOrNullString());
    }

    @Given("^delete all profiles for ([\\w, \\+]+) sportsman$")
    public void delete_all_profiles_for_sportsman(String fio) throws Throwable {
        apiSteps.deleteAllProfiles(fio);
    }
}
