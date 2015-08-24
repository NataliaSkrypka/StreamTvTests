package ua.net.streamtv.testSteps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.net.streamtv.entities.Sportsman;
import ua.net.streamtv.steps.ActionsWithSportsmanSteps;
import ua.net.streamtv.steps.FileComparasionSteps;
import ua.net.streamtv.steps.LoginSteps;
import ua.net.streamtv.utils.TestConfiguration;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by nskrypka on 8/19/2015.
 */
@ContextConfiguration(classes = TestConfiguration.class)
public class SportsmenAdditionTestStepdefs {
    List<Sportsman> sportamenInfo;

    @Autowired
    ActionsWithSportsmanSteps actionsWithSportsmanSteps;
    @Autowired
    LoginSteps loginSteps;
    @Autowired
    FileComparasionSteps fileComparasionSteps;
    private String fio;
    private String lastName;
    private String fileAbsolutePath;

    @Given("^user login to streamtv site$")
    public void user_login_to_streamtv_site() throws Throwable {
        loginSteps.login();
    }

    @When("^user adds new sportsman$")
    public void user_adds_new_sportsman(List<Sportsman> sportsmenInfo) {
        this.sportamenInfo = sportsmenInfo;
        for (Sportsman sportsman : sportsmenInfo) {
            System.out.println(sportsman.getFirstName());
            actionsWithSportsmanSteps.addNewSportsman(sportsman);
        }
    }

    @When("^user change last name to (\\w+) for ([\\w,\\s]+) sportsman$")
    public void user_change_last_name_for_sportsman(String newLastName, String fio) {
        actionsWithSportsmanSteps.searchFor(fio);
        actionsWithSportsmanSteps.editLastName(newLastName);
    }

    @Then("^he is added successfully$")
    public void sportsman_was_added_successfully() throws Throwable {
        for (Sportsman sportsman : sportamenInfo) {
            Sportsman sportsmanActual = actionsWithSportsmanSteps.getInfoForSportsman(sportsman.getLastName());
            assertThat("Sportsmen list on UI is not as expected", sportsmanActual.getInfo(), is(sportsman.getInfo()));
        }
    }

    @Then("^changes for ([\\w,\\s]+) are made successfully$")
    public void changes_are_made_successfully(String fio, List<Sportsman> sportsmanAfterEdit) throws Throwable {
        Sportsman sportsmanActual = actionsWithSportsmanSteps.getInfoForSportsman(fio);
        assertThat("Sportsman after edit is not as expected", sportsmanActual.getInfo(), is(sportsmanAfterEdit.get(0).getInfo()));
    }

    @When("^user delete ([\\w,\\s]+) sportsman$")
    public void user_delete_sportsman(String fio) throws Throwable {
        this.fio = fio;
        actionsWithSportsmanSteps.searchFor(fio);
        actionsWithSportsmanSteps.deleteSportsman();
    }

    @Then("^he is deleted successfully$")
    public void he_is_deleted_successfully() throws Throwable {
        actionsWithSportsmanSteps.searchFor(fio);
        assertThat("Some sportsmen are still present after deleting", actionsWithSportsmanSteps.getSearchResultSize(), is(0));
    }

    @When("^user adds photo ([\\w, \\.]+) to (\\w+)'s profile$")
    public void user_adds_photo_photo_jpg_to_profile(String photoPath, String lastName) throws Throwable {
        this.lastName = lastName;
        actionsWithSportsmanSteps.searchFor(lastName);
        String photoAbsolutePath = System.getProperty("user.dir") + "/src/test/resources/files/" + photoPath;
        actionsWithSportsmanSteps.addPhotoToProfile(photoAbsolutePath);
    }

    @Then("^photo is equal to ([\\w, \\.]+) photo$")
    public void photo_is_equal_to_photo(String expectedPhotoName) throws Throwable {
        actionsWithSportsmanSteps.searchFor(lastName);
        String downloadedPhotoPath = actionsWithSportsmanSteps.downloadPhoto();
        boolean areImagesEqual = fileComparasionSteps.compareImages(System.getProperty("user.dir") + "/src/test/resources/files/" + expectedPhotoName, downloadedPhotoPath);
        assertThat("Downloaded image is not as expected", areImagesEqual, is(true));
    }

    @When("^user adds document ([\\w,\\d,\\.]+) to (\\w+)'s profile$")
    public void user_adds_document_to_profile(String fileName, String lastName) throws Throwable {
        this.lastName = lastName;
        fileAbsolutePath = System.getProperty("user.dir") + "/src/test/resources/files/" + fileName;
        actionsWithSportsmanSteps.searchFor(lastName);
        actionsWithSportsmanSteps.addDocumentToProfile(fileAbsolutePath);
    }

    @Then("^document was added successfully$")
    public void document_was_added_successfully() throws Throwable {
        actionsWithSportsmanSteps.searchFor(lastName);
        String fileActual = actionsWithSportsmanSteps.downloadFile();
        boolean areFilesEqual = fileComparasionSteps.comparePdfs(fileActual, fileAbsolutePath);
        assertThat("Downloaded file has not expected size", areFilesEqual, is(true));
    }

    @When("^user delete attachment for ([\\w,\\s]+) profile$")
    public void user_delete_attachment_for_profile(String fio) throws Throwable {
        this.lastName = fio;
        actionsWithSportsmanSteps.searchFor(fio);
        actionsWithSportsmanSteps.deleteAttachment();
    }

    @Then("^this sporsman's profile do not have attachments$")
    public void this_sporsman_s_profile_do_not_have_attachments() throws Throwable {
        int numberOfAttachments = actionsWithSportsmanSteps.getNumberOfAttachments(lastName);
        assertThat("Number of attachments is not as expected", numberOfAttachments, is(0));
    }

    @When("^user search for (\\w+) with ([\\w,\\-]+) region and (\\w+) fst$")
    public void user_search_for_sportsman_with_region_and_fst(String lastName, String region, String fst) throws Throwable {
        actionsWithSportsmanSteps.selectRegionFilter(region);
        actionsWithSportsmanSteps.selectFstFilter(fst);
        actionsWithSportsmanSteps.searchFor(lastName);
    }

    @Then("^only one record for uploaded sportsman is shown$")
    public void only_one_record_for_uploaded_sportsman_is_shown() throws Throwable {
        int searchResult = actionsWithSportsmanSteps.getSearchResultSize();
        assertThat("Search result size is not as expected", searchResult, is(1));
    }
}
