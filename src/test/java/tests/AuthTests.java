package tests;

import pages.AuthPage;
import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.*;

//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthTests {

    private AuthPage authPage;

    @BeforeEach
    void setUp() {
        authPage = new AuthPage();
        authPage.open();
    }

    @Test
    void pageOpenTest() {
        authPage.open();
        assertThat(authPage.isAuthPageVisible()).isTrue();
    }

    @Test
    void shouldLoginWithValidCredentials() {
        var dataPage = authPage.login("test@protei.ru", "test");
        assertThat(dataPage.isDataInputPageVisible()).isTrue();
    }

    @Test
    void shouldShowErrorWhenEmailFormatInvalid() {
        authPage.loginWithInvalidData("EMEAIL- INVALID", "test");
        assertThat(authPage.isErrorMessageDisplayed("Неверный формат E-Mail")).isTrue();
        assertThat(authPage.isAuthPageVisible()).isTrue();
    }

    @Test
    void shouldShowErrorWhenWrongCredentials() {
        authPage.loginWithInvalidData("wrong@mail.ru", "wrong");
        assertThat(authPage.isErrorMessageDisplayed("Неверный E-Mail или пароль")).isTrue();
        assertThat(authPage.isAuthPageVisible()).isTrue();
    }

    @Test
    void shouldShowErrorWhenEmailEmpty() {
        authPage.loginWithInvalidData("", "test");
        assertThat(authPage.isErrorMessageDisplayed("Неверный формат E-Mail")).isTrue();
        assertThat(authPage.isAuthPageVisible()).isTrue();
    }

    @Test
    void shouldShowErrorWhenPasswordEmpty() {
        authPage.loginWithInvalidData("test@protei.ru", "");
        assertThat(authPage.isErrorMessageDisplayed("Неверный E-Mail или пароль")).isTrue();
        assertThat(authPage.isAuthPageVisible()).isTrue();
    }
}