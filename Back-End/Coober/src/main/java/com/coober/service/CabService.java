package com.coober.service;

import java.util.List;

import com.coober.modal.Cab;

public interface CabService {

    public Cab insertCab(Cab cab);

    public Cab updateCab(Cab cab);

    public Cab deleteCab(Integer cabId);

    public List<Cab> viewCabsOfType(String carType);

    public Integer countCabsOfType(String carType);
}
