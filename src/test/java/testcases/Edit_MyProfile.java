package testcases;

import dataobjects.Account;
import dataobjects.Employee;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.MyInfoPage;
import pages.PIM_AddEmployeePage;
import reportConfig.CustomLogger;
import reportConfig.ExtentManager;
import utils.helpers.Common;
import utils.helpers.DataHelper;
import utils.helpers.TestListener;

@Listeners(TestListener.class)
public class Edit_MyProfile extends TestBase {

    @Test(dataProvider = "jsonDataProvider", description = "Verify that admin can delete multiple employees")
    public void OHRM_EDIT_MY_PROFILE_TC011(DataHelper data) {
        CustomLogger logger = new CustomLogger(ExtentManager.getTest());

        Account account = data.getData("valid_account", Account.class);
        Employee employee = DataPreparation.generateEmployee();
        String PIMMenuItem = data.getStringData("PIMMenuItem");

        logger.info("Pre-condition #: An employees is created with login detail");
        LoginPage preloginPage = new LoginPage();
        preloginPage.login(account);

        DashboardPage predashboardPage = new DashboardPage();
        predashboardPage.selectMenuItem(PIMMenuItem);

        PIM_AddEmployeePage pim_addEmployeePage = new PIM_AddEmployeePage();
        pim_addEmployeePage.clickAddNewEmployee();

        pim_addEmployeePage.enterEmployeeInfo(employee);
        pim_addEmployeePage.toggleCreateLoginDetails();
        pim_addEmployeePage.inputUserLoginDetail(employee);

        pim_addEmployeePage.clickBtnSave();
        pim_addEmployeePage.logOut();
        logger.info("Finish Pre-condition #: An employees is created with login detail");

        logger.info("Step #1: Navigate to OrangeHRM login page");
        logger.info("Step #2: Login with valid account");
        LoginPage loginPage = new LoginPage();
        loginPage.login(account);
        logger.info("Step #3: On left panel, click on My Info");
        String MyInfoMenuItem = data.getStringData("MyInfoMenuItem");
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.selectMenuItem(MyInfoMenuItem);

        logger.info("Step #4: Edit Personal Details fields with Nationality: Vietnamese");
        String Nationality = data.getStringData("nationalityDropdown");
        String optionNationality = data.getStringData("optionNationality");
        MyInfoPage myInfoPage = new MyInfoPage();
        myInfoPage.selectDropdown(Nationality, optionNationality);

        logger.info("Step #5: Click Save button");
        myInfoPage.SavePersonalDetail();

        logger.info("VP: Green message appears.");
        System.out.println(myInfoPage.getMessageColor());
        String colorExpected = data.getStringData("greenColor");
        boolean isGreen = Common.isBackgroundColorExpected(myInfoPage.getMessageColor(), colorExpected);
        Assert.assertEquals(isGreen, true);

        logger.info("Step #6: Edit Custom fields with Blood Type: A+");
        String BloodType = data.getStringData("bloodTypeDropdown");
        String optionBloodType = data.getStringData("optionBloodType");
        myInfoPage.selectDropdown(BloodType, optionBloodType);

        logger.info("Step #7: Click Save button");
        myInfoPage.SaveCustomField();
        logger.info("VP: Green message appears.");
        boolean isGreen1 = Common.isBackgroundColorExpected(myInfoPage.getMessageColor(), colorExpected);
        Assert.assertEquals(isGreen1, true);
    }

}
