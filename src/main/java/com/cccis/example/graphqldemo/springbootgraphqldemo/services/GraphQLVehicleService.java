package com.cccis.example.graphqldemo.springbootgraphqldemo.services;

import com.cccis.example.graphqldemo.springbootgraphqldemo.models.Make;
import com.cccis.example.graphqldemo.springbootgraphqldemo.models.Model;
import com.cccis.example.graphqldemo.springbootgraphqldemo.models.Vehicle;
import com.cccis.example.graphqldemo.springbootgraphqldemo.services.datafetchers.*;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@Service
public class GraphQLVehicleService {

    @Value("classpath:vehicle.graphql")
    Resource resource;

    private GraphQL graphQL;

    @Autowired
    private AllVehiclesDataFetcher allVehiclesDataFetcher;

    @Autowired
    private VehicleDataFetcher vehiclesDataFetcher;

    @Autowired
    private AllMakeDataFetcher allMakesDataFetcher;

    @Autowired
    private MakeDataFetcher makeDataFetcher;

    @Autowired
    private AllModelsDataFetcher allModelsDataFetcher;

    @Autowired
    private ModelDataFetcher modelDataFetcher;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private MakeService makeService;

    @Autowired
    private ModelService modelService;
    //load schema at application start up

    @PostConstruct
    public void loadSchema() throws IOException {

        loadDataIntoHSQL();

        //get the schema
        File schemaFile = resource.getFile();

        //parse the schema
        //Register the schema file
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schemaFile);

        //build the wiring to connect
        RuntimeWiring wiring = buildRuntimeWiring();

        //Generate the schema using registered file and wiring which is nothing but reference.
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, wiring);

        //build the graphQL
        graphQL = GraphQL.newGraphQL(schema).build();

        List<Vehicle> list = vehicleService.findAll();

        for (Vehicle vehicle : list) {
            System.out.println(vehicle.getMake().toString());
        }

    }

    private void loadDataIntoHSQL() {

        Stream.of(
                new Make("TOYO", "TOYOTA"),
                new Make("AUDI", "AUDI"),
                new Make("HOND", "HONDA")
        )
                .forEach(make -> {
                    makeService.save(make);
                });


        Stream.of(
                new Model("CAM", "Camry", "TOYO"),
                new Model("X5", "X5", "AUDI"),
                new Model("ODY", "ODYSSE", "HOND"),
                new Model("4RUN", "FORRUNNER", "HOND")
        )
                .forEach(model -> {
                    modelService.save(model);
                });

        Stream.of(
                new Vehicle("JF2SJAHCXFH537549", new Make("TOYO", "TOYOTA"), new Model("CAM", "Camry", "TOYO"), 1998),
                new Vehicle("JF2SJAHCXFH537559", new Make("AUDI", "AUDI"), new Model("X5", "X5", "AUDI"), 1993),
                new Vehicle("JF2SJAHCXFH537569", new Make("HOND", "HONDA"), new Model("ODY", "ODYSSE", "HOND"), 2006),
                new Vehicle("JF2SJAHCXFH5375F9", new Make("HOND", "HONDA"), new Model("4RUN", "FORRUNNER", "HOND"), 2020)
        )
                .forEach(vehicle -> {
                    vehicleService.save(vehicle);
                });
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allVehicles", allVehiclesDataFetcher)
                        .dataFetcher("vehicle", vehiclesDataFetcher)
                        .dataFetcher("allMakes", allMakesDataFetcher)
                        .dataFetcher("make", makeDataFetcher)
                        .dataFetcher("allModels", allModelsDataFetcher)
                        .dataFetcher("model", modelDataFetcher))
                .build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }
}
