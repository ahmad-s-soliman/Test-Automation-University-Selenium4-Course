import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Chapter5_ElementPosition{

    WebDriver driver;
    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://testautomationu.applitools.com/learningpaths.html");
    }

    @Test
    public void getPositionDimension(){
        WebElement logoTAU = driver.findElement(By.className("logo"));
        Rectangle rectLogoTau = logoTAU.getRect();
        System.out.println("x: " + rectLogoTau.getX());
        System.out.println("Y: " + rectLogoTau.getY());
        System.out.println("width: " + rectLogoTau.getWidth());
        System.out.println("height: " + rectLogoTau.getHeight());
    }

}
