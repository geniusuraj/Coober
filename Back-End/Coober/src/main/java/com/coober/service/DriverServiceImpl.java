package com.coober.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.coober.Exception.CooberException;
import com.coober.Exception.NotFoundException;
import com.coober.modal.Driver;
import com.coober.repository.DriverRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DriverServiceImpl implements DriverService{

    @Autowired
    private DriverRepository driverRepository;


    /**
     * @param driver
     * @return Driver
     * Take a driver object to be persisted and return persisted driver;
     */
    @Override
    public Driver insertDriver(Driver driver) {
        log.info("Class: DriverServiceImpl, method: insertDriver started ");

        // checking for null;
        if (driver==null) throw new CooberException("null value");

        // checking for already existing username;
         Optional<Driver> opt= driverRepository.findByUserName(driver.getUserName());
         if(opt.isPresent())
             throw new CooberException("This username already exists, please provide another username");

         // checking for already existing mobile number;
        Optional<Driver> opt1= driverRepository.findByMobileNumber(driver.getMobileNumber());
        if(opt1.isPresent())
            throw new CooberException("This mobile number already exists, please provide another mobile number");

        // checking for already existing email;
        Optional<Driver> opt2= driverRepository.findByEmail(driver.getEmail());
        if(opt2.isPresent())
            throw new CooberException("This email already exists, please provide another email");

        //persisting the driver object;
        Driver persistedDriver= driverRepository.save(driver);
        log.info("Class: DriverServiceImpl, method: insertDriver returned "+ persistedDriver);
        return persistedDriver;
    }

    /**
     * @param driver
     * @return Driver
     * Take a driver object to be updated, return updated driver;
     */
    @Override
    public Driver updateDriver(Driver driver) {
        log.info("Class: DriverServiceImpl, method: updateDriver started ");

        // checking for null;
        if (driver==null) throw new CooberException("null value");

        Optional<Driver> opt= driverRepository.findByUserName(driver.getUserName());
        if(opt.isEmpty())
            throw new CooberException("This username does not exists");

        //updating the driver object;
        Driver updatedDriver= driverRepository.save(driver);
        log.info("Class: DriverServiceImpl, method: updateDriver returned "+ updatedDriver);
        return updatedDriver;
    }

    /**
     * @param driverId
     * @return Driver
     * Take driver id of Integet type, return deletedDriver;
     */
    @Override
    public Driver deleteDriver(Integer driverId) {
        log.info("Class: DriverServiceImpl, method: deleteDriver started ");

        Optional<Driver> opt= driverRepository.findById(driverId);
        if(opt.isEmpty())
            throw new NotFoundException("Driver with id: "+driverId+ "does not exist.");

        driverRepository.deleteById(driverId);
        log.info("Class: DriverServiceImpl, method: deleteDriver returned deleted driver");
        return opt.get();
    }

    /**
     * @return List<Driver>
     *     return list of drivers having rating >=4.5 ;
     */
    @Override
    public List<Driver> viewBestDrivers() {
        log.info("Class: DriverServiceImpl, method: viewBestDrivers started ");

        List<Driver> drivers= driverRepository.findByRatingGreaterThanEqual(4.5F);
        if(drivers.isEmpty())
            throw new NotFoundException("No driver found");
        log.info("Class: DriverServiceImpl, method: viewBestDrivers ended ");
        return drivers;
    }

    /**
     * @param driverId
     * @return Driver
     * Take driver id of integer type, returns driver;
     */
    @Override
    public Driver viewDriver(Integer driverId) {
        log.info("Class: DriverServiceImpl, method: viewDriver started ");
        Driver driver= driverRepository.findById(driverId).orElseThrow(()-> new NotFoundException("driver with id: "+driverId+" not found"));
        log.info("Class: DriverServiceImpl, method: viewDriver returned "+driver);
        return driver;
    }

    /**
     * @param userName
     * @return Driver
     */
    @Override
    public Driver viewDriverByUserName(String userName) {
        Driver driver= driverRepository.findByUserName(userName).orElseThrow(()-> new NotFoundException(" Driver not found"));
        return driver;
    }


}
