import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class FileUploadExample
{
    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.navigate().to("https://gofile.io/uploadFiles/");

    }

    @AfterEach
    public void driverQuit() {
        driver.close();
        driver.quit();
    }

    @Test
    public void fileUploadTest(){

        WebElement uploadFileInput = driver.findElement(By.cssSelector("input[type='file']"));
        String expectedFilename = "rakieta.png";
        String path = "C:\\" + expectedFilename;
        uploadFileInput.sendKeys(path);

        String actualFileName = driver.findElement(By.cssSelector("td[tabindex='0']")).getText();

        Assertions.assertEquals(expectedFilename,actualFileName,"Name of the uploaded file is different than expected one");
    }





}
