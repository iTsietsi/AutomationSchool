package pageObjectsManager;

import org.openqa.selenium.WebDriver;
import pageObjects.LandingPage;

public class PageObjectsManager {
    WebDriver driver;

    private static LandingPage landingPage;

    public PageObjectsManager(WebDriver driver) {
        this.driver = driver;

    }

    public LandingPage getLandingPage(){
        return landingPage = new LandingPage(driver);
    }
}
