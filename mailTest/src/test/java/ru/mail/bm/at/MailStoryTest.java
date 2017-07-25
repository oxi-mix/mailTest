package ru.mail.bm.at;

import java.io.IOException;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mail.bm.at.data.ATConfiguration;
import ru.mail.bm.at.data.Common;
import ru.mail.bm.at.pages.MailPage;
import ru.mail.bm.at.steps.*;
import net.thucydides.core.annotations.*;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.webdriver.WebDriverFacade;
import net.thucydides.junit.runners.SerenityRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;


//@Story(Test.MailPage.PositiveTest.class)
@RunWith(SerenityRunner.class)
public class MailStoryTest {
	private static final Logger log = LoggerFactory.getLogger(MailStoryTest.class);
	
	
	@Managed
	WebDriver driver;
	
	@ManagedPages
	Pages pages;
	Common common;
	
	@Steps
	MailSteps mailSteps;
	
	XMLConfiguration config;
	
	MailPage mailPage;
	

	@Before
	public void setUp() {
		try {
			config = new XMLConfiguration("./src/main/config/data/at-config.xml");
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Title("Login-1. mail - start page")
	public void loginMailStartPage() {

		String targetUrl = config.getString(ATConfiguration.start_page);
		log.debug("Url page: {}", targetUrl);
		//загружаем страницу
		mailSteps.startWithUrl(targetUrl);
		//вводим данные пользователя
		mailSteps.dataInputMailbox(config.getString(ATConfiguration.login), config.getString(ATConfiguration.pass));
		//кликаем на кнопку войти
		common.locatorClick(mailPage.authButtonMailbox);
		//переходим по кнопке Написать письмо
		mailSteps.transferWriteMail();
		//пишем письмо
		mailSteps.writeMail(config.getString(ATConfiguration.letter));
		//вводим данные адресата
		mailSteps.sendMail(config.getString(ATConfiguration.receiver_address));
		//отправляем письмо
		mailSteps.sentMail();
		
	}

	/*
	@Test
	@Title("Login-2. mail - account page")
	public void loginMailAccountPage() {

		try{
			String targetUrl = config.getString(ATConfiguration.account_page);
			log.debug("Url page: {}", targetUrl);
			//загружаем страницу
			mailSteps.startWithUrl(targetUrl);
			//вводим данные пользователя
			mailSteps.dataInputMail(config.getString(ATConfiguration.login), config.getString(ATConfiguration.pass));
			//кликаем на кнопку войти
			common.locatorClick(mailPage.authButtonMailAccount);
			//переходим по кнопке Написать письмо
			mailSteps.transferWriteMail();
			//пишем письмо
			mailSteps.writeMail(config.getString(ATConfiguration.letter));
			//вводим данные адресата
			mailSteps.sendMail(config.getString(ATConfiguration.receiver_address));
			//отправляем письмо
			mailSteps.sentMail();
		}
		catch (Exception ex){
			log.debug("",ex);
		}
		
	}
	*/
	
/*	
	@Test
	@Title("Login-2. mail - Portal page")
	public void loginMailPortalPage() {

		try{
			String targetUrl = config.getString(ATConfiguration.portal_page);
			log.debug("Url page: {}", targetUrl);
			//загружаем страницу
			mailSteps.startWithUrl(targetUrl);
			//вводим данные пользователя
			mailSteps.dataInputMail(config.getString(ATConfiguration.login), config.getString(ATConfiguration.pass));
			//кликаем на кнопку войти
			common.locatorClick(mailPage.authButtonMailAccount);
			//переходим по кнопке Написать письмо
			mailSteps.transferWriteMail();
			//пишем письмо
			mailSteps.writeMail(config.getString(ATConfiguration.letter));
			//вводим данные адресата
			mailSteps.sendMail(config.getString(ATConfiguration.receiver_address));
			//отправляем письмо
			mailSteps.sentMail();
		}
		catch (Exception ex){
			log.debug("",ex);
		}
		
	}
*/

}
