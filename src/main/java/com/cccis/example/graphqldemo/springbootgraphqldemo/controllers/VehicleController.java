package com.cccis.example.graphqldemo.springbootgraphqldemo.controllers;

import com.cccis.example.graphqldemo.springbootgraphqldemo.services.GraphQLVehicleService;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/vehicles")
@RestController
public class VehicleController {

    @Autowired
    GraphQLVehicleService graphQLVehicleService;

    @PostMapping
    public ResponseEntity<Object> getVehicleDetails(@RequestBody String query){
        ExecutionResult result = graphQLVehicleService.getGraphQL().execute(query);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
