/**
 * 
 */
package com.poc.shoppingcart.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.stereotype.Service;

/**
 * @author ArunabhaB
 *
 */
@Service
public class ShoppingCartProperties {
	
	private Properties prop = null;

	public ShoppingCartProperties() {
		InputStream is = null;
		try {
			this.prop = new Properties();
			is = this.getClass().getResourceAsStream("/shoppingcart.properties");
			prop.load(is);
		} catch (final FileNotFoundException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public String getPropertyValue(final String key) {
		return this.prop.getProperty(key);
	}
}
