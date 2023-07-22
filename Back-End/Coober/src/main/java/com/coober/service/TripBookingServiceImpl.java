package com.coober.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coober.Exception.CooberException;
import com.coober.modal.Customer;
import com.coober.modal.Driver;
import com.coober.modal.TripBooking;
import com.coober.repository.CustomerRepository;
import com.coober.repository.DriverRepository;
import com.coober.repository.TripBookingRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TripBookingServiceImpl implements TripBookingService {

    private static final Logger logger = LoggerFactory.getLogger(TripBookingServiceImpl.class);
    @Autowired
    private TripBookingRepository tripBookingRepository;

    @Autowired
    private DriverRepository DriverRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public TripBooking insertTripBooking(TripBooking tripBooking ,Integer customerId) throws CooberException {
        logger.info("Inserting trip booking: {}", tripBooking);
        try{
            Optional<Customer> customer = customerRepository.findById(customerId);

            if (customer.isPresent()) {
                Customer cust = customer.get();
                TripBooking tripB = new TripBooking();


                tripB.setFromLocation(tripBooking.getFromLocation());
                tripB.setToLocation(tripBooking.getToLocation());
                tripB.setFromDateTime(tripBooking.getFromDateTime());
                tripB.setToDateTime(tripBooking.getToDateTime());
                int min = 10;
                int max = 100;
                float distance = (float) Math.floor(Math.random() * (max - min + 1) + min);
                tripB.setDistanceInKm(distance);

                Float billAmount=  calculateBill(customerId);
                tripB.setBill(billAmount);
                tripB.setCustomer(cust);


            List<Driver> driverlist = DriverRepository.findAll();
            Driver driver = null;
            for(int i = 0;i<driverlist.size();i++) {
                if(driverlist.get(i).getIsAvailable()) {
                    driver = driverlist.get(i);
                    break;
                }
            }

            if( driver == null ) throw new CooberException("No Driver Available at the moment");

            tripB.setDriver(driver);

            driver.getTripBookings().add(tripB);
            driver.setIsAvailable(false);


                cust.getTripBookings().add(tripB);

                tripBookingRepository.save(tripB);


                return tripB;

            } else {
                throw new CooberException("Customer not found with id " + tripBooking.getCustomer().getCustomerId());
            }
        }
        catch (Exception e) {
            logger.error("Error occurred while inserting trip booking", e);
            throw new CooberException("Failed to insert trip booking");
        }
    }

    @Override
    public TripBooking updateTripBooking(TripBooking tripBooking) throws CooberException {
        logger.info("Updating trip booking: {}", tripBooking);

        try{
            Optional<TripBooking> opt = tripBookingRepository.findById(tripBooking.getTripBookingId());

            if (opt.isPresent()) {
                TripBooking updatedTrip = tripBookingRepository.save(tripBooking);
                return updatedTrip;
            } else {
                throw new CooberException("User not found!");
            }
        }
        catch (Exception e) {
            logger.error("Error occurred while updating trip booking", e);
            throw new CooberException("Failed to update trip booking");
        }
    }

    @Override
    public TripBooking deleteTripBooking(Integer tripBookingId) throws CooberException {
        logger.info("Deleting trip booking with ID: {}", tripBookingId);

        try{
            TripBooking tripBooking = tripBookingRepository.findById(tripBookingId)
                    .orElseThrow(() -> new CooberException("Trip booking not found with ID: " + tripBookingId));

            tripBookingRepository.delete(tripBooking);
            return tripBooking;
        }
        catch (Exception e) {
            logger.error("Error occurred while deleting trip booking", e);
            throw new CooberException("Failed to delete trip booking");
        }
    }

    @Override
    public List<TripBooking> viewAllTripsCustomer(Integer customerId) throws CooberException {
        logger.info("Retrieving all trip bookings for customer with ID: {}", customerId);
        try{
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new CooberException("Customer not found with ID: " + customerId));

            return tripBookingRepository.findAllByCustomer(customer);
        }
        catch (Exception e) {
            logger.error("Error occurred while retrieving trip bookings for customer with ID: {}", customerId, e);
            throw new CooberException("Failed to retrieve trip bookings for customer");
        }
    }

    @Override
    public float calculateBill(Integer customerId) throws CooberException {
        logger.info("Calculating bill for customer with ID: {}", customerId);

        try{
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new CooberException("Customer not found with ID: " + customerId));

            List<TripBooking> tripBookings = tripBookingRepository.findAllByCustomer(customer);

            tripBookings.sort(Comparator.comparing(TripBooking::getFromDateTime).reversed());

            if (!tripBookings.isEmpty()) {
                TripBooking lastTripBooking = tripBookings.get(0);

                if (lastTripBooking.getStatus() != null && lastTripBooking.getStatus()) {
                    Float distance = lastTripBooking.getDistanceInKm();
                    Float perKmRate = lastTripBooking.getDriver().getCab().getPerKmRate();
                    double tripBill = distance * perKmRate;
                    logger.info("Bill calculated successfully for the last trip of customer with ID {}: {}", customerId, tripBill);

                    return (float) tripBill;
                }
            }
            logger.warn("No completed trip found for customer with ID: {}", customerId);
            return 0.0F;
        }
        catch (Exception e) {
            logger.error("Error occurred while calculating bill for customer with ID: {}", customerId, e);
            throw new CooberException("Failed to calculate bill for customer");
        }
    }


    @Override
    public List<TripBooking> getAllTripBooking(Integer customerid) throws CooberException {
        Optional<Customer> opt = customerRepository.findById(customerid);
        if (opt.isPresent()) {
            List<TripBooking> trips = opt.get().getTripBookings();
            return trips;
        }
        throw new CooberException("Invalid id");
    }
}
