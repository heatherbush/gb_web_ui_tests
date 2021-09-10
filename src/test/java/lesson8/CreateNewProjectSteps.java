package lesson8;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.codeborne.selenide.Selenide.open;

public class CreateNewProjectSteps {
    @Given("I am authorized")
    public void iAmAuthorized() {
        new CrmLoginPage().loginToCrm("Applanatest1", "Student2020!");
    }

    @Given("I am at create new project page")
    public void iAmAtCreateNewProjectPage() {
        open("https://crm.geekbrains.space/project/");
        new CrmProjectPage().clickToCreateProjectButton();
    }

    @When("I fill name field")
    public void iFillNameField() {
        new CrmCreateNewProjectPage().fillProjectName("Проект13467");
    }

    @And("I select organization")
    public void iSelectOrganization() {
        new CrmCreateNewProjectPage().openOrganizationSelect()
                .chooseOrganization();
    }

    @And("I select business  unit")
    public void iSelectBusinessUnit() {
        new CrmCreateNewProjectPage().selectBusinessUnit("Research & Development");
    }

    @And("I select curator")
    public void iSelectCurator() {
        new CrmCreateNewProjectPage().selectCurator("Юзеров Юзер");
    }

    @And("I select leader")
    public void iSelectLeader() {
        new CrmCreateNewProjectPage().selectLeaderRp("Applanatest1 Applanatest1 Applanatest1");
    }

    @And("I select manager")
    public void iSelectManager() {
        new CrmCreateNewProjectPage().selectManager("Applanatest Applanatest Applanatest");
    }

    @And("I fill main contact field")
    public void iFillMainContactField() {
        new CrmCreateNewProjectPage().clickToMainContactField()
                .fillInputMainContact("123 123");
    }

    @And("I select main contact")
    public void iSelectMainContact() {
        new CrmCreateNewProjectPage().selectMainContact();
    }

    @And("I fill planing")
    public void iFillPlaning() {
        new CrmCreateNewProjectPage().fillPlaningFrame("Планирование");
    }

    @And("I fill requirements")
    public void iFillRequirements() {
        new CrmCreateNewProjectPage().fillRequirementsFrame("Управление требованиями");
    }

    @And("i fill development")
    public void iFillDevelopment() {
        new CrmCreateNewProjectPage().fillDevelopmentFrame("Разработка");
    }

    @And("I fill testing")
    public void iFillTesting() {
        new CrmCreateNewProjectPage().fillTestingFrame("Тестирование");
    }

    @And("I fill config management")
    public void iFillConfigManagement() {
        new CrmCreateNewProjectPage().fillConfigManagement("Управление конфигурацией");
    }

    @And("I select skip speed")
    public void iSelectSkipSpeed() {
        new CrmCreateNewProjectPage().skipSpeed();
    }

    @And("I click save and close button")
    public void iClickSaveAndCloseButton() {
        new CrmCreateNewProjectPage().saveNewProject();
    }

    @Then("Success message is visible")
    public void successMessageIsVisible() {
        new CrmCreateNewProjectPage().assertNewProjectCreate("Это значение уже используется.");
    }
}
