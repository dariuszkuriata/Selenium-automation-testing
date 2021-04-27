import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class FillingForms {

    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1920, 1080));
    }

    @AfterEach
    public void driverQuit() {
        driver.close();
        driver.quit();
    }



    @Test
    public void Form(){

        driver.get("https://formy-project.herokuapp.com/form");

        WebElement firstName = driver.findElement(By.id("first-name"));
        firstName.sendKeys("Dariusz");

        WebElement lastName = driver.findElement(By.id("last-name"));
        lastName.sendKeys("Kuriata");

        WebElement jobTitle = driver.findElement(By.id("job-title"));
        jobTitle.sendKeys("Software Tester");

        WebElement educationButton = driver.findElement(By.id("radio-button-2"));
        educationButton.click();

        WebElement sexBox = driver.findElement(By.id("checkbox-1"));
        sexBox.click();

        WebElement experienceYears = driver.findElement(By.cssSelector("option[value='1']"));
        experienceYears.click();

        WebElement dateField = driver.findElement(By.id("datepicker"));
        dateField.sendKeys("11/01/2022");
        dateField.sendKeys(Keys.RETURN);

        WebElement submitButton = driver.findElement(By.cssSelector(".btn.btn-lg.btn-primary"));
        submitButton.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert")));


        String alertText = alert.getText();

        Assertions.assertEquals("The form was successfully submitted!",alertText,"Name of the uploaded file is different than expected one");




    }

    @Test
    public void DatePicker(){

        driver.get("https://formy-project.herokuapp.com/datepicker");

        WebElement dateField = driver.findElement(By.id("datepicker"));
        dateField.sendKeys("11/01/2022");
        dateField.sendKeys(Keys.RETURN);


    }

    @Test
    public void DropDown(){

        driver.get("https://formy-project.herokuapp.com/dropdown");

        WebElement dropDownMenu = driver.findElement(By.id("dropdownMenuButton"));
        dropDownMenu.click();

        WebElement autocompleteItem = driver.findElement(By.id("autocomplete"));
        autocompleteItem.click();


    }

    @Test
    public void fileUpload(){

        driver.get("https://formy-project.herokuapp.com/fileupload");

        WebElement fileUploadField = driver.findElement(By.id("file-upload-field"));
        String fileName = "rakieta.png";
        String path = "C:\\" + fileName;
        fileUploadField.sendKeys(path);

    }

    @Test
    public void ImplicitWaiting() {

        driver.get("https://formy-project.herokuapp.com/dropdown");

        WebElement dropDownMenu = driver.findElement(By.id("dropdownMenuButton"));
        dropDownMenu.click();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement autocompleteItem = driver.findElement(By.id("autocomplete"));
        autocompleteItem.click();

    }

    @Test
    public void ExplicitWaiting(){

        driver.get("https://formy-project.herokuapp.com/autocomplete");

        WebElement autocomplete = driver.findElement(By.id("autocomplete"));
        autocomplete.sendKeys("Opolska 155, Wrocław, Polska");

        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement autocompleteResult = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("pac-item")));
        autocompleteResult.click();

    }


}
