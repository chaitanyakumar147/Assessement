package genericutils;

import java.util.Properties;

import org.testng.annotations.*;

@Listeners(Listener.class)
public class SeleniumBase extends Runner {

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {

    }

    @BeforeTest
    public void beforeTest() {
        launchBrowser();
        getDriver().get(PropertiesLibrary.getValue("./resource/webUrl.properties", "URL"));
    }

    @AfterTest
    public void afterTest() {
        getDriver().quit();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {

    }

}
