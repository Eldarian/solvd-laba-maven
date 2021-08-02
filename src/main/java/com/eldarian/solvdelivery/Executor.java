package com.eldarian.solvdelivery;

import com.eldarian.solvdelivery.database.DAO.OrderDao;
import com.eldarian.solvdelivery.database.DAO.OrderDaoImpl;
import org.apache.log4j.Logger;


public class Executor {

	private static Logger logger = Logger.getLogger(Executor.class);
	public static void main(String[] args) {
		OrderDao orderDAO = new OrderDaoImpl();
		orderDAO.printAllOrders();
//		PropertyConfigurator.configure("log4j.properties");
//		logger.info("logger configuration successful");
//		Client client = new Client();
//		client.contactOperator();
	}

}
