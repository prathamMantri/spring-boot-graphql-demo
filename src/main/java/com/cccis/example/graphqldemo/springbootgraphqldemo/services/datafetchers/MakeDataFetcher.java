package com.cccis.example.graphqldemo.springbootgraphqldemo.services.datafetchers;

import com.cccis.example.graphqldemo.springbootgraphqldemo.models.Make;
import com.cccis.example.graphqldemo.springbootgraphqldemo.services.MakeService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MakeDataFetcher implements DataFetcher<Make> {

    @Autowired
    MakeService makeService;

    @Override
    public Make get(DataFetchingEnvironment dataFetchingEnvironment) {
        return makeService.getOne(dataFetchingEnvironment.getArgument("makeCode"));
    }
}
