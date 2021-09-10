package lesson8;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class CrmLoginPage {

    private SelenideElement inputLogin = $(By.id("prependedInput"));

    private SelenideElement inputPassword = $(By.id("prependedInput2"));

    private SelenideElement loginButton = $(By.xpath("//button"));

    @Step("Заполнить логин")
    public CrmLoginPage fillInputLogin(String login) {
        inputLogin.sendKeys(login);
        return this;
    }

    @Step("Заполнить пароль")
    public CrmLoginPage fillInputPassword(String password) {
        inputPassword.sendKeys(password);
        return this;
    }

    @Step("Нажать на кнопку 'Войти'")
    public CrmMainPage clickLoginButton() {
        loginButton.click();
        return page(CrmMainPage.class);
    }

    public void loginToCrm(String login, String password) {
        open("https://crm.geekbrains.space/");
        inputLogin.sendKeys(login);
        inputPassword.sendKeys(password);
        loginButton.click();
    }
}
