package ua.net.streamtv.pages;

import org.apache.commons.io.FileUtils;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Created by nskrypka on 8/20/2015.
 */
public class SportsmanDetailsPage extends FluentPage {

    private WebDriver driver;

    public static final String ACCEPT_ALERT_BUTTON = "//div[@class='modal-dialog']//button[@class='btn btn-success']";

//    @FindBy(xpath = "//input[@placeholder='Last name']")
//    private WebElement lastNameInput;

    private static final String LAST_NAME_INPUT = "//input[@placeholder='Last name']";

    @FindBy(xpath = "//input[@placeholder='Date of Birth']")
    private WebElement dateOfBirthInput;

    @FindBy(xpath = "//fg-select[@label='Region']//select[@required='required']")
    private WebElement regionSelect;

    @FindBy(xpath = "//fg-select[@label='FST']//select[@required='required']")
    private WebElement fstSelect;

    @FindBy(xpath = "//fg-select[@label='Style']//select")
    private WebElement styleSelect;

    @FindBy(xpath = "//fg-select[@label='Year']//select")
    private WebElement yearSelect;

    @FindBy(xpath = "//input[@placeholder='First name']")
    private WebElement firstNameInput;

    @FindBy(xpath = "//input[@placeholder='Middle name']")
    private WebElement middleNameInput;

    @FindBy(xpath = "//fg-select[@label='Age']//select")
    private WebElement ageSelect;

    private static final String ADD_BUTTON = ".btn-success";
    private static final String DELETE_BUTTON = ".btn-danger";

    @FindBy(css = "tab-heading.ng-scope .glyphicon-remove")
    private WebElement closeTabIcon;

    private static final String WRESTLERS_TAB = "//div[@class='close-it']/ico[@class!='ng-hide']";

    @FindBy(xpath = "//input[@uploader='photoUploader']")
    private WebElement photoUploadButton;
    private static final String PHOTO_LOCATION = "//img[contains(@src,'data/photo')]";

    private static final String FILE_UPLOAD_BUTTON = "//input[@uploader='attachUploader']";

    private static final String FILE_LOCATION = "//a[contains(@href,'data/attach')]";

    private static final String DELETE_ATTACHMENT_ICON = "//ico[@ng-click='deleteAttach($index)']";

    private static final String ATTACHMENT_ROW = "//div[@class='file-drop']/table/tbody/tr";

    @FindBy(xpath = "//fg-select[@value='wr.region2']//select")
    private WebElement region1Select;

    @FindBy(xpath = "//fg-select[@value='wr.fst2']//select")
    private WebElement fst1Select;

    public SportsmanDetailsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void typeLastName(String lastName) {
        new WebDriverWait(driver, 3000).until(presenceOfElementLocated(By.xpath(LAST_NAME_INPUT)));

        driver.findElement(By.xpath(LAST_NAME_INPUT)).clear();
        driver.findElement(By.xpath(LAST_NAME_INPUT)).sendKeys(lastName);
    }

    public void typeDateOfBirth(String dateOfBirth) {
        new FluentWebElement(dateOfBirthInput).text(dateOfBirth);
    }

    public void selectRegion(String region) {
        Select regionDropdown = new Select(regionSelect);
        regionDropdown.selectByVisibleText(region);
    }

    public void selectFst(String fst) {
        Select fstDropdown = new Select(fstSelect);
        fstDropdown.selectByVisibleText(fst);
    }

    public void selectStyle(String style) {
        Select styleDropdown = new Select(styleSelect);
        styleDropdown.selectByVisibleText(style);
    }

    public void selectYear(String year) {
        Select yearDropdown = new Select(yearSelect);
        yearDropdown.selectByVisibleText(year);
    }

    public void typeFirstName(String firstName) {
        new FluentWebElement(firstNameInput).text(firstName);
    }

    public void typeMiddleName(String middleName) {
        new FluentWebElement(middleNameInput).text(middleName);
    }

    public void selectAge(String age) {
        Select ageDropdown = new Select(ageSelect);
        ageDropdown.selectByVisibleText(age);
    }

    public void clickAddNewWrestler() {
        await().atMost(5, TimeUnit.SECONDS).pollingEvery(500, TimeUnit.MILLISECONDS).until(ADD_BUTTON).areDisplayed();
        driver.findElement(By.cssSelector(ADD_BUTTON)).click();
    }

    public void closeSportsmanInfoTab() {
        new WebDriverWait(driver, 3000).until(presenceOfElementLocated(By.xpath(WRESTLERS_TAB)));
        driver.findElement(By.xpath(WRESTLERS_TAB)).click();
    }

    public String getLastName() {
        return new FluentWebElement(driver.findElement(By.xpath(LAST_NAME_INPUT))).getValue();
    }

    public String getDateOfBirth() {
        return new FluentWebElement(dateOfBirthInput).getValue();
    }

    public String getRegion() {
        Select regionDropdown = new Select(regionSelect);
        return regionDropdown.getFirstSelectedOption().getText();
    }

    public String getFst() {
        Select fstDropdown = new Select(fstSelect);
        return fstDropdown.getFirstSelectedOption().getText();
    }

    public String getStyle() {
        Select styleDropdown = new Select(styleSelect);
        return styleDropdown.getFirstSelectedOption().getText();
    }

    public String getYear() {
        Select yearDropdown = new Select(yearSelect);
        return yearDropdown.getFirstSelectedOption().getText();
    }

    public String getFirstName() {
        return new FluentWebElement(firstNameInput).getValue();
    }

    public String getMiddleName() {
        return new FluentWebElement(middleNameInput).getValue();
    }

    public String getAge() {
        Select ageDropdown = new Select(ageSelect);
        return ageDropdown.getFirstSelectedOption().getText();
    }

    public void deleteSportsman() {
        await().atMost(3, TimeUnit.SECONDS).pollingEvery(500, TimeUnit.MILLISECONDS).until(DELETE_BUTTON).areDisplayed();
        driver.findElement(By.cssSelector(DELETE_BUTTON)).click();
        driver.findElement(By.xpath(ACCEPT_ALERT_BUTTON)).click();
    }

    public void uploadPhoto(String photoPath) {
        photoUploadButton.sendKeys(photoPath);
    }

    public String downloadPhoto() throws IOException {
        new WebDriverWait(driver,3000).until(presenceOfElementLocated(By.xpath(PHOTO_LOCATION)));
        URL url = new URL(driver.findElement(By.xpath(PHOTO_LOCATION)).getAttribute("src"));
        BufferedImage bufImgOne = ImageIO.read(url);
        File photo = File.createTempFile("downloadedPhoto", ".png");
        ImageIO.write(bufImgOne, "png", photo);
        return photo.getAbsolutePath();
    }

    public void uploadFile(String fileAbsolutePath) {
        await().atMost(3, TimeUnit.SECONDS).pollingEvery(500, TimeUnit.MILLISECONDS).until(DELETE_BUTTON).areDisplayed();
        driver.findElement(By.xpath(FILE_UPLOAD_BUTTON)).sendKeys(fileAbsolutePath);
    }

    public String downloadFile() throws IOException {
        new WebDriverWait(driver, 3000).until(presenceOfElementLocated(By.xpath(FILE_LOCATION)));
        URL url = new URL(driver.findElement(By.xpath(FILE_LOCATION)).getAttribute("href"));
        File file = File.createTempFile("downloadedFile", ".pdf");
        FileUtils.copyURLToFile(url, file);
        return file.getAbsolutePath();
    }

    public void deleteAttachment() {
        new WebDriverWait(driver, 3000).until(presenceOfElementLocated(By.xpath(DELETE_ATTACHMENT_ICON)));
        getDriver().findElement(By.xpath(DELETE_ATTACHMENT_ICON)).click();
    }

    public int getNumberOfAttachments() {
        return driver.findElements(By.xpath(ATTACHMENT_ROW)).size();
    }

    public void selectRegion1(String region1) {
        Select region1Dropdown = new Select(region1Select);
        region1Dropdown.selectByVisibleText(region1);
    }

    public void selectFst1(String fst1) {
        Select fst1Dropdown = new Select(fst1Select);
        fst1Dropdown.selectByVisibleText(fst1);
    }

    public String getRegion1() {
        Select region1Dropdown = new Select(region1Select);
        return region1Dropdown.getFirstSelectedOption().getText();
    }

    public String getFst1() {
        Select fst1Dropdown = new Select(fst1Select);
        return fst1Dropdown.getFirstSelectedOption().getText();
    }
}
