package lesson6;

import org.junit.jupiter.api.Test;

public class CrmTest extends BaseCrmTest {
    public static final String BASE_URL = "https://crm.geekbrains.space/";

    @Test
    void createNewProject(){
        driver.get(BASE_URL + "project/");
        new CrmProjectPage(driver)
                .clickToCreateProjectButton()
                .fillProjectName("Проект13467")
                .openOrganizationSelect()
                .chooseOrganization()
                .selectBusinessUnit("Research & Development")
                .selectCurator("Юзеров Юзер")
                .selectLeaderRp("Applanatest1 Applanatest1 Applanatest1")
                .selectManager("Applanatest Applanatest Applanatest")
                .clickToMainContactField()
                .fillInputMainContact("123 123")
                .selectMainContact()
                .fillPlaningFrame("Планирование")
                .fillRequirementsFrame("Управление требованиями")
                .fillDevelopmentFrame("Разработка")
                .fillTestingFrame("Тестирование")
                .fillConfigManagement("Управление конфигурацией")
                .skipSpeed()
                .saveNewProject()
                .assertNewProjectCreate("Это значение уже используется.");
    }

    @Test
    void createNewContactPerson() {
        driver.get(BASE_URL + "contact/");
        new CrmContactPage(driver)
                .clickToCreateNewContactButton()
                .fillLastName("123")
                .fillFirstName("123")
                .openOrganizationSelect()
                .selectOrganization()
                .fillJobTitle("test")
                .clickSaveButton()
                .assertCreateNewContact();
    }
}

