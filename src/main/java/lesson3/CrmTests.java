package lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CrmTests {
    public static WebDriver driver;
    public static WebDriverWait webDriverWait;

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, 10);

        loginToCrm();

        createNewProject();

        createNewContactPerson();

        driver.quit();
    }

    public static void loginToCrm() {
        driver.get("https://crm.geekbrains.space/user/login");
        driver.findElement(By.id("prependedInput")).sendKeys("Applanatest1");
        driver.findElement(By.id("prependedInput2")).sendKeys("Student2020!");
        driver.findElement(By.xpath("//button")).click();
    }

    public static void createNewProject() {
        driver.get("https://crm.geekbrains.space/project/");

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title='Создать проект']")));
        driver.findElement(By.xpath("//a[@title='Создать проект']")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("crm_project[name]")));
        driver.findElement(By.name("crm_project[name]")).sendKeys("Проект13467");

        driver.findElement(By.xpath("//span[text()='Укажите организацию']")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='«Все организации»']")));
        driver.findElement(By.xpath("//div[text()='«Все организации»']")).click();

        driver.findElement(By.xpath("//option[text()='Выберите подразделение']")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[text()='Research & Development']")));
        driver.findElement(By.xpath("//option[text()='Research & Development']")).click();

        driver.findElement(By.xpath("//option[text()='Выберите куратора проекта']")).click();
        webDriverWait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//select[@name='crm_project[curator]']/option[text()='Юзеров Юзер']")));
        driver.findElement(By.xpath("//select[@name='crm_project[curator]']/option[text()='Юзеров Юзер']")).click();

        driver.findElement(By.xpath("//option[text()='Выберите руководителя проекта']")).click();
        webDriverWait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//select[@name='crm_project[rp]']/option[text()='Applanatest1 Applanatest1 Applanatest1']")));
        driver.findElement(By.xpath("//select[@name='crm_project[rp]']/option[text()='Applanatest1 Applanatest1 Applanatest1']")).click();

        driver.findElement(By.xpath("//option[text()='Выберите менеджера']")).click();
        webDriverWait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//select[@name='crm_project[manager]']/option[text()='Applanatest Applanatest Applanatest']")));
        driver.findElement(By.xpath("//select[@name='crm_project[manager]']/option[text()='Applanatest Applanatest Applanatest']")).click();

        driver.findElement(By.xpath("//div[contains(@id, 's2id_crm_project_contactMain')]")).click();
        webDriverWait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//div[contains(@id, 's2id_crm_project_contactMain')]/input")));
        driver.findElement(By.xpath("//div[contains(@id, 's2id_crm_project_contactMain')]/input")).sendKeys("123 123");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='select2-result-label']")));
        List<WebElement> contactList = driver.findElements(By.xpath("//div[@class='select2-result-label']"));
        contactList.get(1).click();

        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id, 'crm_project_planning-uid')]")));
        driver.findElement(By.xpath("//body")).sendKeys("Планирование");
        driver.switchTo().parentFrame();

        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id, 'crm_project_requirementsManagement')]")));
        driver.findElement(By.xpath("//body")).sendKeys("Управление требованиями");
        driver.switchTo().parentFrame();

        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id, 'crm_project_development')]")));
        driver.findElement(By.xpath("//body")).sendKeys("Разработка");
        driver.switchTo().parentFrame();

        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id, 'crm_project_testing')]")));
        driver.findElement(By.xpath("//body")).sendKeys("Тестирование");
        driver.switchTo().parentFrame();

        driver.findElement(By.name("crm_project[configManagement]")).sendKeys("Управление конфигурацией");

        driver.findElement(By.name("crm_project[skipSpeedChecks]")).click();

        driver.findElement(By.xpath("//button[contains(text(),' Сохранить ')]")).click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//span[@class='validation-failed']")));
        driver.findElement(By.xpath("//span[@class='validation-failed']"))
                .getText().equals("Это значение уже используется.");
    }

    public static void createNewContactPerson() {
        driver.get("https://crm.geekbrains.space/contact/");

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title='Создать контактное лицо']")));
        driver.findElement(By.xpath("//a[@title='Создать контактное лицо']")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/input[@name='crm_contact[lastName]']")));
        driver.findElement(By.xpath("//input[@name='crm_contact[lastName]']")).sendKeys("123");

        driver.findElement(By.xpath("//input[@name='crm_contact[firstName]']")).sendKeys("123");

        driver.findElement(By.xpath("//span[text()='Укажите организацию']")).click();
        webDriverWait.until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(By.xpath("//li[contains(@class,'select2-result-selectable')]")));
        List<WebElement> contactList = driver.findElements(By.xpath("//li[contains(@class,'select2-result-selectable')]"));
        contactList.get(0).click();

        driver.findElement(By.xpath("//input[@name='crm_contact[jobTitle]']")).sendKeys("test");

        driver.findElement(By.xpath("//button[contains(text(),' Сохранить ')]")).click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//div[@class='flash-messages-holder']/div/div[@class='message']")));
        driver.findElement(By.xpath("//div[@class='flash-messages-holder']/div/div[@class='message']"))
                .getText().equals("Контактное лицо сохранено");

    }
}
