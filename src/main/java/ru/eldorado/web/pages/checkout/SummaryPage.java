package ru.eldorado.web.pages.checkout;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.eldorado.web.pages.AbstractPage;

public class SummaryPage extends AbstractPage {
    //Для Хайбрис:
    @FindBy(how = How.ID, id = "USER_FACE_NAME")
    private SelenideElement firstName;

    @FindBy(how = How.ID, id = "USER_FACE_LAST_NAME")
    private SelenideElement lastName;

    @FindBy(how = How.ID, id = "USER_EMAIL_PICKUP")
    private SelenideElement email;

    @FindBy(how = How.CSS, css = ".selfDeliveryButton input[type='submit']")
    private SelenideElement submit;

    //Для Битрикс:
    @FindBy(how = How.NAME, name = "USER_FACE_NAME")
    private SelenideElement firstNameBitrix;

    @FindBy(how = How.NAME, name = "USER_FACE_LAST_NAME")
    private SelenideElement lastNameBitrix;

    @FindBy(how = How.ID, id = "mobile_phone_code")
    private SelenideElement phoneCode;

    @FindBy(how = How.ID, id = "mobile_phone_phone")
    private SelenideElement phoneNumber;

    @FindBy(how = How.NAME, name = "USER_EMAIL")
    private SelenideElement emailBitrix;

    @FindBy(how = How.CSS, css = ".orderConfirmSubmitButton")
    private SelenideElement submitButton;

    public void setFirstName(String firstName) {
        this.firstNameBitrix.setValue(firstName);
    }

    public void setLastName(String lastName) {
        this.lastNameBitrix.setValue(lastName);
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode.setValue(phoneCode);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.setValue(phoneNumber);
    }

    public void setEmail(String email) {
        this.emailBitrix.setValue(email);
    }

    public void submit() {
        submitButton.click();
    }
}
