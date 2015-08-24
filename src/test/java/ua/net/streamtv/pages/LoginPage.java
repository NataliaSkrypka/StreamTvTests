package ua.net.streamtv.pages;

import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by nskrypka on 8/19/2015.
 */
public class LoginPage extends FluentPage {

    private WebDriver driver;
    private static final String LOGIN_INPUT = "input[placeholder=Login]";

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[text()='Login']")
    private WebElement loginButton;
    private String baseUrl;
    private String login;
    private String password;

    public LoginPage(WebDriver driver, String baseUrl, String login, String password) {
        super(driver);
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
        await().atMost(3, TimeUnit.SECONDS).until(LOGIN_INPUT).areDisplayed();
        find(LOGIN_INPUT).text(login);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        loginButton.click();
    }
}
