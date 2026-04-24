package tests;

import pages.AuthPage;
import pages.DataInputPage;
import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.*;

//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DataInputTests {

    private DataInputPage dataPage;

    @BeforeEach
    void setUp() {
        AuthPage authPage = new AuthPage();
        authPage.open();
        dataPage = authPage.login("test@protei.ru", "test");
    }

    @Test
    void shouldAddRecordWithAllFieldsFilled() {
        int initialCount = dataPage.getTableRowCount();

        dataPage.fillEmail("user@test.ru");
        dataPage.fillName("Иван");
        dataPage.selectGender("Мужской");
        dataPage.checkOption11(true);
        dataPage.checkOption12(true);
        dataPage.selectRadio("2.1");
        dataPage.submitForm();

        assertThat(dataPage.getTableRowCount()).isEqualTo(initialCount + 1);
        assertThat(dataPage.isTableContainsRow("user@test.ru", "Иван", "Мужской", "1.1, 1.2", "2.1")).isTrue();
    }

    @Test
    void shouldShowErrorWhenEmailInvalid() {
        dataPage.fillEmail("invalid-email");
        dataPage.fillName("Петр");
        dataPage.submitForm();

        assertThat(dataPage.isErrorMessageDisplayed("Неверный формат E-Mail")).isTrue();
    }

    @Test
    void shouldShowErrorWhenNameEmpty() {
        dataPage.fillEmail("valid@test.ru");
        dataPage.fillName("");
        dataPage.submitForm();

        assertThat(dataPage.isErrorMessageDisplayed("Поле имя не может быть пустым")).isTrue();
    }

    @Test
    void shouldAddRecordWithNoCheckboxesSelected() {
        int count = dataPage.getTableRowCount();

        dataPage.fillEmail("nocheck@test.ru");
        dataPage.fillName("Анна");
        dataPage.selectGender("Женский");
        dataPage.checkOption11(false);
        dataPage.checkOption12(false);
        dataPage.selectRadio("2.2");
        dataPage.submitForm();

        assertThat(dataPage.getTableRowCount()).isEqualTo(count + 1);
        assertThat(dataPage.isTableContainsRow("nocheck@test.ru", "Анна", "Женский", "Нет", "2.2")).isTrue();
    }


    //TODO Падает тест, т.к. нет подтверждения формы. В будущем добавить.
    @Disabled
    @Test
    void shouldAddMultipleRecordsSequentially() {
        int count = dataPage.getTableRowCount();

        dataPage.fillEmail("first@test.ru");
        dataPage.fillName("Первый");
        dataPage.selectGender("Мужской");
        dataPage.checkOption11(true);
        dataPage.selectRadio("2.3");
        dataPage.submitForm();

        dataPage.fillEmail("second@test.ru");
        dataPage.fillName("Второй");
        dataPage.selectGender("Женский");
        dataPage.checkOption12(true);
        dataPage.selectRadio("2.1");
        dataPage.submitForm();

        assertThat(dataPage.getTableRowCount()).isEqualTo(count + 2);
    }
}