import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.GregorianCalendar;
public class ManageCookies {
    WebDriver driver;
    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1290, 730));
        driver.manage().window().setPosition(new Point(8, 31));
        driver.navigate().to("https://www.amazon.com");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @AfterEach
    public void closeAndQuit() {
        driver.close();
        driver.quit();
    }
    @Test
    public void gettingAndDeletingCookies() {
        driver.manage().deleteCookieNamed("session-id");
        Assertions.assertNull(driver.manage().getCookieNamed("session-id"), "Cookie is not deleted.");
        driver.manage().deleteAllCookies();
        Assertions.assertEquals(0, driver.manage().getCookies().size(), "Number of cookies is not what expected.");
    }
    @Test
    public void addingAndDeletingCookies() {
        Cookie newCookie = new Cookie("test_cookie", "test_value", ".amazon.com", "/",
                new GregorianCalendar(2021, 11, 31).getTime(), true, true);
        driver.manage().addCookie(newCookie);
        Assertions.assertNotNull(driver.manage().getCookieNamed("test_cookie"), "Cookie doesn't exist.");
        Cookie secondCookie = new Cookie("test_cookie2", "test_value2");
        driver.manage().addCookie(secondCookie);
        Assertions.assertNotNull(driver.manage().getCookieNamed("test_cookie2"), "Cookie doesn't exist.");
        driver.manage().deleteCookie(newCookie);
        Assertions.assertNull(driver.manage().getCookieNamed("test_cookie"), "Cookie is not deleted.");
    }
    @Test
    public void windowSettings() {
        Point position = driver.manage().window().getPosition();
        Assertions.assertEquals(new Point(8, 30), position, "Position of the window is now what expected.");
        Dimension size = driver.manage().window().getSize();
        Assertions.assertEquals(new Dimension(1292, 734), size, "Size of the window is now what expected.");
        driver.manage().window().fullscreen();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.manage().window().maximize();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
