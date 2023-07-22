package com.coober.service;

import java.util.List;

import com.coober.modal.Driver;

public interface DriverService {

    public Driver insertDriver(Driver driver);

    public Driver updateDriver(Driver driver);

    public Driver deleteDriver(Integer driverId);

    public List<Driver> viewBestDrivers();

    public Driver viewDriver(Integer driverId);

    public Driver viewDriverByUserName(String userName);

}
