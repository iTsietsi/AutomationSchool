package baseClass;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Reporting;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

public class BaseClass {
    Reporting reporting;

    public static WebDriver driver;
    public static Logger narrator = Logger.getLogger(BaseClass.class.getName());

    // Constructor to initialize the driver
    public BaseClass(WebDriver driver) {
        reporting = new Reporting();
        this.driver = driver;
    }

    // user defined method for capturing screenshot
    public void CaptureScreenshot(WebDriver driver, String tname) throws Exception {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File target = new File(System.getProperty("user.dir") + "\\Screenshots\\" + tname + " " + timeStamp + ".png");
        FileUtils.copyFile(source, target);
        System.out.println("Screenshot taken");
    }

    // Custom libraries for object handling
    public  String getObjectText(WebElement element, String objText){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    public void objectDisplayed(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(element));
        element.isDisplayed();
    }

    public void clearText(WebElement element, String text){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
    }

    public void captureText(WebElement element, String text){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys();
    }

    public void clickObject(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(element));
        new FluentWait<>(driver)
                .ignoring(ElementClickInterceptedException.class)
                .pollingEvery(Duration.ofSeconds(3))
                .withTimeout(Duration.ofSeconds(15))
                .until((Object args) -> {

                    element.click();


                    return true;
                });
        //element.click();
    }

    public void selectOptionByText(WebElement element, String optionText){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(element));
        Select slt = new Select(element);
        slt.selectByVisibleText(optionText);
    }

    public void selectOptionByID(WebElement element, String id){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(element));
        Select slt = new Select(element);
    }

    public void selectOptionByValue(WebElement element, String optionValue){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(element));
        Select slt = new Select(element);
        slt.selectByValue(optionValue);
    }

    void selectOptionByIndex(WebElement element, int optionIndex){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(element));
        Select slt = new Select(element);
        slt.selectByIndex(optionIndex);
    }


    public void logInfo(String message) { narrator.info(message);}

    public void logWarning(String message) { narrator.warning(message);}

    public String getTime(){
        Calendar calendar = Calendar.getInstance();

        return new SimpleDateFormat(" dd MM yyyy hh:mm:ss").format(calendar.getTime());
    }

    public String getDay(int days){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return new SimpleDateFormat("d").format(calendar.getTime());
    }

    public String getMonth(int months){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, months);
        return new SimpleDateFormat("MMM").format(calendar.getTime());
    }

    public String getYear(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, year);
        return new SimpleDateFormat("yyyy").format(calendar.getTime());
    }
}
