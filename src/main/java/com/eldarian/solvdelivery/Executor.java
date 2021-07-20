package com.eldarian.solvdelivery;

import com.eldarian.solvdelivery.ordering.Client;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class Executor {

	static Logger logger = Logger.getLogger(Executor.class);
	public static void main(String[] args) {
		//PropertyConfigurator.configure("log4j.properties");
		logger.info("logger configuration successful");
		Client client = new Client();
		client.contactOperator();
	}

}
