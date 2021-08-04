package com.eldarian.solvdelivery.services;

import com.eldarian.solvdelivery.model.staff.Manager;
import com.eldarian.solvdelivery.model.staff.contact.PhoneOperator;
import com.eldarian.solvdelivery.model.staff.contact.WebOperator;
import com.eldarian.solvdelivery.model.staff.delivery.AutoCourier;
import com.eldarian.solvdelivery.model.staff.delivery.FootCourier;

import java.util.ArrayList;
import java.util.List;

public class StaffServiceEmu {
    private List<Manager> managers;

    private static StaffServiceEmu instance;

    private StaffServiceEmu() {
        /*initStaff();*/
    }

    //hardcoded method with data
    /*private void initStaff() {
        managers = new ArrayList<>();
        Manager manager = new Manager(CityServiceEmu.getInstance());
        manager.addCourier(new FootCourier("Benny"));
        manager.addCourier(new AutoCourier("Jack"));
        manager.addOperator(new PhoneOperator(manager));
        manager.addOperator(new WebOperator(manager));
        managers.add(manager);
    }*/

    public Manager getManager() {
        return managers.get(0); //TODO replace to search of free manager or make single-manager system
    }

    public static StaffServiceEmu getInstance() {
        if (instance == null) {
            instance = new StaffServiceEmu();
        }
        return instance;
    }
}
