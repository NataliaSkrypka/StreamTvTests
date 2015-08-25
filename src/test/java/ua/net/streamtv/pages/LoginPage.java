package ua.net.streamtv.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by nskrypka on 8/19/2015.
 */
public class LoginPage {

    private WebDriver driver;
    private static final By LOGIN_INPUT = By.cssSelector("input[placeholder=Login]");

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[text()='Login']")
    private WebElement loginButton;
    private String baseUrl;
    private String login;
    private String password;

    public LoginPage(WebDriver driver, String baseUrl, String login, String password) {
        this.driver = driver;
        this.baseUrl = baseUrl;
        this.login = login;
        this.password = password;
        PageFactory.initElements(driver, this);
    }

    public void openSite() {
        driver.get(baseUrl);
        driver.manage().window().maximize();
    }

    public void login() {
        new WebDriverWait(driver, 3000).until(ExpectedConditions.presenceOfElementLocated(LOGIN_INPUT));
        driver.findElement(LOGIN_INPUT).clear();
        driver.findElement(LOGIN_INPUT).sendKeys(login);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        loginButton.click();
    }
}
