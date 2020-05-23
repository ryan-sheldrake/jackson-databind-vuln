package demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import spark.Spark;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class MyServer {

    private static final List<Product> database = new CopyOnWriteArrayList<>();

    private static final ObjectMapper deserializer = new ObjectMapper().enableDefaultTyping();
    private static final ObjectMapper serializer = new ObjectMapper();

    public static void main(String[] args) {

        //Adding some basic data
        database.add(new Product("car", randomData()));
        database.add(new Product("boat", randomData()));
        database.add(new Product("plane", randomData()));

        //Configure HTTP server
        Spark.port(8888);

        //Define endpoints
        Spark.get("/products", (request, response) -> {
            System.out.println("products get!");
            return serializer.writeValueAsString(database);
        });

        Spark.post("/products", (request, response) -> {
            try {
                System.out.println("products post!");
                final Product product = deserializer.readValue(request.body(), Product.class);
                Optional.ofNullable(product).ifPresent(p -> database.add(p));
                return "THANKS";
            } catch (final Exception e) {
                return "ERROR";
            }
        });
    }

    private static Map<String, String> randomData() {
        final String[] colors = {"yellow", "red", "green"};
        final Map<String, String> data = new HashMap<>();
        data.put("cost", new Random().nextInt(100_000) + "");
        data.put("color", colors[new Random().nextInt(colors.length)]);
        return data;
    }
}