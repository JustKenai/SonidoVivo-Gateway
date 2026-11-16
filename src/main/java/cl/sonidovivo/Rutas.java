package cl.sonidovivo;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import java.util.Arrays;

@Configuration
public class Rutas {

    @Bean
    public RouteLocator configurarRutas(RouteLocatorBuilder builder) {
        return builder.routes()
                // 1. Categoria y Auth
                .route("categoria-service", r -> r.path("/api/categoria/**", "/api/categoria", "/api/auth/**", "/api/auth")
                        .filters(f -> f.preserveHostHeader())
                        .uri("lb://CATEGORIA"))

                // 2. Producto
                .route("producto-service", r -> r.path("/api/producto/**", "/api/producto")
                        .filters(f -> f.preserveHostHeader())
                        .uri("lb://PRODUCTO"))

                // 3. Carrito
                .route("carrito-service", r -> r.path("/api/carrito/**", "/api/carrito")
                        .filters(f -> f.preserveHostHeader())
                        .uri("lb://CARRITO"))

                .build();
    }

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOriginPatterns(Arrays.asList("*"));
        corsConfig.setAllowedMethods(Arrays.asList("*"));
        corsConfig.setAllowedHeaders(Arrays.asList("*"));
        corsConfig.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }
}