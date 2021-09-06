package lesson6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;

public class CrmCreateNewProjectPage extends BaseView {

    public static final String inputProjectNameLocator = "crm_project[name]";
    public static final String resultSelectOrganizationLocator = "//div[text()='«Все организации»']";
    public static final String inputMainContactLocator = "//div[contains(@id, 's2id_crm_project_contactMain')]/input";
    public static final String contactListLocator = "//div[@class='select2-result-label']";
    public static final String validationFieldLocator = "//span[@class='validation-failed']";

    @FindBy(name = inputProjectNameLocator)
    public WebElement inputProjectName;

    public CrmCreateNewProjectPage fillProjectName(String name) {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.name(inputProjectNameLocator)));
        inputProjectName.sendKeys(name);
        return this;
    }

    @FindBy(xpath = "//span[text()='Укажите организацию']")
    public WebElement selectOrganization;

    public CrmCreateNewProjectPage openOrganizationSelect() {
        selectOrganization.click();
        return this;
    }

    @FindBy(xpath = resultSelectOrganizationLocator)
    public WebElement resultSelectOrganization;

    public CrmCreateNewProjectPage chooseOrganization() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(resultSelectOrganizationLocator)));
        resultSelectOrganization.click();
        return this;
    }

    @FindBy(name = "crm_project[businessUnit]")
    public WebElement businessUnitSelect;

    public CrmCreateNewProjectPage selectBusinessUnit(String businessUnit) {
        new Select(businessUnitSelect).selectByVisibleText(businessUnit);
        return this;
    }

    @FindBy(name = "crm_project[curator]")
    public WebElement curatorSelect;

    public CrmCreateNewProjectPage selectCurator(String curator) {
        new Select(curatorSelect).selectByVisibleText(curator);
        return this;
    }

    @FindBy(name = "crm_project[rp]")
    public WebElement leaderRpSelect;

    public CrmCreateNewProjectPage selectLeaderRp(String leader) {
        new Select(leaderRpSelect).selectByVisibleText(leader);
        return this;
    }

    @FindBy(name = "crm_project[manager]")
    public WebElement managerSelect;

    public CrmCreateNewProjectPage selectManager(String manager) {
        new Select(managerSelect).selectByVisibleText(manager);
        return this;
    }

    @FindBy(xpath = "//div[contains(@id, 's2id_crm_project_contactMain')]")
    public WebElement mainContactField;

    public CrmCreateNewProjectPage clickToMainContactField() {
        mainContactField.click();
        return this;
    }

    @FindBy(xpath = inputMainContactLocator)
    public WebElement inputMainContact;

    public CrmCreateNewProjectPage fillInputMainContact(String contact) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(inputMainContactLocator)));
        inputMainContact.sendKeys(contact);
        return this;
    }

    @FindBy(xpath = contactListLocator)
    public List<WebElement> contactList;

    public CrmCreateNewProjectPage selectMainContact() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(contactListLocator)));
        contactList.get(1).click();
        return this;
    }

    @FindBy(xpath = "//body")
    public WebElement frameBody;

    @FindBy(xpath = "//iframe[contains(@id, 'crm_project_planning-uid')]")
    public WebElement planingFrame;

    public CrmCreateNewProjectPage fillPlaningFrame(String message) {
        driver.switchTo().frame(planingFrame);
        frameBody.sendKeys(message);
        driver.switchTo().parentFrame();
        return this;
    }

    @FindBy(xpath = "//iframe[contains(@id, 'crm_project_requirementsManagement')]")
    public WebElement requirementsFrame;

    public CrmCreateNewProjectPage fillRequirementsFrame(String message) {
        driver.switchTo().frame(requirementsFrame);
        frameBody.sendKeys(message);
        driver.switchTo().parentFrame();
        return this;
    }

    @FindBy(xpath = "//iframe[contains(@id, 'crm_project_development')]")
    public WebElement developmentFrame;

    public CrmCreateNewProjectPage fillDevelopmentFrame(String message) {
        driver.switchTo().frame(developmentFrame);
        frameBody.sendKeys(message);
        driver.switchTo().parentFrame();
        return this;
    }

    @FindBy(xpath = "//iframe[contains(@id, 'crm_project_testing')]")
    public WebElement testingFrame;

    public CrmCreateNewProjectPage fillTestingFrame(String message) {
        driver.switchTo().frame(testingFrame);
        frameBody.sendKeys(message);
        driver.switchTo().parentFrame();
        return this;
    }

    @FindBy(name = "crm_project[configManagement]")
    public WebElement configManagement;

    public CrmCreateNewProjectPage fillConfigManagement(String message) {
        configManagement.sendKeys(message);
        return this;
    }

    @FindBy(name = "crm_project[skipSpeedChecks]")
    public WebElement skipSpeedCheckbox;

    public CrmCreateNewProjectPage skipSpeed() {
        skipSpeedCheckbox.click();
        return this;
    }

    @FindBy(xpath = "//button[contains(text(),' Сохранить ')]")
    public WebElement newProjectSaveButton;

    public CrmCreateNewProjectPage saveNewProject() {
        newProjectSaveButton.click();
        return this;
    }

    @FindBy(xpath = validationFieldLocator)
    public WebElement validationField;

    public void assertNewProjectCreate(String message) {
        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath(validationFieldLocator)));
        assertThat(validationField, hasText(message));
    }

    public CrmCreateNewProjectPage(WebDriver driver) {
        super(driver);
    }
}
