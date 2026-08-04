package utilities;

import java.time.Duration;

import baseTest.TestBase;

public class BrowserUtils extends TestBase {

    // Wait until a new tab/window opens
    public static void waitForTabToOpen() {

        long start = System.currentTimeMillis();

        while (getDriver().getWindowHandles().size() == 1) {

            if (System.currentTimeMillis() - start > Duration.ofSeconds(5).toMillis()) {
                throw new RuntimeException("New tab did not open within 5 seconds.");
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // Switch to newly opened tab
    public static void switchToNewTab() {

        String parent = getDriver().getWindowHandle();

        waitForTabToOpen();

        for (String window : getDriver().getWindowHandles()) {

            if (!window.equals(parent)) {
                getDriver().switchTo().window(window);
                return;
            }
        }

        throw new RuntimeException("Unable to switch to new tab.");
    }

    // Switch back to parent window
    public static void switchToParentWindow(String parentWindow) {
        getDriver().switchTo().window(parentWindow);
    }

    // Close current tab and return to parent
    public static void closeCurrentTabAndReturn(String parentWindow) {

        getDriver().close();
        getDriver().switchTo().window(parentWindow);
    }
}