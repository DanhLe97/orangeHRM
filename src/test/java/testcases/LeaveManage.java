package testcases;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import dataobjects.Account;
import dataobjects.Employee;
import pages.DashboardPage;
import pages.LeavePage;
import pages.LoginPage;
import pages.PIM_AddEmployeePage;
import pages.PIM_EmployeeListPage;
import reportConfig.CustomLogger;
import utils.helpers.DataHelper;
import utils.helpers.TestListener;

@Listeners(TestListener.class)
public class LeaveManage extends TestBase {
	@Test(dataProvider = "jsonDataProvider", description = "Verify that user can submit leave request")
	public void OHRM_MANAGE_EMPLOYEE_TC006(DataHelper data) {
		CustomLogger logger = new CustomLogger();
		Account account = data.getData("valid_account", Account.class);
		String LeaveMenuItem = data.getStringData("leaveMenuItem");
		String AssignLeaveItem = data.getStringData("assignLeave");
		String PIMMenuItem = data.getStringData("PIMMenuItem");
		Employee employee = DataPreparation.generateEmployee();
		String employeeName = employee.getFirstName() + " " + employee.getMidName() + " " + employee.getLastName();
		String leaveType = data.getStringData("leaveType");
		String partialDay = data.getStringData("partialDay");
		String startDay = data.getStringData("startDay");
		String endDay = data.getStringData("endDay");
		String fromTime = data.getStringData("fromTime");
		String toTime = data.getStringData("toTime");
		String numberOfDayLeave = data.getStringData("numberOfDayLeave");

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
		System.out.println(employeeName);
		pim_addEmployeePage.clickBtnSave();
		PIM_EmployeeListPage pim_employeeListPage = new PIM_EmployeeListPage();
		Assert.assertEquals(pim_employeeListPage.getEmployeeName(),
				employee.getFirstName() + " " + employee.getLastName());
		pim_employeeListPage.selectMenuItem(LeaveMenuItem);
		logger.info("Step #1: Login with valid admin account");
		logger.info("Step #2: Select Leave menu item");
		LeavePage leavePage = new LeavePage();
		logger.info("Step #3: Select Assign Leave item");
		leavePage.selectLeaveMenu(AssignLeaveItem);

		logger.info("Step #4: Enter page assign fields");
		leavePage.inputAssignLeaveInfo(employeeName, leaveType, partialDay, startDay, endDay, fromTime, toTime);

	}

//	@Test(dataProvider = "jsonDataProvider", description = "Verify that user can delete Leave type.")
//	public void OHRM_LEAVE_TC007 (DataHelper data) {
//		CustomLogger logger = new CustomLogger();
//		Account account = data.getData("valid_account", Account.class);
//		String LeaveMenuItem = data.getStringData("LeaveMenuItem");
//		
//		
//	}
}
