package com.cccis.example.graphqldemo.springbootgraphqldemo.services.datafetchers;

import com.cccis.example.graphqldemo.springbootgraphqldemo.models.Model;
import com.cccis.example.graphqldemo.springbootgraphqldemo.services.ModelService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ModelDataFetcher implements DataFetcher<Model> {

    @Autowired
    ModelService modelService;

    @Override
    public Model get(DataFetchingEnvironment dataFetchingEnvironment) {
        return modelService.getOne(dataFetchingEnvironment.getArgument("modelCode"));
    }
}
