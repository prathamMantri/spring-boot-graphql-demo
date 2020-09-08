package com.cccis.example.graphqldemo.springbootgraphqldemo.services.datafetchers;

import com.cccis.example.graphqldemo.springbootgraphqldemo.models.Make;
import com.cccis.example.graphqldemo.springbootgraphqldemo.models.Model;
import com.cccis.example.graphqldemo.springbootgraphqldemo.services.ModelService;
import com.cccis.example.graphqldemo.springbootgraphqldemo.services.VehicleService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllModelsDataFetcher  implements DataFetcher<List<Model>> {

    @Autowired
    ModelService modelService;

    @Override
    public List<Model> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return modelService.findAll();
    }
}
