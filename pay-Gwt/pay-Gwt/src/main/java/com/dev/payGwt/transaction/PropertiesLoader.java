package com.dev.payGwt.transaction;

import java.util.Properties;
import java.util.Set;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Class to load status messages from file message.properties
 * 
 * @author Divyanshu
 * @since 27/09/2018
 */

@Component
public final class PropertiesLoader {
	 
	 private final Properties msg = new Properties();
	 private PropertiesLoader() {
	        String filePath = "C:\\Users\\z003yjtj\\Downloads\\Src\\pay-Gwt\\pay-Gwt\\src\\main\\resources\\message.properties";
	        InputStream inStream;
			try {
				inStream = new FileInputStream(new File(filePath));
				try {
					msg.load(inStream);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	    }
	 
	 private static class LazyHolder {
		 private static final PropertiesLoader INSTANCE = new PropertiesLoader();
	 }
	 
	 public static PropertiesLoader getInstance() {
		 return LazyHolder.INSTANCE;
	 }
	 
	 public String getProperty(final String key) {
		 return msg.getProperty(key);
	 }
	 
	 public Set<Object> getAllPropertyNames() {
		 return msg.keySet();
	 }

	 public boolean containsKey(final String key) {
		 return msg.containsKey(key);
	 }

	 public void setProperty(final String key, final String value) {
		 msg.setProperty(key, value);
	 }
}
