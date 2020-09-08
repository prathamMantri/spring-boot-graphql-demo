package com.cccis.example.graphqldemo.springbootgraphqldemo.services.datafetchers;

import com.cccis.example.graphqldemo.springbootgraphqldemo.models.Make;
import com.cccis.example.graphqldemo.springbootgraphqldemo.services.MakeService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllMakeDataFetcher implements DataFetcher<List<Make>> {

    @Autowired
    MakeService makeService;

    @Override
    public List<Make> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return makeService.findAll();
    }

}
