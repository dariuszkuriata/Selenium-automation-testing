import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PageTitleAssertion {

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
    public void navigate2(){

        driver.navigate().to("https://dariuszkuriatacv.netlify.app/");
        driver.navigate().to("https://dariuszkuriatacveng.netlify.app/");
        driver.navigate().back();
        String cvTitle = "Moje CV";
        Assertions.assertEquals(cvTitle, driver.getTitle(), "The title of the page is not: " + cvTitle);
        driver.navigate().forward();
        String cvengTitle = "My Resume";
        Assertions.assertEquals(cvengTitle, driver.getTitle(), "The title of the page is not: " + cvengTitle);

    }

    @Test
    public void getCurrentURLExample(){


        String googleUrl = "https://www.google.pl/";
        driver.navigate().to("https://google.pl");
        Assertions.assertEquals(googleUrl, driver.getCurrentUrl(), "Current URL is not: " + googleUrl);

    }

    @Test
    public void getTitleExample(){


        String googleTitle = "Google";
        driver.navigate().to("https://google.pl");
        Assertions.assertEquals(googleTitle, driver.getTitle(), "The page title is not: " + googleTitle);

    }

    @Test
    public void getPageSourceExample(){


        String pageImg = "/images/design/favicon.ico";
        driver.navigate().to("https://serwisbilinski.pl");
        Assertions.assertTrue(driver.getPageSource().contains(pageImg), "Page source does not contain: " + pageImg);

    }



    @Test
    public void getPageSourceExample2(){


        String pageImg = "/images/photo.jpg";
        driver.navigate().to("https://dariuszkuriatacveng.netlify.app/");
        Assertions.assertTrue(driver.getPageSource().contains(pageImg), "Page source does not contain: " + pageImg);

    }

    @Test
    public void getPageSourceExample3(){


        driver.navigate().to("https://dariuszkuriatacv.netlify.app/");
        Assertions.assertTrue(driver.getPageSource().contains("lang=\"pl-PL\""), "Page source does not contain lang=\"pl-PL\"");

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
