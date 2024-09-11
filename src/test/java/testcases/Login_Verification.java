package testcases;

import dataobjects.Account;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import reportConfig.CustomLogger;
import utils.helpers.DataHelper;
import utils.helpers.TestListener;

@Listeners(TestListener.class)
public class Login_Verification extends TestBase {

    @Test(dataProvider = "jsonDataProvider", description = "Verify that user can login successfully with correct credentials")
    public void OHRM_LOGIN_TC001(DataHelper data) {
        CustomLogger logger = new CustomLogger();

        String mainPageLabel = data.getStringData("mainPageLabel");
        Account account = data.getData("valid_account", Account.class);

        logger.info("Step #1: Navigate to OrangeHRM page");
        LoginPage loginPage = new LoginPage();

        logger.info("Step #2: Login with valid username and password");
        loginPage.login(account);

        logger.info("VP: Verify that OrangeHRM main page appear");
        DashboardPage dashboardPage = new DashboardPage();
        Assert.assertEquals(dashboardPage.getDashboardLabel(), mainPageLabel);
    }
}
