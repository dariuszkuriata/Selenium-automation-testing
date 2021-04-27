import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class KeyboardAndMouseInput {

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
    public void KeyPress(){

        driver.get("https://formy-project.herokuapp.com/keypress");

        WebElement name = driver.findElement(By.id("name"));
        name.click();
        name.sendKeys("Dariusz Kuriata");
        WebElement button = driver.findElement(By.id("button"));
        button.click();

    }

    @Test
    public void AutoComplete(){

        driver.get("https://formy-project.herokuapp.com/autocomplete");

        WebElement autocomplete = driver.findElement(By.id("autocomplete"));
        autocomplete.sendKeys("Opolska 155, Wrocław, Polska");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement autocompleteResult = driver.findElement(By.className("pac-item"));
        autocompleteResult.click();

    }



}
