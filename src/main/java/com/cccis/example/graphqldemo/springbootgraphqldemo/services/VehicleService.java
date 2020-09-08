package com.cccis.example.graphqldemo.springbootgraphqldemo.services;

import com.cccis.example.graphqldemo.springbootgraphqldemo.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VehicleService extends JpaRepository<Vehicle, String> {

}
