package pages;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.fasterxml.jackson.databind.JsonNode;

import drivers.DriverWrapper;
import elements.ButtonElement;
import elements.ElementWrapper;
import elements.LabelElement;
import elements.TextBoxElement;
import utils.constants.TestConfigConstant;
import utils.helpers.LocatorHelper;

public class LeavePage extends PageBase {
	private JsonNode locator = LocatorHelper.loadLocators(TestConfigConstant.LEAVE_PAGE);
	private String dynamicLeaveMenu = locator.get("dynamicLeaveMenu").asText();
//Employee Name textbox
	private LabelElement txtEmployeeName = new LabelElement(locator.get("txtEmployeeName").asText());
//Leave Type dropdown
	private LabelElement dropdownLeaveType = new LabelElement(locator.get("dynamicDropdownAssignLeave").asText(),
			"Leave Type");
//From Date datepicker
	private LabelElement inputFromDate = new LabelElement(locator.get("inputFromDate").asText());
//To Date datepicker
	private LabelElement inputToDate = new LabelElement(locator.get("inputToDate").asText());
//Partial Day dropdown
	private LabelElement dropdownPartialDay = new LabelElement(locator.get("dynamicDropdownAssignLeave").asText(),
			"Partial Days");
//Start Day dropdown
	private LabelElement dropdownStartDay = new LabelElement(locator.get("dynamicDropdownAssignLeave").asText(),
			"Start Day");
//End Day dropdown
	private LabelElement dropdownEndDay = new LabelElement(locator.get("dynamicDropdownAssignLeave").asText(),
			"End Day");
//Assign button
	private ButtonElement btnAssign = new ButtonElement(locator.get("btnAssign").asText());
//From time
	private TextBoxElement txtFromTime = new TextBoxElement(locator.get("dynamicDropdownAssignLeave").asText(), "From");
//To time
	private TextBoxElement txtToTime = new TextBoxElement(locator.get("dynamicDropdownAssignLeave").asText(), "To");

	private LabelElement lblToDate = new LabelElement(locator.get("lblToDate").asText());
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM");
	Alert alert = new Alert() {

		@Override
		public void sendKeys(String keysToSend) {
			// TODO Auto-generated method stub

		}

		@Override
		public String getText() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void dismiss() {
			// TODO Auto-generated method stub

		}

		@Override
		public void accept() {
			// TODO Auto-generated method stub

		}
	};

	public void selectLeaveMenu(String menuItem) {
		LabelElement lblMenuItem = new LabelElement(locator.get("dynamicLeaveMenu").asText(), menuItem);
		lblMenuItem.click();
	}

	public void inputAssignLeave(String a, String b, String c, String d) {
		txtEmployeeName.waitForElementToBeVisible();
		txtEmployeeName.sendKeys(a);
		dropdownLeaveType.selectItemInDropdown(b);

	}

	public void inputAssignLeaveInfo(String employeeName, String leaveType, String partialDay, String startDay,
			String endDay, String fromTime, String toTime) {
		txtEmployeeName.waitForElementToBeVisible();
		txtEmployeeName.clearText();
		txtEmployeeName.sendKeys(employeeName);
		LabelElement suggestUserName = new LabelElement(locator.get("dynamicSuggestUserName").asText(), employeeName);
		suggestUserName.click();
		dropdownLeaveType.click();
		LabelElement optionLeaveType = new LabelElement(locator.get("dynamicDropdownSelection").asText(), leaveType);
		optionLeaveType.click();
		inputToDate.clearText();
		inputToDate.sendKeys(plusDate(2));

		inputFromDate.clearText();
		inputFromDate.sendKeys(getCurrentDate());
		lblToDate.click();

		dropdownPartialDay.click();
		LabelElement optionPartialDays = new LabelElement(locator.get("dynamicDropdownSelection").asText(), partialDay);
		optionPartialDays.click();

		dropdownStartDay.scrollToElement();
		dropdownStartDay.click();
		LabelElement optionStartlDays = new LabelElement(locator.get("dynamicDropdownSelection").asText(), startDay);
		optionStartlDays.click();

		dropdownEndDay.scrollToElement();
		dropdownEndDay.click();
		LabelElement optionEndlDays = new LabelElement(locator.get("dynamicDropdownSelection").asText(), endDay);
		optionEndlDays.click();

		txtFromTime.clearText();
		txtFromTime.sendKeys(fromTime);

		txtToTime.clearText();
		txtToTime.sendKeys(toTime);

		btnAssign.scrollToElement();
		btnAssign.click();
		alert.accept();

	}

	private String getCurrentDate() {
		LocalDate currentDate = LocalDate.now();
		return currentDate.format(formatter).toString();
	}

	private String plusDate(int number) {
		LocalDate newDate = LocalDate.now().plusDays(number);
		return newDate.format(formatter).toString();
	}

}