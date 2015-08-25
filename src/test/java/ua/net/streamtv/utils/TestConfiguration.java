package ua.net.streamtv.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import ua.net.streamtv.steps.ActionsWithSportsmanSteps;
import ua.net.streamtv.steps.ApiSteps;
import ua.net.streamtv.steps.FileComparasionSteps;
import ua.net.streamtv.steps.LoginSteps;

/**
 * Created by nskrypka on 8/20/2015.
 */
@Configuration
@PropertySource(value = "classpath:config.properties")
public class TestConfiguration {

    @Autowired
    private WebDriver driver;

    @Value("${base.url}")
    private String baseUrl;
    @Value("${login}")
    private String login;
    @Value("${password}")
    private String password;
    @Value("${api.create.url}")
    private String createUrl;
    @Value("${api.login.url}")
    private String loginUrl;
    @Value("${api.read.url}")
    private String readUrl;
    @Value("${api.search.url}")
    private String searchUrl;
    @Value("${api.update.url}")
    private String updateUrl;
    @Value("${api.delete.url}")
    private String deleteUrl;

    @Bean(destroyMethod = "quit")
    public WebDriver driver() {
        String desiredDriver = System.getProperty("driver");
        if ("chrome".equals(desiredDriver)) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/drivers/chromedriver.exe");
            return new ChromeDriver();
        } else {
            return new FirefoxDriver();
        }

    }

    @Bean
    public LoginSteps loginSteps() {
        return new LoginSteps(driver, baseUrl, login, password);
    }

    @Bean
    public ActionsWithSportsmanSteps actionsWithSportsmanSteps() {
        return new ActionsWithSportsmanSteps(driver);
    }

    @Bean
    public ApiSteps apiSteps() {
        return new ApiSteps(createUrl, login, password, loginUrl, readUrl, searchUrl, updateUrl, deleteUrl);
    }

    @Bean
    FileComparasionSteps fileComparasionSteps() {
        return new FileComparasionSteps();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
