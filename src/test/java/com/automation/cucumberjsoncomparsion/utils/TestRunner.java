package com.automation.cucumberjsoncomparsion.utils;

import java.io.File;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * @author Sudharsan
 *
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = { "classpath:" }, plugin = {
		ProjectConstants.PLUGIN }, glue = ProjectConstants.GLUE, monochrome = true)

public class TestRunner {
	@AfterClass
	public static void setup() {
		Reporter.loadXMLConfig(new File("src/test/resource/extent-config.xml"));
		Reporter.setSystemInfo("Project", ProjectConstants.PROJECT);
	}

}
