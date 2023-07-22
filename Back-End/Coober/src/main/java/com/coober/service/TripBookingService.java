package com.coober.service;

import java.util.List;

import com.coober.Exception.CooberException;
import com.coober.modal.TripBooking;

public interface TripBookingService {
    public TripBooking insertTripBooking(TripBooking tripBooking,Integer customerId) throws CooberException;

    public TripBooking updateTripBooking(TripBooking tripBooking) throws CooberException;
    public TripBooking deleteTripBooking(Integer tripBookingId) throws CooberException;
    public List<TripBooking> viewAllTripsCustomer(Integer customerld) throws CooberException;

    public float calculateBill(Integer customerId) throws CooberException;

    public List<TripBooking> getAllTripBooking(Integer customerid) throws CooberException;
=======
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
