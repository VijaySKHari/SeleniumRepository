package ListenersImplementation;

import java.io.FileInputStream;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsClass {

	public ExtentReports extentReports() {

		String path = System.getProperty("user.dir") + "//ExtentsReportFolder/extentReport.html";
		ExtentSparkReporter spark = new ExtentSparkReporter(path);
		spark.config().setDocumentTitle("DOCUMENTATION TILE IS VIJAY");
		spark.config().setReportName("REPORTER NAME IS VIJAYHARI SK");

		ExtentReports report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("username", "vijayhari sk");
		return report;

	}

}
