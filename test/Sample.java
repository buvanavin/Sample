package org.test;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bass.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.Select;
import org.pojo.LoginPojo;

public class Sample extends BaseClass {
	public static void main(String[] args) throws IOException, InterruptedException, AWTException  {
		browserLaunch("chrome");
		passUrl(" http://www.adactin.com/HotelApp/");
		LoginPojo l=new LoginPojo();
		toInput(l.getTxtUser(), excelRead(5, 0));
		toInput(l.getTxtPass(), excelRead(5, 1));
		driver.findElement(By.id("login")).click();
		driver.findElement(By.id("location")).click();
		Robot r=new Robot();
		multikeys("down");
		multikeys("enter");
		driver.findElement(By.id("hotels")).click();
		multikeys("down");
		multikeys("down");
		multikeys("enter");
		WebElement drop = driver.findElement(By.xpath("//select[@id='room_type']"));
		drop.click();
	    Select s=new Select(drop);
	    s.selectByVisibleText("Super Deluxe");
	 /* WebElement room = driver.findElement(By.xpath("//select[@id='room_nos']"));
	    room.click();
	    Select s1=new Select(room);
	    s.selectByIndex(6); */
	    driver.findElement(By.xpath("//select[@id='adult_room']")).click();
	    s.selectByValue("3");
				
	}
}
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="Suite">
  <test thread-count="5" name="Test">
  
  <parameter name="username" value="java"></parameter>
  <parameter name="password" value="java@123"></parameter>
    <classes>
      <class name="org.tng.C"/>
      <class name="org.tng.B"/>
      <class name="org.tng.A"/>
    </classes>
  </test> <!-- Test --> 
</suite> <!-- Suite -->
