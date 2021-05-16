package utility;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import tests.SimpleFormTests;

public class TestListener implements ITestListener {
	private final static Logger LOGGER = LogManager.getLogger(TestListener.class);

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		LOGGER.info("Test case started: " + getTestMethodName(result));
		System.out.println("Test case started: " + getTestMethodName(result));
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		LOGGER.info("Test case passed: " + getTestMethodName(result));
		System.out.println("Test case passed: " + getTestMethodName(result));
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		LOGGER.info("Test case failed: " + getTestMethodName(result));
		System.out.println("Test case failed: " + getTestMethodName(result));
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		LOGGER.info("Test case skipped: " + getTestMethodName(result));
		System.out.println("Test case skipped: " + getTestMethodName(result));
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		LOGGER.info("Web automation started");
		System.out.println("Web automation started");
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
//		EmailManager.sendEmail(EmailManager.setupEmailBody());
		LOGGER.info("Web automation ended ");
		System.out.println("Web automation ended ");
	}
	
	/**
	 * 
	 * @param iTestResult
	 * @return
	 */
	private String getTestMethodName(ITestResult iTestResult) {
		String className = iTestResult.getTestClass().getName();
		className = className.substring(className.lastIndexOf('.') + 1);
		return className + " - " + iTestResult.getMethod().getConstructorOrMethod().getName();
	}

}
