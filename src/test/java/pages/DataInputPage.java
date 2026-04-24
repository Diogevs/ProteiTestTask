package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class DataInputPage {

    private final SelenideElement emailInput = $("#dataEmail");
    private final SelenideElement nameInput = $("#dataName");
    private final SelenideElement genderSelect = $("#dataGender");

    private final SelenideElement check11 = $("#dataCheck11");
    private final SelenideElement check12 = $("#dataCheck12");

    private final SelenideElement radio21 = $("#dataSelect21");
    private final SelenideElement radio22 = $("#dataSelect22");
    private final SelenideElement radio23 = $("#dataSelect23");

    private final SelenideElement submitButton = $("#dataSend");
    private final SelenideElement alertsHolder = $("#dataAlertsHolder");
    private final ElementsCollection tableRows = $$("#dataTable tbody tr");

    public void fillEmail(String email) {
        emailInput.setValue(email);
    }

    public void fillName(String name) {
        nameInput.setValue(name);
    }

    public void selectGender(String gender) {
        genderSelect.selectOption(gender);
    }

    public void checkOption11(boolean checked) {
        if (checked && !check11.isSelected()) check11.click();
        if (!checked && check11.isSelected()) check11.click();
    }

    public void checkOption12(boolean checked) {
        if (checked && !check12.isSelected()) check12.click();
        if (!checked && check12.isSelected()) check12.click();
    }

    public void selectRadio(String optionId) {
        switch(optionId) {
            case "2.1": radio21.click(); break;
            case "2.2": radio22.click(); break;
            case "2.3": radio23.click(); break;
        }
    }

    public void submitForm() {
        submitButton.click();
    }

    public boolean isErrorMessageDisplayed(String messageText) {
        return alertsHolder.$(".uk-alert-danger").exists() &&
                alertsHolder.getText().contains(messageText);
    }

    public int getTableRowCount() {
        return tableRows.size();
    }

    public boolean isTableContainsRow(String email, String name, String gender,
                                      String choice1, String choice2) {
        for (SelenideElement row : tableRows) {
            String rowText = row.getText();
            if (rowText.contains(email) && rowText.contains(name) &&
                    rowText.contains(gender) && rowText.contains(choice1) &&
                    rowText.contains(choice2)) {
                return true;
            }
        }
        return false;
    }

    public boolean isDataInputPageVisible() {
        return $("#inputsPage").isDisplayed();
    }
}