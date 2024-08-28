package rahulshettyacademyInterview.TestComponents;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademy.resources.ExtentReporterNG;

public class Listeners implements ITestListener{
	  ExtentTest test;
      ExtentReports extent = ExtentReporterNG.getReport();
      public void onTestStart(ITestResult result) {
    	 test =  extent.createTest(result.getMethod().getMethodName());
      }
      public void onTestSuccess(ITestResult result) {
    	  test.log(Status.PASS, "Test Passed");
      }
      public void onTestFailure(ITestResult result) {
    	  test.fail(result.getThrowable());
    	  test.addScreenCaptureFromPath(null);
      }
}
