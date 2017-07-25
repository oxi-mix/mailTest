package ru.mail.bm.at.steps;

import net.serenity_bdd.core.Serenity;
import net.serenity_bdd.core.annotations.findby.FindBy;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.WebElementFacade;
import net.thucydides.core.steps.ScenarioSteps;
import net.thucydides.core.webdriver.WebDriverFacade;
import net.thucydides.core.pages.PageObject;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mail.bm.at.data.ATConfiguration;
import ru.mail.bm.at.pages.*;
import ru.mail.bm.at.data.Common;

public class MailSteps extends ScenarioSteps {

	private static final Logger log = LoggerFactory.getLogger(ru.mail.bm.at.pages.MailPage.class);
	
	MailPage mailPage;
	Common common;

	XMLConfiguration config;
	
	
	@Before
	public void setUp() {
		try {
			config = new XMLConfiguration("./src/main/config/data/at-config.xml");
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	@Step("Открытие страницы с URL: {0}")
	public void startWithUrl(String startURL) {
		try {
			log.info("Starting browser with URL:" + startURL);
			mailPage.getDriver().get(startURL);
		}
		catch (Exception ex){
			log.debug("",ex);
			throw ex;
		}
	}
	
	@Step("ввод данных для логина start page")
	public void dataInputMailbox(String loginMailbox, String passwordMailbox) {
		try {
			common.inputValueType(mailPage.loginMailbox, loginMailbox);
			log.info("login:" + loginMailbox);
			common.inputValueType(mailPage.passwordMailbox, passwordMailbox);
			log.info("password:" + config.getString(ATConfiguration.pass));
		}
		catch (Exception ex){
			log.debug("",ex);
		}
	}

	@Step("переход для написания письма")
	public void transferWriteMail() {
		try {
			waitABit(2000);
			common.waitBeforeAppearanceOrTimeEnded(mailPage.btnTextPad,10);
			common.locatorClick(mailPage.btnTextPad);
		}
		catch (Exception ex){
			log.debug("",ex);
		}
	}
	
	@Step("Пишем письмо")
	public void writeMail(String letter) {
		try {
			
			common.waitBeforeAppearanceOrTimeEnded(mailPage.ifameLetter,10);
			
			//waitABit(2000);
			common.switchFrame(mailPage.ifameLetter);
			common.locatorClick(mailPage.bodyLetter);
			common.inputValueType(mailPage.bodyLetter, letter);
			common.switchToMainFrameDefaultContent();
		}
		catch (Exception ex){
			log.debug("",ex);
		}
	}
	

	@Step("Отправляем письмо")
	public void sendMail(String receiverAddress) {
		try {
			waitABit(2000);
			common.inputValueType(mailPage.receiverAddress, receiverAddress);
			common.locatorClick(mailPage.btnSendText);
		}
		catch (Exception ex){
			log.debug("",ex);
		}
	}
	
	@Step("Письмо отправлено")
	public void sentMail() {
		try {
			waitABit(2000);
			String mailResponseExpected = "Ваше письмо отправлено. Перейти во Входящие";
			String mailResponseReturned = common.getTextLocator(mailPage.sentMessage);
			
			if (mailResponseExpected.equals(mailResponseReturned)){
				System.out.println(common.getTextLocator(mailPage.sentMessage)); 
			}
			else
				throw new Exception("Can not find the item, or it is not"); 
		}
		catch (Exception ex){
			log.debug("",ex);
		}
	}

}

