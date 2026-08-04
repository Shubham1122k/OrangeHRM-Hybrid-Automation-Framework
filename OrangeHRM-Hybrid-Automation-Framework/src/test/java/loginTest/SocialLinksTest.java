package loginTest;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseTest.TestBase;
import loginModule.LogIn;
import utilities.BrowserUtils;

public class SocialLinksTest extends TestBase {

    LogIn login;

    @BeforeMethod
    public void setUpPage() {
        login = new LogIn(getDriver());
    }

    @Test
    public void verifyLinkedInLink() {

        String parent = getDriver().getWindowHandle();

        login.clickLinkedIn();

        BrowserUtils.switchToNewTab();

        Assert.assertTrue(getDriver().getCurrentUrl().contains("linkedin"));

        BrowserUtils.closeCurrentTabAndReturn(parent);
    }

    @Test
    public void verifyFacebookLink() {

        String parent = getDriver().getWindowHandle();

        login.clickFacebook();

        BrowserUtils.switchToNewTab();

        Assert.assertTrue(getDriver().getCurrentUrl().contains("facebook"));

        BrowserUtils.closeCurrentTabAndReturn(parent);
    }

    @Test
    public void verifyTwitterLink() {

        String parent = getDriver().getWindowHandle();

        login.clickTwitter();

        BrowserUtils.switchToNewTab();

        String url = getDriver().getCurrentUrl().toLowerCase();

        Assert.assertTrue(url.contains("twitter") || url.contains("x.com"));

        BrowserUtils.closeCurrentTabAndReturn(parent);
    }

    @Test
    public void verifyYoutubeLink() {

        String parent = getDriver().getWindowHandle();

        login.clickYoutube();

        BrowserUtils.switchToNewTab();

        Assert.assertTrue(getDriver().getCurrentUrl().contains("youtube"));

        BrowserUtils.closeCurrentTabAndReturn(parent);
    }
}