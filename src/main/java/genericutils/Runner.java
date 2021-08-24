package genericutils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Runner {

    protected static final ThreadLocal<Runner> driverThreadLocal = new ThreadLocal<Runner>();
    private WebDriver driver;


    public void setDriver(Runner runner) {
        driverThreadLocal.set(runner);
    }

    public WebDriver getDriver() {
        return driverThreadLocal.get().driver;
    }

    public WebDriver launchBrowser() {

        ChromeOptions options = new ChromeOptions();
        /* options.setHeadless(true);*/
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        Runner runner = new Runner();
        runner.driver = driver;
        setDriver(runner);

        return getDriver();
    }

}
