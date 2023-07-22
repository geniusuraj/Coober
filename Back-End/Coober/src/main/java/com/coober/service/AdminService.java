package com.coober.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.coober.modal.Admin;
import com.coober.modal.TripBooking;
import com.rideeasy.exception.DataNotFoundException;
import com.rideeasy.exception.InvalidInputException;

public interface AdminService {
	
	public Admin insertAdmin(Admin admin) throws InvalidInputException;
	public Admin updateAdmin(Integer adminId, Admin admin) throws InvalidInputException;
	public Admin deleteAdmin(Integer adminId) throws NotFoundException;
	public List<TripBooking> getAllTripsOfCustomer(Integer customerId) throws NotFoundException;
	public List<TripBooking> getTripsCabWise(Integer desiredCabId) throws DataNotFoundException;
	public List<TripBooking> getTripsCustomerWise(Integer customerId) throws DataNotFoundException;
	public List<TripBooking> getTripsDateWise(LocalDateTime dateTime) throws DataNotFoundException;
	public List<TripBooking> getAllTripsForDays(Integer customerId, LocalDateTime fromDate, LocalDateTime toDate) throws DataNotFoundException;

}
	