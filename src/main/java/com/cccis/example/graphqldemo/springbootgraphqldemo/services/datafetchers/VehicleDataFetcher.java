package com.cccis.example.graphqldemo.springbootgraphqldemo.services.datafetchers;

import com.cccis.example.graphqldemo.springbootgraphqldemo.models.Vehicle;
import com.cccis.example.graphqldemo.springbootgraphqldemo.services.VehicleService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class VehicleDataFetcher implements DataFetcher<Vehicle> {

    @Autowired
    VehicleService vehicleService;

    @Override
    public Vehicle get(DataFetchingEnvironment dataFetchingEnvironment) {
        String vin = dataFetchingEnvironment.getArgument("vin");
        return vehicleService.getOne(vin);
    }
}
