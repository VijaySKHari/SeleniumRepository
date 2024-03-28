package ListenersImplementation;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import BaseClass.BaseClass;

public class Listeners extends BaseClass implements ITestListener {
	ExtentReportsClass extentReports = new ExtentReportsClass();
	ExtentReports reports = extentReports.extentReports();
	ThreadLocal<ExtentTest> threadLocal = new ThreadLocal();
	ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {
		test = reports.createTest(result.getMethod().getMethodName());
		threadLocal.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailure(ITestResult result) {

		threadLocal.get().fail(result.getThrowable());

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}

		String sreenShot = getSreenShot(driver, result.getMethod().getMethodName());
		threadLocal.get().addScreenCaptureFromPath(sreenShot, result.getMethod().getMethodName());
	}

	@Override
	public void onFinish(ITestContext context) {
		reports.flush();
	}
}
