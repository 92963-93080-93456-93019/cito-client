package ua.tqs.client.utils;

public class Environment {
    private static final String CITO_ENGINE = "http://localhost:8081";
    private static final String CITO_ENGINE_CLIENT_API = "/clientApi";

    public static final String CITO_REGISTER_ORDER_API_URL = CITO_ENGINE + CITO_ENGINE_CLIENT_API + "/{clientId}/orders?appid={apiKey}";
    public static final String CITO_PRODUCTS_API_URL = CITO_ENGINE + CITO_ENGINE_CLIENT_API + "/{clientId}/products?appid={apiKey}&query={query}";

}
