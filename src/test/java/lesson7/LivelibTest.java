package lesson7;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@Story("Тесты для Livelib")
public class LivelibTest extends BaseLiveLibTest {

    public static final String BASE_URL = "https://www.livelib.ru/";

    @Nested
    @Story("Тест логина")
    class OnlyLoginTest {

        @Test
        @Description("Тест логина")
        void loginToLiveLib() {
            driver.get(BASE_URL);
            new LivelibMainLoggedOutPage(driver)
                    .fillLogin("BeattleTranced")
                    .clickProceedButton()
                    .fillPassword("7h3f4f3t85")
                    .clickSubmitButton()
                    .openUserNav()
                    .assertLogin();
        }

        @Nested
        @Story("Тесты добавления книги в списки 'Хочу прочитать', 'Читаю сейчас' и удаление книги из списка 'Хочу прочитать'")
        class RestTests {
            @BeforeEach
            void loginAndCookieAgree() {
                loginToLiveLib();
                new LivelibMainLoggedPage(driver)
                        .cookiesAgree();
            }

            @Test
            @Description("Тест добавления книги в список 'Читаю сейчас' из списка 'Хочу прочитать'")
            void addBookToReadingNowListFromWantToRead() {
                driver.get(BASE_URL + "reader/BeattleTranced/wish");
                new LivelibWishPage(driver)
                        .openMenuChoiceOfBookStatus()
                        .changeStatusToReadNow()
                        .saveBookStatus()
                        .assertReadNowStatus();
            }

            @Test
            @Description("Тест добавления книги в список 'Хочу прочитать'")
            void addBookToWishList() {
                driver.get(BASE_URL + "book/1001350571-soyuz-kapitana-forpatrila-lois-makmaster-budzhold");
                new LivelibBookPage(driver)
                        .clickButtonToAddBookToWishList()
                        .assertBookInWishList();
            }

            @Test
            @Description("Тест удаления книги из списка 'Хочу прочитать'")
            void deleteBookFromWishList() {
                driver.get(BASE_URL + "reader/BeattleTranced/wish");
                new LivelibWishPage(driver)
                        .openChoiceOfActionBlock()
                        .chooseAnActionForThisBook("Удалить")
                        .applyAction()
                        .assertFirstBookNumber();
            }
        }
    }
}
