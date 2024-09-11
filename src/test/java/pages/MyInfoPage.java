package pages;

import com.fasterxml.jackson.databind.JsonNode;
import elements.ButtonElement;
import elements.LabelElement;
import elements.SelectElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.helpers.LocatorHelper;

import static utils.constants.TestConfigConstant.*;

public class MyInfoPage extends PageBase {
    private static final Logger log = LogManager.getLogger(MyInfoPage.class.getName());

    private JsonNode locator = LocatorHelper.loadLocators(MYINFO_PAGE);

    private SelectElement btnDropdown = new SelectElement(locator.get("btnDropdown").asText());
    private SelectElement optionDropdown = new SelectElement(locator.get("optionDropdown").asText());
    private ButtonElement btnSavePersonalDetails = new ButtonElement(locator.get("btnSavePersonalDetails").asText());
    private ButtonElement btnSaveCustomFields = new ButtonElement(locator.get("btnSaveCustomFields").asText());

    private ButtonElement messageSuccess = new ButtonElement(locator.get("messageSuccess").asText());

    public void selectDropdown(String nameDropdown, String option){
        log.debug("Select dropdown {} with option {}",nameDropdown,option);
        btnDropdown.setdynamicBy(nameDropdown);
        btnDropdown.waitForClickableElementAndClick();
        btnDropdown.click();
        optionDropdown.setdynamicBy(option);
        optionDropdown.click();
    }

    public void SavePersonalDetail(){
        log.debug("click on Save button when saving Personal Detail");
        btnSavePersonalDetails.click();
    }

    public void SaveCustomField(){
        log.debug("click on Save button when saving Custom Field");
        btnSaveCustomFields.click();
    }

    public String getMessageColor(){
        log.debug("get color of message after saving successfully");
        messageSuccess.waitForElementToBeVisible();
        //return messageSuccess.getElement().getAttribute("color");
        return messageSuccess.getElement().getCssValue("background-color");
    }
}
