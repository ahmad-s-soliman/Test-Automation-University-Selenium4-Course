import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class Chapter4_WindowAndTabManagement {
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
    public void testWorkingInBothWindowTabs(){
        //automatically open and switch to the new window or tab
        driver.switchTo().newWindow(WindowType.TAB).get("https://www.orangehrm.com/?_gl=1*1ms7et0*_up*MQ..*_ga*MzUzMTk4MTk2LjE3MTcwODY3MTk.*_ga_6C6T9MXTRT*MTcxNzA4NjcxNy4xLjAuMTcxNzA4NjcxNy4wLjAuMTk0OTU1NjgwMQ..");
        System.out.println("Title is: " + driver.getTitle());

        //work in the new window or tab
        driver.findElement(By.id("Form_submitForm_EmailHomePage")).sendKeys("example@tau.com");
        driver.findElement(By.id("Form_submitForm_action_request")).click();

        //get the window ID handles
        Set<String> allWindowTabs = driver.getWindowHandles();
        Iterator<String> iterator = allWindowTabs.iterator();

        String mainFirstWindow = iterator.next();
        //switch and work in the main window or tab
        driver.switchTo().window(mainFirstWindow);
        System.out.println("Title is:" + driver.getTitle());
    }
}
