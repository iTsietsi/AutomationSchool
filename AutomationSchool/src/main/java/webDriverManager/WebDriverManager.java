package webDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utilities.ReadConfig;

import java.util.concurrent.TimeUnit;

public class WebDriverManager {
    WebDriver driver;
    ReadConfig readConfig;

    public WebDriverManager() {
        readConfig = new ReadConfig("config");
    }

    public WebDriver getWebDriver(String brws) {

        if (brws.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", readConfig.getChromePath());
            driver = new ChromeDriver();

        }else if (brws.equalsIgnoreCase("FF") || brws.equalsIgnoreCase("Firefox")) {
            System.setProperty("webdriver.gecko.driver", readConfig.getFFPath());
            driver = new FirefoxDriver();
        }
        return driver;
    }

    public void setURL(String baseURL) {
        if (baseURL != null && !baseURL.isEmpty()) {
            driver.get(readConfig.getURL());
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
           // driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.getWindowHandles();
            //driver.get(baseURL);

/*            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setAcceptInsecureCerts(true);*/
        }
    }


}
