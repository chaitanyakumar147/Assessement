package genericutils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

public abstract class Reporter extends Runner {

    protected static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
    /*protected static ExtentTest extentTest;*/
    protected static ExtentReports reports;

    public static void startReport() {
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(new File("target/Spark/Spark.html"));

        extentSparkReporter.config().setDocumentTitle("Report");
        extentSparkReporter.config().setReportName("Automation");
        extentSparkReporter.config().setTheme(Theme.DARK);
        extentSparkReporter.config().setEncoding("utf-8");
        extentSparkReporter.config().setProtocol(Protocol.HTTPS);
        extentSparkReporter.config().setTimelineEnabled(true);

        reports = new ExtentReports();
        reports.attachReporter(extentSparkReporter);
    }

    public static void reportLog(String status, String details) {
        if (status.toUpperCase().trim().equals("INFO"))
            extentTest.get().log(Status.INFO, details);
        if (status.toUpperCase().trim().equals("WARN"))
            extentTest.get().log(Status.WARNING, details);
        if (status.toUpperCase().trim().equals("PASS"))
            extentTest.get().log(Status.PASS, details);
        if (status.toUpperCase().trim().equals("FAIL"))
            extentTest.get().log(Status.FAIL, details);
        if (status.toUpperCase().trim().equals("SKIP"))
            extentTest.get().log(Status.SKIP, details);
    }

    public static void reportLog(String status, String details, String mediaPath) {
        if (status.toUpperCase().trim().equals("INFO"))
            extentTest.get().log(Status.INFO, details, MediaEntityBuilder.createScreenCaptureFromPath(mediaPath).build());
        if (status.toUpperCase().trim().equals("WARN"))
            extentTest.get().log(Status.WARNING, details, MediaEntityBuilder.createScreenCaptureFromPath(mediaPath).build());
        if (status.toUpperCase().trim().equals("PASS"))
            extentTest.get().log(Status.PASS, details, MediaEntityBuilder.createScreenCaptureFromPath(mediaPath).build());
        if (status.toUpperCase().trim().equals("FAIL"))
            extentTest.get().log(Status.FAIL, details, MediaEntityBuilder.createScreenCaptureFromPath(mediaPath).build());
        if (status.toUpperCase().trim().equals("SKIP"))
            extentTest.get().log(Status.SKIP, details, MediaEntityBuilder.createScreenCaptureFromPath(mediaPath).build());
    }

    public static void stopReport() {
        reports.flush();
    }
}
