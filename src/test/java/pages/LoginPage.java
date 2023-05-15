package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {
    private final String LOGIN_URL = "/";
    private final SelenideElement
            loginInput = $("input[placeholder='Username']"),
            passwordInput = $("input[placeholder='Password']"),
            submitButton = $("button[type='submit']");

    public LoginPage openPage() {
        open(LOGIN_URL);

        return this;
    }

    public LoginPage setLogin(String value) {
        loginInput.setValue(value);

        return this;
    }

    public LoginPage setPassword(String value) {
        passwordInput.setValue(value);

        return this;
    }

    public void clickSubmit() {
        submitButton.click();
    }
}
