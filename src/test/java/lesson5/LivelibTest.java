package lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;

public class LivelibTest {
    static WebDriver driver;
    static WebDriverWait webDriverWait;
    private static final String BASE_URL = "https://www.livelib.ru/";

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @Nested
    class OnlyLoginTest {
        @BeforeEach
        void setupBrowser() {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--disable-notifications");
            driver = new ChromeDriver(chromeOptions);
            webDriverWait = new WebDriverWait(driver, 10);
        }

        @Test
        void login() {
            driver.get(BASE_URL);
            driver.findElement(By.id("checkin-email-block")).sendKeys("BeattleTranced");

            driver.findElement(By.xpath("//button[contains(text(),'Продолжить')]")).click();

            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("user[password]")));
            driver.findElement(By.name("user[password]")).sendKeys("7h3f4f3t85");

            driver.findElement(By.id("user[submit]")).click();

            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//summary[@class='user-nav__toggle']/img[@alt='BeattleTranced']"))).click();

            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Мои книги')]")));
            assertThat(driver.findElement(By.xpath("//a[contains(text(),'Мои книги')]")), hasText("МОИ КНИГИ"));
        }

        @Nested
        class RestTests {
            @BeforeEach
                    void loginAndCookieAgree() {
                        login();
                        cookiesAgree();
                    }

            @Test
            void addBookToReadingNowListFromWantToRead() {
                driver.get(BASE_URL + "reader/BeattleTranced/wish");

                webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(@id,'span-userbook')]")));
                List<WebElement> wantToReadButtons = driver.findElements(By.xpath("//a[contains(@id,'span-userbook')]"));
                wantToReadButtons.get(0).click();

                webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[text()='Выбор действия']")));
                webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='read-now']/parent::div")));
                driver.findElement(By.xpath("//div[@class='add-book__action-item']/input[@type='radio'][@value='2']/following-sibling::label")).click();

                driver.findElement(By.xpath("//div[contains(@class,'add-book__footer-buttons')]/button[contains(text(),'Сохранить')]"))
                        .click();

                webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@title,'Читаю сейчас')]")));
                assertThat(driver.findElement(By.xpath("//a[contains(@title,'Читаю сейчас')]")), hasText("Читаю"));
            }

            @Test
            void addBookToWishList() {
                driver.get(BASE_URL + "book/1001350571-soyuz-kapitana-forpatrila-lois-makmaster-budzhold");

                webDriverWait.until(ExpectedConditions
                        .presenceOfElementLocated(By.xpath("//div[@data-view_mode='main']/a[contains(@id,'span-userbook')]")));
                driver.findElement(By.xpath("//div[@data-view_mode='main']/a[contains(@id,'span-userbook')]")).click();

                webDriverWait.until(ExpectedConditions
                        .presenceOfElementLocated(By.xpath("//span/a[@href='/reader/BeattleTranced/wish']")));
                assertThat(driver.findElement(By.xpath("//span/a[@href='/reader/BeattleTranced/wish']")),
                        hasText("Хочу прочитать"));
            }

            @Test
            void deleteBookFromWishList() {
                driver.get(BASE_URL + "reader/BeattleTranced/wish");

                webDriverWait.until(ExpectedConditions
                        .presenceOfAllElementsLocatedBy(By.xpath("//a[contains(@data-id,'my-book-list-checkbox')]/span")));
                List<WebElement> selectCheckbox = driver.findElements(By.xpath("//a[contains(@data-id,'my-book-list-checkbox')]/span"));
                selectCheckbox.get(0).click();

                webDriverWait.until(ExpectedConditions.
                        presenceOfAllElementsLocatedBy(By.xpath("//div[@id='my-book-list-block']")));
                driver.findElement(By.xpath("//select[@id='my-book-list-select']")).click();

                webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//option[@value='delete']")));
                driver.findElement(By.xpath("//option[@value='delete']")).click();

                driver.findElement(By.xpath("//input[@value='Применить']")).click();
                driver.switchTo().alert().accept();

                webDriverWait.until(driver -> driver.findElements(By.xpath("//div[@class='brow-priority']/span")).get(0)
                        .getText().equals("№ 2"));
                List<WebElement> wishList = driver.findElements(By.xpath("//div[@class='brow-priority']/span"));
                assertThat(wishList.get(0), hasText("№ 2"));
            }
        }

        @AfterEach
        void tearDown() {
            driver.quit();
        }
    }

    public static void cookiesAgree() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='btn-cookies-agree']")));
        driver.findElement(By.xpath("//div[@class='btn-cookies-agree']")).click();
    }
}
