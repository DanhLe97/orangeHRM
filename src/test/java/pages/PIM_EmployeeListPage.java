package pages;

import com.fasterxml.jackson.databind.JsonNode;
import elements.ButtonElement;
import elements.LabelElement;
import elements.TextBoxElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.helpers.LocatorHelper;

import static utils.constants.TestConfigConstant.PIM_EMPLOYEE_LIST_PAGE;

public class PIM_EmployeeListPage extends PageBase {
    private static final Logger log = LogManager.getLogger(PIM_EmployeeListPage.class.getName());

    JsonNode locator = LocatorHelper.loadLocators(PIM_EMPLOYEE_LIST_PAGE);

    private ButtonElement tabEmployeeList = new ButtonElement(locator.get("PIMHeaderItem").asText(), "Employee List");
    private LabelElement lblPersonalDetails = new LabelElement(locator.get("lblPersonalDetails").asText());
    private LabelElement lblEmployeeName = new LabelElement(locator.get("lblEmployeeName").asText());

    private TextBoxElement txtEmployeeId = new TextBoxElement(locator.get("txtInputEmployeeInformation").asText(), "Employee Id");
    private ButtonElement btnSave = new ButtonElement(locator.get("btnInputEmployeeInformation").asText(), " Search ");
    private LabelElement searchResultEmployeeID = new LabelElement(locator.get("dataByColumnName").asText(), "Id");
    private LabelElement searchResultEmployeeFirstMidName = new LabelElement(locator.get("dataByColumnName").asText(), "First (& Middle) Name");
    private LabelElement searchResultEmployeeLastName = new LabelElement(locator.get("dataByColumnName").asText(), "Last Name");
    private ButtonElement btnSearch = new ButtonElement(locator.get("btnSearch").asText());

    private ButtonElement btnDeleteSelected = new ButtonElement(locator.get("btnDeleteSelected").asText());
    private ButtonElement btnConfirmDelete = new ButtonElement(locator.get("btnConfirmDelete").asText());


    public void clickTabEmployeeList() {
        log.debug("Switch to 'Employee List' tab");
        tabEmployeeList.click();
    }

    public String getPersonalDetailsPageTitle() {
        log.debug("Get personal detail page");
        lblPersonalDetails.waitForElementToBeVisible();
        return lblPersonalDetails.getText();
    }

    public String getEmployeeName() {
        log.debug("Get employee name");
        lblEmployeeName.waitForElementToBeVisible();
        return lblEmployeeName.getText();
    }


    public void inputEmployeeId(String employeeId) {
        txtEmployeeId.clearTextByKey();
        txtEmployeeId.sendKeys(employeeId);
    }

    public void clickSearchButton() {
        btnSave.click();
    }


    public String getSearchId() {
        return searchResultEmployeeID.getText();
    }

    public String getSearchFirstMidName() {
        return searchResultEmployeeFirstMidName.getText();
    }

    public String getSearchLastName() {
        return searchResultEmployeeLastName.getText();
    }


    public void selectUserByID(String employeeId) {
        log.debug("Scroll to user with ID: {}", employeeId);
        ButtonElement dynamicUserCheckboxByID = new ButtonElement(locator.get("dynamicUserCheckbox").asText(), employeeId);
//        dynamicUserCheckboxByID.scrollToElement();
        log.debug("Click on User {} checkbox", employeeId);
        dynamicUserCheckboxByID.click();
    }

    public void clickOnDeleteSelectedBtn() {
        log.debug("Scroll to Delete Selected button");
//        btnDeleteSelected.scrollToElement();
        log.debug("Click on Delete Selected button");
        btnDeleteSelected.click();
    }

    public void clickOnYesToConfirmDelete() {
        log.debug("Scroll to confirm delete button");
//        btnConfirmDelete.scrollToElement();
        log.debug("Click on Yes, Delete to confirm delete selected users");
        btnConfirmDelete.click();
    }

    public String getSearchResultLabel(String employeeId) {
        log.debug("Wait for Employee ID textbox visible");
        txtEmployeeId.waitForElementToBeVisible();
        log.debug("Enter Employee ID: " + employeeId);
        txtEmployeeId.clearTextByKey();
        txtEmployeeId.sendKeys(employeeId);
        log.debug("Click on Search button");
        btnSearch.click();
        log.debug("Check visibility of searched result");
        LabelElement lblSearchResult = new LabelElement(locator.get("lblSearchResult").asText());

        return lblSearchResult.getText();
    }

    public void waitForPersonalDetailsPageTitleDisplay() {
        log.debug("Get personal detail page");
        lblPersonalDetails.waitForElementToBeVisible();
    }
}
