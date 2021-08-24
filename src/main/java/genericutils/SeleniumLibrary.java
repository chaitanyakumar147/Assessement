package genericutils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static genericutils.Reporter.reportLog;

public class SeleniumLibrary implements IAutoConstant {

    private WebDriver driver;

    public SeleniumLibrary(WebDriver driver) {
        this.driver = driver;
    }

    public Object runScript(String script) {
        return ((JavascriptExecutor) driver).executeScript(script);
    }

    public void waitForPageToLoad() {
        FluentWait<WebDriver> fWait = new FluentWait<WebDriver>(driver)
                .pollingEvery(Duration.ofMillis(FPP))
                .withTimeout(Duration.ofSeconds(FTO))
                .ignoring(NoSuchElementException.class);
        fWait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                String state = (String) runScript("return document.readyState");
                return state.equals("complete");
            }
        });
        reportLog("INFO", "[PASSED] Successfully waited for page to load.");
    }

    public void waitForElementToBePresent(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(ETO)).until(ExpectedConditions.visibilityOf(element));
        reportLog("INFO", "[PASSED] Successfully waited with explicit time out of 10 seconds for " + element + " to be visible.");
    }

    public void waitForElementToAppear(WebElement element) {
        int timeCount = 0;
        while (timeCount < 20) {
            try {
                element.isDisplayed();
                break;
            } catch (Exception e) {
                threadTimeOut(FPP);
                timeCount++;
            }
        }
        reportLog("INFO", "[PASSED] Successfully waited for " + ((timeCount * FPP) / 1000) + " seconds for " + element + " to appear.");
    }

    public void waitAndClick(WebElement element) {
        int timeCount = 0;
        while (timeCount < 20) {
            try {
                element.click();
                break;
            } catch (Exception e) {
                threadTimeOut(FPP);
                timeCount++;
            }
        }
        reportLog("INFO", "[PASSED] Successfully waited for " + ((timeCount * FPP) / 1000) + " seconds for " + element + " to appear and clicked");
    }

    public void threadTimeOut(long milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        reportLog("INFO", "[PASSED] Successfully waited for " + (milliSeconds / 1000) + " seconds.");
    }

    public void type(WebElement element, String data) {
        new WebDriverWait(driver, Duration.ofSeconds(ETO)).until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(data);
        System.out.println("[PASSED] the data : \"" + data + "\" entered successfully in the field located by element " + element);
        reportLog("INFO", "[PASSED] The data : \"" + data + "\" entered successfully in the field located by element " + element);
    }

    public void typeAndEnter(WebElement element, String data) {
        new WebDriverWait(driver, Duration.ofSeconds(ETO)).until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(data, Keys.ENTER);
        System.out.println("[PASSED] the data : \"" + data + "\" entered successfully in the field located by element " + element);
        reportLog("INFO", "[PASSED] The data : \"" + data + "\" entered successfully in the field located by element " + element);
    }

    public void clearAndType(WebElement element, String data) {
    	new WebDriverWait(driver, Duration.ofSeconds(ETO)).until(ExpectedConditions.visibilityOf(element));
        element.clear();
        System.out.println("[PASSED] the field has been successfully cleared located by the element " + element);
        reportLog("INFO", "[PASSED] The field has been successfully cleared located by the element " + element);
        element.sendKeys(data);
        System.out.println("[PASSED] the data : \"" + data + "\" entered successfully in the field located by element " + element);
        reportLog("INFO", "[PASSED] The data : \"" + data + "\" entered successfully in the field located by element " + element);
    }

    public void selectOption(WebElement element, String text) {
        new Select(element).selectByVisibleText(text);
    }

    public void selectOption(WebElement element, int index) {
        new Select(element).selectByIndex(index);
    }

    public boolean selectDynamicOption(WebElement element, String text) {
        Select sel = new Select(element);
        List<WebElement> options = sel.getOptions();
        for (WebElement option : options) {
            if (option.getText().equals(text)) {
                sel.selectByVisibleText(text);
                return true;
            }
        }
        return false;
    }

    public String acceptAlert() {
        new WebDriverWait(driver, Duration.ofSeconds(ETO)).until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        return alertText;
    }

    public String dismissAlert() {
        new WebDriverWait(driver, Duration.ofSeconds(ETO)).until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.dismiss();
        return alertText;
    }

    public void sendTextToAlert(String text) {
        new WebDriverWait(driver, Duration.ofSeconds(ETO)).until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(text);
    }

    private Set<String> windowHandles;
    private String parentId;
    private String childId;

    private Set<String> switchWindow() {
        windowHandles = driver.getWindowHandles();
        Iterator<String> iterator = windowHandles.iterator();
        parentId = iterator.next();
        childId = iterator.next();
        return windowHandles;
    }

    public void switchToChildWindow() {
        switchWindow();
        driver.switchTo().window(childId);
    }

    public void switchToChildWindow(String title) {
        switchWindow();
        windowHandles.remove(parentId);
        for (String window : windowHandles) {
            driver.switchTo().window(window);
            if (driver.getTitle().contains(title))
                break;
        }
    }

    public void switchToParentWindow() {
        driver.switchTo().window(parentId);
    }

    public String takeSnap() {
        String path = System.getProperty("user.dir") + "/reports/images/" + (long) Math.floor(Math.random() * 900000000L) + 10000000L + ".jpg";
        try {
            FileUtils.copyFile(((RemoteWebDriver) driver).getScreenshotAs(OutputType.FILE), new File(path));
        } catch (WebDriverException | IOException e) {
            e.printStackTrace();
        }
        return path;
    }
}
