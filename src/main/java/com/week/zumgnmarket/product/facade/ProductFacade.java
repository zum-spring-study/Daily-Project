package com.week.zumgnmarket.product.facade;

import com.week.zumgnmarket.product.dto.ProductRequest;
import com.week.zumgnmarket.product.dto.ProductResponse;
import com.week.zumgnmarket.product.service.ProductService;
import com.week.zumgnmarket.product.service.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductFacade {

    private final ProductService productService;

    public List<ProductResponse> getAllProducts(String location, Pageable pageable) {
        List<ProductDTO> allProducts = productService.getAllProducts(location, pageable);

        return allProducts.stream().map(ProductResponse::buildToResponse).collect(Collectors.toList());

    }

    public ProductResponse addProduct(ProductRequest productRequest) {
        ProductDTO product = productService.createProduct(productRequest);

        return ProductResponse.buildToResponse(product);
    }

    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        ProductDTO productDTO = productService.editProduct(id, productRequest);

        return ProductResponse.buildToResponse(productDTO);
    }

    public ProductResponse patchProduct(Long id) {
        return null;
    }
}
