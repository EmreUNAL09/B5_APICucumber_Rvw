package com.devEx.pages;

import com.devEx.utilities.ConfigurationReader;
import com.devEx.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    @FindBy(name = "email")
    public WebElement emailInput;

    @FindBy(name = "password")
    public WebElement passwordInput;

    @FindBy(xpath = "//input[@type='submit']")
    public WebElement loginBtnLoginPage;

    @FindBy(partialLinkText = "Forgot")
    public WebElement forgotPassword;

    @FindBy(xpath = "//*[text()='Invalid Credentials!']")
    public WebElement invalidText;


    public void loginTeacher(){
        String email= ConfigurationReader.get("email");
        String password = ConfigurationReader.get("password");
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        loginBtnLoginPage.click();
    }

    public void login(String username,String password){
        understandBtn.click();
        loginBtnBasePage.click();
        emailInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginBtnLoginPage.click();
    }

    //1- BU KISIMLARI KOPYALAMA, YAZ, AYNI ZAMANDA CONFİGURATİON İÇİNE url EKLE
    public void setup() throws InterruptedException {
        Driver.get().get(ConfigurationReader.get("url"));
        login(ConfigurationReader.get("email"),ConfigurationReader.get("password")); // BANA AİT BİLGİLERİ ALIYORUM
        Thread.sleep(2000);
    }
    //2- OLUTURULAN YENİ EXPERİENCE LOCATE EDİLMELİ
    public String getNewCompany(String name){
        return    Driver.get().findElement(By.xpath("//td[text()='"+name+"']")).getText();
    }
}
