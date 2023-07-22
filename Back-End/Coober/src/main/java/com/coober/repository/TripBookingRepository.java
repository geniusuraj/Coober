package com.coober.repository;

import com.coober.modal.Customer;
import com.coober.modal.TripBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripBookingRepository extends JpaRepository<TripBooking, Integer> {

    @Query("SELECT tb FROM TripBooking tb")
    public List<TripBooking> findAllTripBooking();
//    public List<TripBooking> findAllByCustomerId(int customerId);

    List<TripBooking> findAllByCustomer(Customer customer);
}
