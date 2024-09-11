package pages;

import com.fasterxml.jackson.databind.JsonNode;
import dataobjects.Employee;
import elements.ButtonElement;
import elements.TextBoxElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.helpers.LocatorHelper;

import static utils.constants.TestConfigConstant.PIM_ADD_EMPLOYEE_PAGE;

public class PIM_AddEmployeePage extends PageBase {
    private static final Logger log = LogManager.getLogger(PIM_AddEmployeePage.class.getName());

    private JsonNode locator = LocatorHelper.loadLocators(PIM_ADD_EMPLOYEE_PAGE);

    private ButtonElement tabAddEmployee = new ButtonElement(locator.get("PIMHeaderItem").asText(), "Add Employee");

    private ButtonElement btnAddNewEmployee = new ButtonElement(locator.get("btnAddNewEmployee").asText());
    private TextBoxElement txtFirstName = new TextBoxElement(locator.get("txtEmployeeName").asText(), "firstName");
    private TextBoxElement txtMidName = new TextBoxElement(locator.get("txtEmployeeName").asText(), "middleName");
    private TextBoxElement txtLastName = new TextBoxElement(locator.get("txtEmployeeName").asText(), "lastName");
    private TextBoxElement txtEmployeeId = new TextBoxElement(locator.get("txtEmployeeId").asText());
    private ButtonElement btnCreateLoginDetails = new ButtonElement(locator.get("btnCreateLoginDetails").asText());
    private TextBoxElement txtUserName = new TextBoxElement(locator.get("txtLoginDetails").asText(), "Username");
    private TextBoxElement txtPassword = new TextBoxElement(locator.get("txtLoginDetails").asText(), "Password");
    private TextBoxElement txtConfirmPassword = new TextBoxElement(locator.get("txtLoginDetails").asText(), "Confirm Password");
    private ButtonElement btnSave = new ButtonElement(locator.get("btnSave").asText());
    private ButtonElement dynamicPIMTab = new ButtonElement(locator.get( "dynamicPIMTab" ).asText());


    public void clickTabAddEmployee() {
        log.debug("Switch to 'Add employee' tab");
        tabAddEmployee.click();
    }

    public void clickAddNewEmployee() {
        log.debug("Click on add button");
        btnAddNewEmployee.click();
    }

    public void enterEmployeeInfo(Employee employee) {
        log.debug("Enter employee info");
        txtFirstName.sendKeys(employee.getFirstName());
        txtMidName.sendKeys(employee.getMidName());
        txtLastName.sendKeys(employee.getLastName());
        //txtEmployeeId.clearText();
        txtEmployeeId.clearTextByKey();
        txtEmployeeId.sendKeys(employee.getEmployeeId());
    }

    public void toggleCreateLoginDetails() {
        log.debug("Toggle create login detail switch");
        btnCreateLoginDetails.click();
    }

    public void inputUserLoginDetail(Employee employee) {
        log.debug("Enter user login detail");
        txtUserName.sendKeys(employee.getUserName());
        txtPassword.sendKeys(employee.getPassWord());
        txtConfirmPassword.sendKeys(employee.getConfirmpassWord());
    }

    public void clickBtnSave() {
        log.debug("Click on save button to add employee");
        btnSave.click();
        
    }
}
