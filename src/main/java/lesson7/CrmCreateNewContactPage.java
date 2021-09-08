package lesson7;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;

public class CrmCreateNewContactPage extends BaseView {
    public static final String lastNameLocator = "//div/input[@name='crm_contact[lastName]']";
    public static final String organizationListLocator = "//li[contains(@class,'select2-result-selectable')]";
    public static final String assertMessageLocator = "//div[@class='flash-messages-holder']/div/div[@class='message']";


    @FindBy(xpath = lastNameLocator)
    public WebElement lastName;

    @Step("Заполнить фамилию")
    public CrmCreateNewContactPage fillLastName(String lastname) {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(lastNameLocator)));
        lastName.sendKeys(lastname);
        return this;
    }

    @FindBy(xpath = "//input[@name='crm_contact[firstName]']")
    public WebElement firstName;

    @Step("Заполнить имя")
    public CrmCreateNewContactPage fillFirstName(String name) {
        firstName.sendKeys(name);
        return this;
    }

    @FindBy(xpath = "//span[text()='Укажите организацию']")
    public WebElement organizationSelect;

    @Step("Открыть список организаций")
    public CrmCreateNewContactPage openOrganizationSelect() {
        organizationSelect.click();
        return this;
    }

    @FindBy(xpath = organizationListLocator)
    public List<WebElement> organizationList;

    @Step("Выбрать первый пункт списка организаций")
    public CrmCreateNewContactPage selectOrganization() {
        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(organizationListLocator)));
        organizationList.get(0).click();
        return this;
    }

    @FindBy(xpath = "//input[@name='crm_contact[jobTitle]']")
    public WebElement jobTitle;

    @Step("Заполнить должность")
    public CrmCreateNewContactPage fillJobTitle(String job) {
        jobTitle.sendKeys(job);
        return this;
    }

    @FindBy(xpath = "//button[contains(text(),' Сохранить ')]")
    public WebElement saveNewContactButton;

    @Step("Нажать на кнопку 'Сохранить'")
    public CrmCreateNewContactPage clickSaveButton() {
        saveNewContactButton.click();
        return this;
    }

    @FindBy(xpath = assertMessageLocator)
    public WebElement assertMessage;

    @Step("Проверить наличие сообщения 'Контактное лицо сохранено'")
    public void assertCreateNewContact() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(assertMessageLocator)));
        assertThat(assertMessage, hasText("Контактное лицо сохранено"));
    }

    public CrmCreateNewContactPage(WebDriver driver) {
        super(driver);
    }
}
