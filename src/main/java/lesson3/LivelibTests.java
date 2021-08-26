package lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class LivelibTests {
    public static WebDriver driver;
    public static WebDriverWait webDriverWait;

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
        driver = new ChromeDriver(chromeOptions);
        webDriverWait = new WebDriverWait(driver, 10);

        login();
        cookiesAgree();
        addBookToReadingNowListFromWantToRead();
        driver.quit();
    }

    public static void login() {
        driver.get("https://www.livelib.ru/");
        driver.findElement(By.id("checkin-email-block")).sendKeys("BeattleTranced");

        driver.findElement(By.xpath("//button[contains(text(),'Продолжить')]")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("user[password]")));
        driver.findElement(By.name("user[password]")).sendKeys("7h3f4f3t85");

        driver.findElement(By.id("user[submit]")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//summary[@class='user-nav__toggle']/img[@alt='BeattleTranced']"))).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Мои книги')]")));
        driver.findElement(By.xpath("//a[contains(text(),'Мои книги')]")).getText().equals("МОИ КНИГИ");
    }

    public static void cookiesAgree() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='btn-cookies-agree']")));
        driver.findElement(By.xpath("//div[@class='btn-cookies-agree']")).click();
    }

    public static void addBookToReadingNowListFromWantToRead() {
        driver.get("https://www.livelib.ru/reader/BeattleTranced/wish");

        webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(@id,'span-userbook')]")));
        List<WebElement> wantToReadButtons = driver.findElements(By.xpath("//a[contains(@id,'span-userbook')]"));
        wantToReadButtons.get(0).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[text()='Выбор действия']")));
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='read-now']/parent::div")));
        driver.findElement(By.xpath("//div[@class='add-book__action-item']/input[@type='radio'][@value='2']/following-sibling::label")).click();

        driver.findElement(By.xpath("//div[contains(@class,'add-book__footer-buttons')]/button[contains(text(),'Сохранить')]"))
                .click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@title,'Читаю сейчас')]")));
        driver.findElement(By.xpath("//a[contains(@title,'Читаю сейчас')]")).getText().equals(" Читаю ");
    }
}
