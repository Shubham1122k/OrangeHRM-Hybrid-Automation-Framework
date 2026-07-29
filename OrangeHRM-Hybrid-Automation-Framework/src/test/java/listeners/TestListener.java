package listeners;



import org.testng.ITestListener;
import org.testng.ITestResult;

import utilities.ScreenshotUtil;
import QABootcamp_Maven.OrangeHRM.TestBase;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

    	ScreenshotUtil.captureScreenshot(
    	        TestBase.getDriver(),
    	        result.getName()
    	);

        System.out.println("Screenshot taken for: "
                + result.getName());
    }
}
