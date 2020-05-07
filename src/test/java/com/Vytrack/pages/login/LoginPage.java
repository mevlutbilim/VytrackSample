package com.Vytrack.pages.login;

import com.Vytrack.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @FindBy(id="prependedInput")
    public WebElement usernameElement;

    @FindBy(id="prependedInput2")
    public WebElement passwordElement;

    @FindBy(id="_submit")
    public WebElement loginButonElement;

    public LoginPage() {
        PageFactory.initElements(Driver.getDriver(),this);
    }
    public void login(String username,String password){
        usernameElement.sendKeys(username);
        passwordElement.sendKeys(password);
        loginButonElement.click();
    }
}
