package lesson8;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;

public class CrmCreateNewProjectPage {

    private SelenideElement inputProjectName = $(By.name("crm_project[name]"));

    @Step("Заполнить имя")
    public CrmCreateNewProjectPage fillProjectName(String name) {
        inputProjectName.sendKeys(name);
        return this;
    }

    private SelenideElement selectOrganization = $(By.xpath("//span[text()='Укажите организацию']"));

    @Step("Открыть список организаций")
    public CrmCreateNewProjectPage openOrganizationSelect() {
        selectOrganization.click();
        return this;
    }

    private SelenideElement resultSelectOrganization = $(By.xpath("//div[text()='«Все организации»']"));

    @Step("Выбрать организацию")
    public CrmCreateNewProjectPage chooseOrganization() {
        resultSelectOrganization.click();
        return this;
    }

    private SelenideElement businessUnitSelect = $(By.name("crm_project[businessUnit]"));

    @Step("Выбрать подразделение")
    public CrmCreateNewProjectPage selectBusinessUnit(String businessUnit) {
        businessUnitSelect.selectOptionContainingText(businessUnit);
        return this;
    }

    private SelenideElement curatorSelect = $(By.name("crm_project[curator]"));

    @Step("Выбрать куратора")
    public CrmCreateNewProjectPage selectCurator(String curator) {
        curatorSelect.selectOptionContainingText(curator);
        return this;
    }

    private SelenideElement leaderRpSelect = $(By.name("crm_project[rp]"));

    @Step("Выбрать руководителя")
    public CrmCreateNewProjectPage selectLeaderRp(String leader) {
        leaderRpSelect.selectOptionContainingText(leader);
        return this;
    }

    private SelenideElement managerSelect = $(By.name("crm_project[manager]"));

    @Step("Выбрать менеджера")
    public CrmCreateNewProjectPage selectManager(String manager) {
        managerSelect.selectOptionContainingText(manager);
        return this;
    }

    private SelenideElement mainContactField = $(By.xpath("//div[contains(@id, 's2id_crm_project_contactMain')]"));

    @Step("Открыть список основных контактных лиц")
    public CrmCreateNewProjectPage clickToMainContactField() {
        mainContactField.click();
        return this;
    }

    private SelenideElement inputMainContact = $(By.xpath("//div[contains(@id, 's2id_crm_project_contactMain')]/input"));

    @Step("Вписать основное контактное лицо")
    public CrmCreateNewProjectPage fillInputMainContact(String contact) {
        inputMainContact.sendKeys(contact);
        return this;
    }

    private List<SelenideElement> contactList = $$(By.xpath("//div[@class='select2-result-label']"));

    @Step("Выбрать вписанное контактное лицо из выпавшего списка")
    public CrmCreateNewProjectPage selectMainContact() {
        contactList.get(1).click();
        return this;
    }

    private SelenideElement frameBody = $(By.xpath("//body"));
    private SelenideElement planingFrame = $(By.xpath("//iframe[contains(@id, 'crm_project_planning-uid')]"));

    @Step("Заполнить поле 'Планирование'")
    public CrmCreateNewProjectPage fillPlaningFrame(String message) {
        switchTo().frame(planingFrame);
        frameBody.sendKeys(message);
        switchTo().parentFrame();
        return this;
    }

    private SelenideElement requirementsFrame = $(By.xpath("//iframe[contains(@id, 'crm_project_requirementsManagement')]"));

    @Step("Заполнить поле 'Управление требованиями'")
    public CrmCreateNewProjectPage fillRequirementsFrame(String message) {
        switchTo().frame(requirementsFrame);
        frameBody.sendKeys(message);
        switchTo().parentFrame();
        return this;
    }

    private SelenideElement developmentFrame = $(By.xpath("//iframe[contains(@id, 'crm_project_development')]"));

    @Step("Заполнить поле 'Разработка'")
    public CrmCreateNewProjectPage fillDevelopmentFrame(String message) {
        switchTo().frame(developmentFrame);
        frameBody.sendKeys(message);
        switchTo().parentFrame();
        return this;
    }

    private SelenideElement testingFrame = $(By.xpath("//iframe[contains(@id, 'crm_project_testing')]"));

    @Step("Заполнить поле 'Тестирование'")
    public CrmCreateNewProjectPage fillTestingFrame(String message) {
        switchTo().frame(testingFrame);
        frameBody.sendKeys(message);
        switchTo().parentFrame();
        return this;
    }

    private SelenideElement configManagement = $(By.name("crm_project[configManagement]"));

    @Step("Заполнить поле 'Управление конфигурацией'")
    public CrmCreateNewProjectPage fillConfigManagement(String message) {
        configManagement.sendKeys(message);
        return this;
    }

    private SelenideElement skipSpeedCheckbox = $(By.name("crm_project[skipSpeedChecks]"));

    @Step("Выбрать 'Всегда открывать проект в развернутом виде'")
    public CrmCreateNewProjectPage skipSpeed() {
        skipSpeedCheckbox.click();
        return this;
    }

    private SelenideElement newProjectSaveButton = $(By.xpath("//button[contains(text(),' Сохранить ')]"));

    @Step("Кликнуть на кнопку 'Сохранить'")
    public CrmCreateNewProjectPage saveNewProject() {
        newProjectSaveButton.click();
        return this;
    }

    private SelenideElement validationField = $(By.xpath("//span[@class='validation-failed']"));

    @Step("Проверить появление итогового сообщения")
    public void assertNewProjectCreate(String message) {
        assertThat(validationField, hasText(message));
    }
}
