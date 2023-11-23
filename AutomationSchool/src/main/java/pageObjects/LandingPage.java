package pageObjects;

import baseClass.BaseClass;
import baseClass.ValidationLogs;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.io.FileInputStream;
import java.util.List;

public class LandingPage extends ValidationLogs {
    WebDriver driver;
    BaseClass baseClass;

    //Constructor to initialize the driver
    public LandingPage(WebDriver driver) {
        super();
        this.driver = driver;

        PageFactory.initElements(driver, this);
        baseClass = new BaseClass(driver);
    }

    WebElement addUserBtn = driver.findElement(By.xpath("//button[@type='add']"));
    WebElement addUserFormHeaderLbl = driver.findElement(By.xpath("//div[@class='modal-header']"));
    WebElement firstNameTxt = driver.findElement(By.xpath("//input[@name='FirstName']"));
    WebElement lastNameTxt = driver.findElement(By.xpath("//input[@name='LastName']"));
    WebElement userNameTxt = driver.findElement(By.xpath("//input[@name='UserName']"));
    WebElement passwordTxt = driver.findElement(By.xpath("//input[@name='Password']"));
    WebElement custCompanyAAARadio = driver.findElement(By.xpath("/html/body/div[2]/div[2]/form/table/tbody/tr[5]/td[2]/label[1]/input"));
    WebElement custCompanyBBBRadio = driver.findElement(By.xpath("/html/body/div[2]/div[2]/form/table/tbody/tr[5]/td[2]/label[2]/input"));
    WebElement roleSelect = driver.findElement(By.xpath("//select[@name='RoleId']"));
    WebElement emailTxt = driver.findElement(By.xpath("//input[@name='Email']"));
    WebElement cellPhoneTxt = driver.findElement(By.xpath("//input[@name='Mobilephone']"));
    WebElement saveBtn = driver.findElement(By.xpath("//button[@class='btn btn-success']"));
    WebElement closeBtn = driver.findElement(By.xpath("//button[@class='btn btn-danger']"));

    public void addUsersToTheList()  throws Exception{

            String excelFilePath = "./testData/Users.xlsx";
            FileInputStream inputStream = new FileInputStream(excelFilePath);

            XSSFWorkbook wb = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = wb.getSheetAt(0);

            int rows = sheet.getLastRowNum();
            int cols = sheet.getRow(1).getLastCellNum();

            for (int r = 0; r <= rows; r++) {
                XSSFRow row = sheet.getRow(r);

                String fname = row.getCell(0).getStringCellValue();
                String lname = row.getCell(1).getStringCellValue();
                String uname = row.getCell(2).getStringCellValue();
                String pword = row.getCell(3).getStringCellValue();
                String email = row.getCell(6).getStringCellValue();
                String cell = row.getCell(7).getStringCellValue();

            baseClass.clickObject(addUserBtn);
            baseClass.captureText(firstNameTxt, fname);
            baseClass.captureText(lastNameTxt, lname);
            baseClass.captureText(userNameTxt, uname);
            baseClass.captureText(passwordTxt, pword);
            baseClass.clickObject(custCompanyAAARadio);
            baseClass.selectOptionByText(roleSelect, "Admin");
            baseClass.captureText(emailTxt, email);
            baseClass.captureText(cellPhoneTxt, cell);
            baseClass.CaptureScreenshot(driver, "Capture user details");
            baseClass.clickObject(saveBtn);
        }
    }

    public void validateUserListTableView() {
        try {

            WebElement userListTbl = driver.findElement(By.xpath("/html/body/table/thead/tr[3]"));
            List<WebElement> tableColumns = userListTbl.findElements(By.tagName("th"));

            int columnCount = tableColumns.size();
            for (int column = 0; column < columnCount; column++) {
                String celText = tableColumns.get(column).getText();
                if (column == 0) {
                    logStringValidation("Verify column name: First Name", "First Name", celText);
                } else if (column == 1) {
                    logStringValidation("Verify column name: Last Name", "Last Name", celText);
                } else if (column == 2) {
                    logStringValidation("Verify column name: User Name", "User Name", celText);
                } else if (column == 3) {
                    logStringValidation("Verify column name: ", "", celText);
                } else if (column == 4) {
                    logStringValidation("Verify column name: Customer", "Customer", celText);
                } else if (column == 5) {
                    logStringValidation("Verify column name: Role", "Role", celText);
                } else if (column == 6) {
                    logStringValidation("Verify column name: E-mail", "E-mail", celText);
                } else if (column == 7) {
                    logStringValidation("Verify column name: Cell Phone", "Cell Phone", celText);
                } else if (column == 8) {
                    logStringValidation("Verify column name: Locked", "Locked", celText);
                } else if (column == 9) {

                } else if (column == 10) {

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
