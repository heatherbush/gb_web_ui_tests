package lesson8;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class CrmProjectPage {

    private SelenideElement buttonCreateNewProject = $(By.xpath("//a[@title='Создать проект']"));

    @Step("Кликнуть на кнопку 'Создать проект'")
    public CrmCreateNewProjectPage clickToCreateProjectButton() {
        buttonCreateNewProject.click();
        return page(CrmCreateNewProjectPage.class);
    }

}
