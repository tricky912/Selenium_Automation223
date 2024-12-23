package utilities;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;


import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import testBase.CommonScenarios;

public class ExtentReportUtility implements ITestListener{
	public static ExtentReports extent;
	public static WebDriver driver;

	    private static ExtentReports extentReports = ExtentReportUtility.initializeReport();
	    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	    /**
	     * Invoked when a test case starts.
	     */
	    public void onTestStart(ITestResult result) {
	        String testName = result.getMethod().getMethodName();
	        ExtentTest test = extentReports.createTest(testName);
	        extentTest.set(test);
	        extentTest.get().log(Status.INFO, "Test Started: " + testName);
	    }

	    /**
	     * Invoked when a test case passes.
	     */
	    public void onTestSuccess(ITestResult result) {
	        String testName = result.getMethod().getMethodName();
	        extentTest.get().log(Status.PASS, "Test Passed: " + testName);
	    }

	    /**
	     * Invoked when a test case fails.
	     */
	    public void onTestFailure(ITestResult result) {
	        String testName = result.getMethod().getMethodName();
	        extentTest.get().log(Status.FAIL, "Test Failed: " + result.getThrowable());

	        // Capture screenshot
	        Object testClass = result.getInstance();
	        CommonScenarios baseTest = (CommonScenarios) testClass;

	        try {
	            String screenshotPath = baseTest.captureScreenshot(testName);
	            extentTest.get().addScreenCaptureFromPath(screenshotPath, "Screenshot for Failure");
	        } catch (IOException e) {
	            extentTest.get().log(Status.FAIL, "Failed to capture screenshot: " + e.getMessage());
	        }
	    }

	    /**
	     * Invoked when a test case is skipped.
	     */
	    public void onTestSkipped(ITestResult result) {
	        String testName = result.getMethod().getMethodName();
	        extentTest.get().log(Status.SKIP, "Test Skipped: " + result.getThrowable());
	    }

	    /**
	     * Invoked when a test starts at the suite level.
	     */
	    public void onStart(ITestContext context) {
	        System.out.println("Test Suite Execution Started: " + context.getName());
	    }

	    /**
	     * Invoked when the test suite execution finishes.
	     */
	    public void onFinish(ITestContext context) {
	        System.out.println("Test Suite Execution Finished: " + context.getName());
	        extentReports.flush();
	    }
	    
	    public static ExtentReports initializeReport() {
	        if (extent == null) {
	            // Generate report file with timestamp
	            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
	            String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport_" + timestamp + ".html";

	            // Initialize ExtentSparkReporter for HTML report generation
	            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
	            sparkReporter.config().setReportName("Automation Test Report");
	            sparkReporter.config().setDocumentTitle("Test Execution Report");

	            // Initialize ExtentReports
	            extent = new ExtentReports();
	            extent.attachReporter(sparkReporter);

	            // Add system/environment details
	            extent.setSystemInfo("Tester", "Your Name");
	            extent.setSystemInfo("Operating System", System.getProperty("os.name"));
	            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
	        }
	        return extent;
	    }


}
