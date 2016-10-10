package com.fdar.apress.s2.services;

import com.fdar.apress.s2.domain.Location;

import java.util.List;

public interface LocationService {

    public void create(Location loc);

    public List<Location> findAll();

    public Location findById( Long id );
}
