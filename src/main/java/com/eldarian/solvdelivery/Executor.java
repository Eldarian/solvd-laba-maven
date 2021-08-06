package com.eldarian.solvdelivery;

import com.eldarian.solvdelivery.client.Client;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class Executor {

	private static final Logger LOGGER = Logger.getLogger(Executor.class);

	public static void main(String[] args) {
		PropertyConfigurator.configure("src/main/resources/log4j.properties");
		LOGGER.info("logger configuration successful");
		Client client = new Client();
		client.execute();
	}

}
