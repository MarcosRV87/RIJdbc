package uo.ri.amp.conf;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/*
 * Clase utilizada para externalizar las consultas SQL.
 */
public class Conf {

	private static Conf instance;
	private Properties properties;

	private Conf() {
		properties = new Properties();
		try {
			properties
					.load(new FileInputStream("src/configuration.properties"));
		} catch (IOException e) {
			throw new RuntimeException("Properties file can not be loaded", e);
		}
	}

	public static String get(String key) {
		return getInstance().getProperty(key);
	}

	private String getProperty(String key) {
		String value = properties.getProperty(key);
		if (value == null) {
			throw new RuntimeException("Property not found in config file");
		}
		return value;
	}

	private static Conf getInstance() {
		if (instance == null) {
			instance = new Conf();
		}
		return instance;
	}

}
