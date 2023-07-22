package com.coober.service;

import com.coober.Exception.cooberException;
import com.coober.modal.TripBooking;

import java.util.List;

public interface TripBookingService {
    public TripBooking insertTripBooking(TripBooking tripBooking,Integer customerId) throws cooberException;

    public TripBooking updateTripBooking(TripBooking tripBooking) throws cooberException;
    public TripBooking deleteTripBooking(Integer tripBookingId) throws cooberException;
    public List<TripBooking> viewAllTripsCustomer(Integer customerld) throws cooberException;

    public float calculateBill(Integer customerId) throws cooberException;

    public List<TripBooking> getAllTripBooking(Integer customerid) throws cooberException;



}
