package com.week.zumgnmarket.product.controller;

import com.week.zumgnmarket.product.dto.ProductRequest;
import com.week.zumgnmarket.product.dto.ProductResponse;
import com.week.zumgnmarket.product.facade.ProductFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "Product", description = "상품 관련 API")
public class ProductController {

    private final ProductFacade productFacade;

    /*
    * 상품 목록 조회
    * @param location
    * ALL = 전체, Else = LocationNames enum 참고
    * return ProductResponse
     */
    @GetMapping("/products")
    @Tag(name = "getAllProducts", description = "상품 목록 조회 API")
    public List<ProductResponse> getAllProducts(@RequestParam("location") String location, Pageable pageable) {

        return productFacade.getAllProducts(location, pageable);
    }

    /*
     * 상품 등록
     * @RequestBody ProductRequest
     * return ProductResponse
     */
    @PostMapping("/product/add")
    @Tag(name = "addProduct", description = "상품 등록 API")
    public ProductResponse addProduct(@RequestBody ProductRequest productRequest) {
        return productFacade.addProduct(productRequest);
    }

    /*
     * 상품 수정
     * @path id = 상품 id
     * @RequestBody ProductRequest
     * return ProductResponse
     */
    @PatchMapping("/product/{id}")
    @Tag(name = "updateProduct", description = "상품 수정 API")
    public ProductResponse updateProduct(@PathVariable("id") Long id,
                                         @RequestBody ProductRequest productRequest) {

        return productFacade.updateProduct(id, productRequest);
    }

    /*
     * 상품 삭제 (상태 변경)
     * @Path id = 상품 id
     * false = 삭제, true = 복구
     * @return ProductResponse
     */
    @PatchMapping("/product/unable/{id}")
    @Tag(name = "removeProduct", description = "상품 삭제 API")
    public ProductResponse removeProduct(@PathVariable("id") Long id) {
        return productFacade.patchProduct(id);
    }
}
