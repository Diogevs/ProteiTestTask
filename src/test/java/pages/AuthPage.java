package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class AuthPage {

    private final SelenideElement emailInput = $("#loginEmail");
    private final SelenideElement passwordInput = $("#loginPassword");
    private final SelenideElement loginButton = $("#authButton");
    private final SelenideElement alertsHolder = $("#authAlertsHolder");

    public void  open() {
        String path = "file:///" + System.getProperty("user.dir") + "/src/test/resources/qa-test.html";
        Selenide.open(path);
    }

    public DataInputPage login(String email, String password) {
        emailInput.setValue(email);
        passwordInput.setValue(password);
        loginButton.click();
        return page(DataInputPage.class);
    }

    public void loginWithInvalidData(String email, String password) {
        emailInput.setValue(email);
        passwordInput.setValue(password);
        loginButton.click();
    }

    public boolean isErrorMessageDisplayed(String messageText) {
        return alertsHolder.$(".uk-alert-danger").exists() &&
                alertsHolder.getText().contains(messageText);
    }

    public boolean isAuthPageVisible() {
        return $("#authPage").isDisplayed();
    }
}