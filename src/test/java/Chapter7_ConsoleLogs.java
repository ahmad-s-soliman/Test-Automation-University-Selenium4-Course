import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.log.Log;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Chapter7_ConsoleLogs {
    EdgeDriver driver;

    @BeforeClass
    public void setUp(){
        WebDriverManager.edgedriver().setup(); //try commenting this and see if it runs
        driver = new EdgeDriver();
        driver.manage().window().maximize();

    }

    @Test
    public void viewBrowserConsoleLogs(){
        //get the devtools and create a session
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        //enable the console logs
        devTools.send(Log.enable());

        //add a listener for the console logs
        devTools.addListener(Log.entryAdded(), logEntry -> {
            System.out.println("----------");
            System.out.println("Level: " + logEntry.getLevel());
            System.out.println("Text: " + logEntry.getText());
            System.out.println("Broken URL: " + logEntry.getUrl());
        });

        //load the aut (application under test)
        driver.get("https://the-internet.herokuapp.com/broken_images");

    }
}
