package testcases;

import dataobjects.Account;
import dataobjects.Employee;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.model.Log;

import pages.DashboardPage;
import pages.LoginPage;
import pages.PIM_AddEmployeePage;
import pages.PIM_EmployeeListPage;
import pages.PIM_ReportsPage;
import reportConfig.CustomLogger;
import utils.helpers.DataFaker;
import utils.helpers.DataHelper;
import utils.helpers.TestListener;

@Listeners(TestListener.class)
public class Manage_employee extends TestBase {

    @Test(dataProvider = "jsonDataProvider", description = "Verify that admin can add new employee with login details")
    public void OHRM_MANAGE_EMPLOYEE_TC002(DataHelper data) {
        CustomLogger logger = new CustomLogger();
        Account account = data.getData("valid_account", Account.class);
        Employee employee = DataPreparation.generateEmployee();

        String PIMMenuItem = data.getStringData("PIMMenuItem");
        String personalDetailsTitle = data.getStringData("personalDetailsTitle");

        logger.info("Step #1: Navigate to OrangeHRM page");
        LoginPage loginPage = new LoginPage();

        logger.info("Step #2: Login with valid username and password");
        loginPage.login(account);

        logger.info("Step #3: Go to PIM tab");
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.selectMenuItem(PIMMenuItem);

        logger.info("Step #4: Click on Add button");
        PIM_AddEmployeePage pim_addEmployeePage = new PIM_AddEmployeePage();
        pim_addEmployeePage.clickAddNewEmployee();

        logger.info("Step #5: Enter employee first name and last name");
        pim_addEmployeePage.enterEmployeeInfo(employee);

        logger.info("Step #6: Toggle the Create Login Details");
        pim_addEmployeePage.toggleCreateLoginDetails();

        logger.info("Step #7: Enter User name, password and confirm password");
        pim_addEmployeePage.inputUserLoginDetail(employee);

        logger.info("Step #8: Click on Save button");
        pim_addEmployeePage.clickBtnSave();

        logger.info("VP: Verify that Personal Details page with employee name appears");
        PIM_EmployeeListPage pim_employeeListPage = new PIM_EmployeeListPage();
        Assert.assertEquals(pim_employeeListPage.getPersonalDetailsPageTitle(), personalDetailsTitle);
        Assert.assertEquals(pim_employeeListPage.getEmployeeName(), employee.getFirstName() + " " + employee.getLastName());
        String t = employee.getFirstName() + " " + employee.getMidName() + " " + employee.getLastName();
        System.out.println(t);
        
        
    
    }

    @Test(dataProvider = "jsonDataProvider", description = "Verify that admin can add a report template")
    public void OHRM_MANAGE_EMPLOYEE_TC003(DataHelper data) {
        CustomLogger logger = new CustomLogger();
        Account account = data.getData("valid_account", Account.class);
        Employee employee = DataPreparation.generateEmployee();
        String PIMMenuItem = data.getStringData("PIMMenuItem");
        String displayFieldGroup = data.getStringData("displayFieldGroup");
        String displayField = data.getStringData("displayField");

        logger.info("Pre-condition: An employee is created");
        LoginPage loginPage = new LoginPage();
        loginPage.login(account);

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.selectMenuItem(PIMMenuItem);

        PIM_AddEmployeePage pim_addEmployeePage = new PIM_AddEmployeePage();
        pim_addEmployeePage.clickAddNewEmployee();

        pim_addEmployeePage.enterEmployeeInfo(employee);
        pim_addEmployeePage.toggleCreateLoginDetails();
        pim_addEmployeePage.inputUserLoginDetail(employee);

        pim_addEmployeePage.clickBtnSave();

        logger.info("Step #1: Click on 'Report' in top header");
        PIM_ReportsPage pim_reportsPage = new PIM_ReportsPage();
        pim_reportsPage.clickReportsTab();

        logger.info("Step #2: Click on Add button");
        pim_reportsPage.clickAddReportButton();

        logger.info("Step #3: Enter report info");
        String reportName = DataFaker.generateRandomString(5);
        pim_reportsPage.enterReportInfo(reportName, displayFieldGroup, displayField);

        logger.info("Step #4: Click on \"+\" button");
        pim_reportsPage.clickPlusButton();

        logger.info("Step #5: Click on \"Save\" button");
        pim_reportsPage.clickBtnSave();

        logger.info("VP: Verify that the report is displayed");
        Assert.assertEquals(pim_reportsPage.getReportName(), reportName);
    }

    @Test(dataProvider = "jsonDataProvider", description = "Verify that admin can search for an employee with ID")
    public void OHRM_MANAGE_EMPLOYEE_TC004(DataHelper data) {
        CustomLogger logger = new CustomLogger();
        Account account = data.getData("valid_account", Account.class);
        Employee employee = DataPreparation.generateEmployee();
        String PIMMenuItem = data.getStringData("PIMMenuItem");

        logger.info("Pre-condition: An employee is created");
        LoginPage loginPage = new LoginPage();
        loginPage.login(account);

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.selectMenuItem(PIMMenuItem);

        PIM_AddEmployeePage pim_addEmployeePage = new PIM_AddEmployeePage();
        pim_addEmployeePage.clickAddNewEmployee();

        pim_addEmployeePage.enterEmployeeInfo(employee);
        pim_addEmployeePage.toggleCreateLoginDetails();
        pim_addEmployeePage.inputUserLoginDetail(employee);

        pim_addEmployeePage.clickBtnSave();

        logger.info("Step #1: Navigate to OrangeHRM login page");
        logger.info("Step #2: Login with valid account");
        logger.info("Step #3: Go to PIM tab");
        PIM_EmployeeListPage pim_employeeListPage = new PIM_EmployeeListPage();
        pim_employeeListPage.waitForPersonalDetailsPageTitleDisplay();
        pim_employeeListPage.clickTabEmployeeList();

        logger.info("Step #4: Enter ID of created employee ID");
        pim_employeeListPage.inputEmployeeId(employee.getEmployeeId());

        logger.info("Step #5: Click on Search button");
        pim_employeeListPage.clickSearchButton();

        logger.info("VP: Verify that the employee is displayed");
        Assert.assertEquals(pim_employeeListPage.getSearchId(), employee.getEmployeeId());
        Assert.assertEquals(pim_employeeListPage.getSearchFirstMidName(), String.format("%s %s", employee.getFirstName(), employee.getMidName()));
        Assert.assertEquals(pim_employeeListPage.getSearchLastName(), employee.getLastName());
    }

    @Test(dataProvider = "jsonDataProvider", description = "Verify that admin can delete multiple employees")
    public void OHRM_MANAGE_EMPLOYEE_TC005(DataHelper data) {
        CustomLogger logger = new CustomLogger();
        Account account = data.getData("valid_account", Account.class);
        int numberOfEmployee = data.getIntData("numberOfEmployee");
        String PIMMenuItem = data.getStringData("PIMMenuItem");
        String expectedSearchResultLabel = data.getStringData("expectedSearchResultLabel");
        Employee[] employees = DataPreparation.generateEmployees(numberOfEmployee);

        PIM_AddEmployeePage pim_addEmployeePage = new PIM_AddEmployeePage();
        PIM_EmployeeListPage pim_employeeListPage = new PIM_EmployeeListPage();

        logger.info("Pre-condition #: Log in with admin account then create two employees ");

        LoginPage loginPage = new LoginPage();
        loginPage.login(account);

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.selectMenuItem(PIMMenuItem);

        for (int i = 0; i < numberOfEmployee; i++) {
            Employee employee = employees[i];

            pim_addEmployeePage.clickTabAddEmployee();

            pim_addEmployeePage.enterEmployeeInfo(employee);
            pim_addEmployeePage.toggleCreateLoginDetails();
            pim_addEmployeePage.inputUserLoginDetail(employee);

            pim_addEmployeePage.clickBtnSave();
            pim_employeeListPage.waitForPersonalDetailsPageTitleDisplay();
        }

        logger.info("Step #1: Navigate to Employee List tab of PIM page");
        pim_employeeListPage.clickTabEmployeeList();

        logger.info("Step #2: Select the checkboxes before created employees");
        for (int i = 0; i < numberOfEmployee; i++) {
            pim_employeeListPage.selectUserByID(employees[i].getEmployeeId());
        }

        logger.info("Step #3: Click on \"Delete Selected\" button");
        pim_employeeListPage.clickOnDeleteSelectedBtn();

        logger.info("Step #4: Click on \"Yes, Delete\" on confirmation popup");
        pim_employeeListPage.clickOnYesToConfirmDelete();

        logger.info("VP: Verify that the employees are not existed");
        for (int i = 0; i < numberOfEmployee; i++) {
            String actualSearchResultLabel = pim_employeeListPage.getSearchResultLabel(employees[i].getEmployeeId());
            Assert.assertEquals(actualSearchResultLabel, expectedSearchResultLabel);
        }
    }
//    @Test(dataProvider = "jsonDataProvider", description = "Verify that user can submit leave request")
//    public void OHRM_MANAGE_EMPLOYEE_TC006(DataHelper data) {
//    	CustomLogger logger = new CustomLogger();
//    	Account account = data.getData("valid_account", Account.class);
//    	String LeaveMenuItem = data.getStringData("LeaveMenuItem");
//    	
//    	logger.info("Pre-condition: An employee is created");
//    	LoginPage loginPage = new LoginPage();
//    	loginPage.login(account);
//    	
//    	
//    }
}
