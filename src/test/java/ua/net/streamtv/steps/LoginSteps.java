package ua.net.streamtv.steps;

import org.openqa.selenium.WebDriver;
import ua.net.streamtv.pages.LoginPage;

/**
 * Created by nskrypka on 8/20/2015.
 */
public class LoginSteps {

    private WebDriver driver;
    private String baseUrl;
    private String login;
    private String password;

    public LoginSteps(WebDriver driver, String baseUrl, String login, String password) {
        this.driver = driver;
        this.baseUrl = baseUrl;
        this.login = login;
        this.password = password;
        loginPage = new LoginPage(driver, baseUrl, login, password);
    }

    LoginPage loginPage;

    public void login() {
        loginPage.openSite();
        loginPage.login();
    }
}
