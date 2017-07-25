package ru.mail.bm.at.pages;


import net.serenity_bdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.webdriver.WebDriverFacade;


import org.apache.commons.configuration.XMLConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MailPage extends PageObject {

	private static final Logger log = LoggerFactory.getLogger(MailPage.class);
	
    public  String loginMailbox ="//*[@id='mailbox__login']";
    public  String passwordMailbox ="//*[@id='mailbox__password']";
    public  String authButtonMailbox ="//*[@id='mailbox__auth__button']";
    
    /*
    public  String loginMailAccount ="//input[@name='Login']";
    public  String passwordMailAccount ="//input[@name='Password']";
    public  String authButtonMailAccount ="//*[contains(text(), normalize-space('Войти'))]/parent::button";
    */

    public  String btnTextPad =".//*[@id='b-toolbar__left']//span[@class='b-toolbar__btn__text b-toolbar__btn__text_pad']";
    
    public  String bodyLetter ="//*[@id='tinymce']";
    
    public  String ifameLetter = "//iframe[contains(@id,'toolkit')]";
    
    public  String receiverAddress ="//textarea[@tabindex='4']";
    public  String btnSendText ="//*[@class='b-toolbar__btn__text']";
    
    public  String sentMessage ="//*[@class='message-sent__title']";

	
}