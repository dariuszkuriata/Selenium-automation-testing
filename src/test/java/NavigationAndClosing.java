import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class NavigationAndClosing {

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
    public void getMethod(){

        driver.get("https://google.pl");

    }

    @Test
    public void navigate(){

        driver.navigate().to("https://serwisbilinski.pl");
        driver.navigate().to("https://serwisbilinski.pl/oferta-realizacje");
        driver.navigate().to("https://www.serwisbilinski.pl/kontakt");
        driver.navigate().to("https://www.serwisbilinski.pl/polityka-prywatnosci");
        driver.navigate().back();
        driver.navigate().back();
        driver.navigate().back();
        driver.navigate().refresh();

    }











    @Test
    public void testIfLanguageChanged(){



        driver.navigate().to("http://wikipedia.pl");
        driver.findElement(By.cssSelector("a[title='hiszpański']")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assertions.assertTrue(driver.getPageSource().contains("lang=\"es\""), "Page source does not contain lang=\"es\"");
        driver.findElement(By.cssSelector("a[title='polaco']")).click();
        Assertions.assertTrue(driver.getPageSource().contains("lang=\"pl\""), "Page source does not contain lang=\"pl\"");


    }

}
