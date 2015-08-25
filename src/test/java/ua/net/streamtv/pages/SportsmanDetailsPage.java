package ua.net.streamtv.pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.browserlaunchers.Sleeper;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
public class SportsmanDetailsPage {

    private WebDriver driver;

    public static final By ACCEPT_ALERT_BUTTON = By.xpath("//div[@class='modal-dialog']//button[@class='btn btn-success']");

    private static final By LAST_NAME_INPUT = By.xpath("//input[@placeholder='Last name']");

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

    private static final By ADD_BUTTON = By.cssSelector(".btn-success");
    private static final By DELETE_BUTTON = By.cssSelector(".btn-danger");

    @FindBy(css = "tab-heading.ng-scope .glyphicon-remove")
    private WebElement closeTabIcon;

    private static final String WRESTLERS_TAB = "//div[@class='close-it']/ico[@class!='ng-hide']";

    private static final By PHOTO_UPLOAD_BUTTON = By.xpath("//input[@uploader='photoUploader']");
    private static final String PHOTO_LOCATION = "//img[contains(@src,'data/photo')]";

    private static final By FILE_UPLOAD_BUTTON = By.xpath("//input[@uploader='attachUploader']");

    private static final String FILE_LOCATION = "//a[contains(@href,'data/attach')]";

    private static final String DELETE_ATTACHMENT_ICON = "//ico[@ng-click='deleteAttach($index)']";

    private static final String ATTACHMENT_ROW = "//div[@class='file-drop']/table/tbody/tr";

    @FindBy(xpath = "//fg-select[@value='wr.region2']//select")
    private WebElement region1Select;

    @FindBy(xpath = "//fg-select[@value='wr.fst2']//select")
    private WebElement fst1Select;

    public SportsmanDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void typeLastName(String lastName) {
        new WebDriverWait(driver, 3000).until(presenceOfElementLocated(LAST_NAME_INPUT));
        driver.findElement(LAST_NAME_INPUT).clear();
        driver.findElement(LAST_NAME_INPUT).sendKeys(lastName);
    }

    public void typeDateOfBirth(String dateOfBirth) {
        dateOfBirthInput.clear();
        dateOfBirthInput.sendKeys(dateOfBirth);
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
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
    }

    public void typeMiddleName(String middleName) {
        middleNameInput.clear();
        middleNameInput.sendKeys(middleName);
    }

    public void selectAge(String age) {
        Select ageDropdown = new Select(ageSelect);
        ageDropdown.selectByVisibleText(age);
    }

    public void clickAddNewWrestler() {
        new WebDriverWait(driver, 3000).until(presenceOfElementLocated(ADD_BUTTON));
        driver.findElement(ADD_BUTTON).click();
    }

    public void closeSportsmanInfoTab() {
        new WebDriverWait(driver, 3000).until(presenceOfElementLocated(By.xpath(WRESTLERS_TAB)));
        driver.findElement(By.xpath(WRESTLERS_TAB)).click();
    }

    public String getLastName() {
        return driver.findElement(LAST_NAME_INPUT).getAttribute("value");
    }

    public String getDateOfBirth() {
        return dateOfBirthInput.getAttribute("value");
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
        return firstNameInput.getAttribute("value");
    }

    public String getMiddleName() {
        return middleNameInput.getAttribute("value");
    }

    public String getAge() {
        Select ageDropdown = new Select(ageSelect);
        return ageDropdown.getFirstSelectedOption().getText();
    }

    public void deleteSportsman() {
        new WebDriverWait(driver, 3000).until(presenceOfElementLocated(DELETE_BUTTON));
        driver.findElement(DELETE_BUTTON).click();
        driver.findElement(ACCEPT_ALERT_BUTTON).click();
    }

    public void uploadPhoto(String photoPath) {
        new WebDriverWait(driver, 3000).until(presenceOfElementLocated(PHOTO_UPLOAD_BUTTON));
        driver.findElement(PHOTO_UPLOAD_BUTTON).sendKeys(photoPath);
        Sleeper.sleepTight(3000);
    }

    public String downloadPhoto() throws IOException {
//        new WebDriverWait(driver,3000).until(presenceOfElementLocated(By.xpath(PHOTO_LOCATION)));
        URL url = new URL(driver.findElement(By.xpath(PHOTO_LOCATION)).getAttribute("src"));
        BufferedImage bufImgOne = ImageIO.read(url);
        File photo = File.createTempFile("downloadedPhoto", ".png");
        ImageIO.write(bufImgOne, "png", photo);
        return photo.getAbsolutePath();
    }

    public void uploadFile(String fileAbsolutePath) {
        new WebDriverWait(driver,3000).until(presenceOfElementLocated(FILE_UPLOAD_BUTTON));
        driver.findElement(FILE_UPLOAD_BUTTON).sendKeys(fileAbsolutePath);
        Sleeper.sleepTight(3000);
    }

    public String downloadFile() throws IOException {
//        new WebDriverWait(driver, 3000).until(presenceOfElementLocated(By.xpath(FILE_LOCATION)));
        URL url = new URL(driver.findElement(By.xpath(FILE_LOCATION)).getAttribute("href"));
        File file = File.createTempFile("downloadedFile", ".pdf");
        FileUtils.copyURLToFile(url, file);
        return file.getAbsolutePath();
    }

    public void deleteAttachment() {
//        new WebDriverWait(driver, 3000).until(presenceOfElementLocated(By.xpath(DELETE_ATTACHMENT_ICON)));
        driver.findElement(By.xpath(DELETE_ATTACHMENT_ICON)).click();
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
