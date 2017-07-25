package ru.mail.bm.at.data;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.io.FileUtils;
import org.fluentlenium.core.annotation.Page;
//import org.junit.*;
//import org.junit.Test;

//import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mail.bm.at.data.ATConfiguration;
import ru.mail.bm.at.pages.MailPage;


//import ru.betinaction.bm.at.requirements.BetMotion;
//import ru.betinaction.bm.at.steps.*;

import net.thucydides.core.annotations.*;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.pages.WebElementFacade;
//import net.thucydides.junit.runners.SerenityRunner;
import net.thucydides.core.pages.PageObject;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import net.thucydides.core.pages.PageObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import  ru.yandex.qatools.ashot.AShot ; 
import  ru.yandex.qatools.ashot.Screenshot ; 
import  ru.yandex.qatools.ashot.screentaker.ViewportPastingStrategy ;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Common extends PageObject {

	private static final Logger log = LoggerFactory.getLogger(Common.class);
	
	XMLConfiguration config; 
	
	public void setUp() {
		try {
			config = new XMLConfiguration("./src/main/config/data/at-config.xml");
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	
	/**текст по локатору*/
	public String getTextLocator(String xpath){
		return findBy(xpath).getText();
	}
	
	/**видимый ли элемент по локатору*/
	public boolean geIsDisplayedLocator(String locator){
		return findBy(locator).isVisible();
	}
	
	/**проверка наличия локатора*/
	public boolean needLocatorAvailable (String locator) {
		try {
			WebElementFacade locatorAvailable = findBy(locator);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	/**значение атрибута по локатору*/
	public String getAtributByLocator(String locator, String atribut){
		return findBy(locator).getAttribute(atribut);
	}
	
	/**Ждать пока не будет найдено или кончется время*/
	public void waitBeforeAppearanceOrTimeEnded(String locator, Integer time) throws Exception{

		int count_time = 1;
		while (Integer.valueOf(getDriver().findElements(By.xpath(locator)).size()) == 0)
		{
			wait(2);
			log.debug("Wait GUI || second");
			if (count_time == time) break;
			
			count_time = count_time + 1;
		}
		if(Integer.valueOf(getDriver().findElements(By.xpath(locator)).size()) == 0) { 
			throw new Exception("Can not find the item, or it is not"); 
		}
	}
	
	/**Кликаем на необходимый элемент*/
	public void locatorClick (String locator) {
		try{
			waitBeforeAppearanceOrTimeEnded(locator, 10);
			
			WebElementFacade locator_click = findBy(locator);
			locator_click.waitUntilPresent().click();
			log.debug("клик на локатор - " + locator);
		}
		catch (Exception ex){
			log.debug("",ex);
		}
	}
	
	/**Двойной клик на необходимый элемент*/
	public void locatorDoubleClick (String locator) {
		WebElementFacade locator_doubleClick = findBy(locator);
		Actions builder = new Actions(getDriver());
	    builder.doubleClick(locator_doubleClick).build().perform();
		log.debug("doubleClick на локатор - " + locator);
	}
	
	/**ввести значение в input*/
	public void inputValueType (String locator, String value) {
		 WebElementFacade input = findBy(locator).type(value);
		 log.debug("ввели по локатору - "+ locator + " значение " + value);
		 waitABit(1000);
	}
	

	/**переход в нужный фрейм*/
	public void needIframe(String locator_iframe) {
		(new WebDriverWait(getDriver(), 10))
        .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator_iframe));
	}

	/**Зайти внуть фрейма Frame*/
	public void switchToMainFrame(String nameFrame) {
		getDriver().switchTo().frame(nameFrame);
		log.debug("open frame - " + nameFrame);
		waitABit(1000);
	}

	/**Зайти внуть фрейма, если считать по счету*/
	public void switchToMainFrameIndex(Integer index) {
		getDriver().switchTo().frame(index);
		log.debug("open frame - " + index);
	}
	
	/**Переключиться в основное окно*/
	public void switchToMainFrameDefaultContent() {
		getDriver().switchTo().defaultContent();
		log.debug("defaultContent " );
	}

	/**Зайти внуть фрейма Frame*/
	public void switchFrame(String locatorFrame) {
		WebElement currentIframe = getDriver().findElement(By.xpath(locatorFrame));
		getDriver().switchTo().frame(currentIframe);
		log.debug("open frame - " + locatorFrame);
	}
}	

	


