package com.Vytrack.tests;

import com.Vytrack.pages.login.LoginPage;
import com.Vytrack.utilities.ConfigurationReader;
import com.Vytrack.utilities.TestBase;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @Test
    public void loginTest(){
        LoginPage loginPage=new LoginPage();
        String username= ConfigurationReader.getProperty("storemanagerusername");
        String pasword=ConfigurationReader.getProperty("storemanagerpassword");
        loginPage.login(username,pasword);
    }

}
