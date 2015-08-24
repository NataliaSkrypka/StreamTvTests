package ua.net.streamtv.pages;

import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Created by nskrypka on 8/20/2015.
 */
public class SearchPage extends FluentPage {

    private WebDriver driver;

    private static final String NEW_SPORTSMAN_BUTTON = ".form-group .btn-default";
    private static final String SEARCH_INPUT = "input[ng-model=searchFor]";
    private static final String SEARCH_BUTTON = "div.form-group .btn-primary";
    private static final String RESULT_ROW = ".table tbody tr";
    private static final String REGION_FILTER = "//select[@ng-model='filters.fregion']";

    @FindBy(xpath = "//select[@ng-model='filters.ffst']")
    private WebElement fstSelect;

    public SearchPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickAddNewSportsman() {
        await().atMost(3, TimeUnit.SECONDS).pollingEvery(500, TimeUnit.MILLISECONDS).until(NEW_SPORTSMAN_BUTTON).isPresent();
        driver.findElement(By.cssSelector(NEW_SPORTSMAN_BUTTON)).click();
    }

    public void searchForSportsman(String lastName) {
        await().atMost(3, TimeUnit.SECONDS).pollingEvery(200, TimeUnit.MILLISECONDS).until(SEARCH_INPUT).areDisplayed();
        fill(SEARCH_INPUT).with(lastName);
        click(SEARCH_BUTTON);
    }

    public void openSportsmanDetails() {
        new WebDriverWait(driver, 3000).until(presenceOfElementLocated(By.cssSelector(RESULT_ROW)));
        driver.findElement(By.cssSelector(RESULT_ROW)).click();
    }

    public int getSearchResultSize() {
        return driver.findElements(By.cssSelector(RESULT_ROW)).size();
    }

    public void selectRegionFilter(String region) {
        new WebDriverWait(driver, 3000).until(presenceOfElementLocated(By.xpath(REGION_FILTER)));
        Select regionDropdown = new Select(driver.findElement(By.xpath(REGION_FILTER)));
        regionDropdown.selectByVisibleText(region);
    }

    public void selectFstFilter(String fst) {
        Select fstDropdown = new Select(fstSelect);
        fstDropdown.selectByVisibleText(fst);
    }
}
