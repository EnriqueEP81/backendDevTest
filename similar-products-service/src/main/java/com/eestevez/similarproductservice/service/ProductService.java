package com.eestevez.similarproductservice.service;

import com.eestevez.similarproductservice.model.ProductDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final RestTemplate restTemplate;

    @Value("${external.service.base-url}")
    private String baseUrl;

    public List<ProductDetail> findSimilarProducts(String productId) {
        String[] similarIds = restTemplate.getForObject(
                baseUrl + "/" + productId + "/similarids",
                String[].class);

        return Arrays.stream(similarIds)
                .map(this::findProductDetail)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
    // Slightly better performance returning ProductDetail(null) than Optional<ProductDetail>
    private ProductDetail findProductDetail(String productId){
        try {
            return restTemplate.getForObject(baseUrl + "/" + productId, ProductDetail.class);
        }
        catch(HttpClientErrorException.NotFound e) {
            log.warn("Product Id {} not found (404)",productId);
            return null;
        }
        catch (HttpServerErrorException e) {
            log.error("Server error for product ID {} (5xx): {}", productId, e.getMessage());
            return null;
        }
        catch (RestClientException e) {
            log.error("Unexpected error for product ID {}: {}", productId, e.getMessage());
            return null;
        }
    }

}
