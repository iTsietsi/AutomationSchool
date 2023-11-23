package gui.test;

import baseClass.BaseClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.LandingPage;
import pageObjectsManager.PageObjectsManager;
import utilities.ReadConfig;
import utilities.Reporting;
import webDriverManager.WebDriverManager;

public class UserListTableTest {
    static final Logger narrate = LogManager.getLogger(UserListTableTest.class);

    WebDriver driver;
    WebDriverManager webDriverManager;
    ReadConfig readConfig;
    BaseClass baseClass;
    LandingPage landingPage;
    PageObjectsManager pageObjectsManager;
    Reporting reporting;

    public UserListTableTest() {
        webDriverManager = new WebDriverManager();
        readConfig = new ReadConfig("config");
        driver = webDriverManager.getWebDriver(readConfig.getBrowser());
        baseClass = new BaseClass(driver);
        pageObjectsManager = new PageObjectsManager(driver);
        landingPage = pageObjectsManager.getLandingPage();
        reporting = new Reporting();
    }

    @Test
    public void validateUserListTable() {
        try {
            webDriverManager.setURL("QA");
            Thread.sleep(5000);

            landingPage.validateUserListTableView();
            baseClass.CaptureScreenshot(driver, "Validate User List Table");

        } catch (Exception message) {
            message.printStackTrace();
        }
    }

    @Test
    public void addUsersToTheList() {
        try {
            webDriverManager.setURL("QA");
            Thread.sleep(5000);

            landingPage.validateUserListTableView();
            baseClass.CaptureScreenshot(driver, "Validate User List Table");

            landingPage.addUsersToTheList();
            baseClass.CaptureScreenshot(driver, "User added to the list");
        } catch (Exception message) {
            message.printStackTrace();
        }
    }

    @AfterMethod
    void tearDown() {
        driver.quit();
    }
}
