import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class Chapter3_RelativeLocators {
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        By ByLoginForm = By.className("bi-person");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(ByLoginForm));
        System.out.println(driver.getTitle());
    }



    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void testRelativeLocator(){
        By ByLoginForm = By.className("bi-person");
        WebElement loginForm = driver.findElement(ByLoginForm);
        WebElement credentials = driver.findElement(with(By.className("orangehrm-login-error")).above(loginForm));
        System.out.println(credentials.getText());
    }

    @Test
    public void testListOfElements(){
        List<WebElement> allSocialMedia = driver.findElements(with(By.tagName("a"))
                .near(By.className("orangehrm-login-footer-sm")));
        for(WebElement socialMedia : allSocialMedia){
            System.out.println(socialMedia.getAttribute("href"));;
        }
    }

    @Test
    public void testNewWindowTab(){
        WebDriver newWindow = driver.switchTo().newWindow(WindowType.TAB);
        newWindow.get("https://www.linkedin.com/company/orangehrm/");
        System.out.println("Title is:" + driver.getTitle());
    }


}
