package com.sample.configuration;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.sample.constants.Constants;

public class Configuration {

	Constants constants = new Constants();


	InputStream input = null;

	Properties prop = new Properties();

	public Configuration() {

		try {
			input = new FileInputStream(constants.resourcesPath+"Config"+".properties");
			prop.load(input);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public String getBrowser() {
		return prop.getProperty("Browser");
	}
	public String getUrl() {
		return prop.getProperty("URL");
	}
	public String getUsernameFB() {
		return prop.getProperty("UsernameFB");
	}
	public String getPasswordFB() {
		return prop.getProperty("PasswordFB");
	}
	public String getUrlWallet() {
		return prop.getProperty("URLWallet");
	}
	public String getUsernameWallet() {
		return prop.getProperty("Username_WH");
	}
	public String getPasswordWallet() {
		return prop.getProperty("Password_WH");
	}

}
