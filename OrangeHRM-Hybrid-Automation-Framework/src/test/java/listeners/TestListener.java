package listeners;



import org.testng.ITestListener;
import org.testng.ITestResult;

import baseTest.TestBase;
import utilities.ScreenshotUtils;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

    	ScreenshotUtils.captureScreenshot(
    	        TestBase.getDriver(),
    	        result.getName()
    	);

        System.out.println("Screenshot taken for: "
                + result.getName());
    }
}
