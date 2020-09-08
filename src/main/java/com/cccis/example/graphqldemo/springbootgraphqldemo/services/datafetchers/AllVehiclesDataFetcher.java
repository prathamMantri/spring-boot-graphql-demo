package com.cccis.example.graphqldemo.springbootgraphqldemo.services.datafetchers;

import com.cccis.example.graphqldemo.springbootgraphqldemo.models.Vehicle;
import com.cccis.example.graphqldemo.springbootgraphqldemo.services.VehicleService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllVehiclesDataFetcher implements DataFetcher<List<Vehicle>> {

    @Autowired
    VehicleService vehicleService;

    @Override
    public List<Vehicle> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return vehicleService.findAll();
    }
}
