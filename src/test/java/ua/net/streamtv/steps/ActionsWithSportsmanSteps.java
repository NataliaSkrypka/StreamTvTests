package ua.net.streamtv.steps;

import org.openqa.selenium.WebDriver;
import ua.net.streamtv.entities.Sportsman;
import ua.net.streamtv.pages.SearchPage;
import ua.net.streamtv.pages.SportsmanDetailsPage;

import java.io.IOException;

/**
 * Created by nskrypka on 8/20/2015.
 */
public class ActionsWithSportsmanSteps {

    SearchPage searchPage;
    SportsmanDetailsPage sportsmanDetailsPage;

    private WebDriver driver;

    public ActionsWithSportsmanSteps(WebDriver driver) {
        this.driver = driver;
        searchPage = new SearchPage(driver);
        sportsmanDetailsPage = new SportsmanDetailsPage(driver);
    }

    public void addNewSportsman(Sportsman sportsman) {
        searchPage.clickAddNewSportsman();
        sportsmanDetailsPage.typeLastName(sportsman.getLastName());
        sportsmanDetailsPage.typeDateOfBirth(sportsman.getDateOfBirth());
        sportsmanDetailsPage.selectRegion(sportsman.getRegion());
        sportsmanDetailsPage.selectFst(sportsman.getFst());
        sportsmanDetailsPage.selectStyle(sportsman.getStyle());
        sportsmanDetailsPage.selectYear(sportsman.getYear());
        sportsmanDetailsPage.typeFirstName(sportsman.getFirstName());
        sportsmanDetailsPage.typeMiddleName(sportsman.getMiddleName());
        sportsmanDetailsPage.selectRegion1(sportsman.getRegion1());
        sportsmanDetailsPage.selectFst1(sportsman.getFst1());
        sportsmanDetailsPage.selectAge(sportsman.getAge());
        sportsmanDetailsPage.clickAddNewWrestler();
        sportsmanDetailsPage.closeSportsmanInfoTab();
    }

    public Sportsman getInfoForSportsman(String lastName) {
        searchPage.searchForSportsman(lastName);
        searchPage.openSportsmanDetails();
        String lastNameActual = sportsmanDetailsPage.getLastName();
        String dateOfBirth = sportsmanDetailsPage.getDateOfBirth();
        String region = sportsmanDetailsPage.getRegion();
        String fst = sportsmanDetailsPage.getFst();
        String style = sportsmanDetailsPage.getStyle();
        String year = sportsmanDetailsPage.getYear();
        String firstName = sportsmanDetailsPage.getFirstName();
        String middleName = sportsmanDetailsPage.getMiddleName();
        String region1 = sportsmanDetailsPage.getRegion1();
        String fst1 = sportsmanDetailsPage.getFst1();
        String age = sportsmanDetailsPage.getAge();
        return new Sportsman(lastNameActual, dateOfBirth, region, fst, style, year, firstName, middleName, region1, fst1, age);
    }

    public void searchFor(String fio) {
        searchPage.searchForSportsman(fio);
    }

    public void editLastName(String newLastName) {
        searchPage.openSportsmanDetails();
        sportsmanDetailsPage.typeLastName(newLastName);
        sportsmanDetailsPage.clickAddNewWrestler();
        sportsmanDetailsPage.closeSportsmanInfoTab();
    }

    public void deleteSportsman() {
        searchPage.openSportsmanDetails();
        sportsmanDetailsPage.deleteSportsman();
    }

    public int getSearchResultSize() {
        return searchPage.getSearchResultSize();
    }

    public void addPhotoToProfile(String photoPath) {
        searchPage.openSportsmanDetails();
        sportsmanDetailsPage.uploadPhoto(photoPath);
        sportsmanDetailsPage.closeSportsmanInfoTab();
    }

    public String downloadPhoto() throws IOException {
        searchPage.openSportsmanDetails();
        return sportsmanDetailsPage.downloadPhoto();
    }

    public void addDocumentToProfile(String fileAbsolutePath) {
        searchPage.openSportsmanDetails();
        sportsmanDetailsPage.uploadFile(fileAbsolutePath);
        sportsmanDetailsPage.closeSportsmanInfoTab();
    }

    public String downloadFile() throws IOException {
        searchPage.openSportsmanDetails();
        return sportsmanDetailsPage.downloadFile();
    }

    public void deleteAttachment() {
        searchPage.openSportsmanDetails();
        sportsmanDetailsPage.deleteAttachment();
        sportsmanDetailsPage.closeSportsmanInfoTab();
    }

    public int getNumberOfAttachments(String fio) {
        searchFor(fio);
        searchPage.openSportsmanDetails();
        return sportsmanDetailsPage.getNumberOfAttachments();
    }

    public void selectRegionFilter(String region) {
        searchPage.selectRegionFilter(region);
    }

    public void selectFstFilter(String fst) {
        searchPage.selectFstFilter(fst);
    }
}
