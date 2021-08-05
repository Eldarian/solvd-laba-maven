package com.eldarian.solvdelivery.services;

import com.eldarian.solvdelivery.database.dao.OrderDao;
import com.eldarian.solvdelivery.database.dao.OrderDaoImpl;
import com.eldarian.solvdelivery.database.dto.OrderDto;
import com.eldarian.solvdelivery.model.order.Order;

public class OrderServiceImpl implements OrderService{
    private OrderDao orderDao = new OrderDaoImpl();
    private DishService dishService = new DishServiceImpl();
    private CityService cityService = new CityServiceImpl();

    @Override
    public Order getOrder(int id) {
        OrderDto dto = orderDao.getOrderById(id);
        Order order = new Order();
        order.setId(id);
        order.setBuilding(cityService.findBuilding(dto.getStreet(), dto.getBuildingNumber()));
        order.setRestaurant(cityService.findRestaurant(dto.getRestaurantId()));
        order.setDish(dishService.findDish(dto.getDishId()));
        return order;
    }

    @Override
    public boolean addOrder(Order order) {
        return orderDao.insertOrder(getOrderDto(order));
    }

    @Override
    public boolean updateOrder(Order order) {
        return orderDao.updateOrder(getOrderDto(order));
    }

    @Override
    public boolean deleteOrder(int id) {
        return orderDao.deleteOrder(id);
    }

    private OrderDto getOrderDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setRestaurantId(order.getRestaurant().getId());
        dto.setDishId(order.getDish().getId());
        dto.setStreet(order.getBuilding().getStreetName());
        dto.setBuildingNumber(order.getBuilding().getBuildingNumber());
        return dto;
    }


}
