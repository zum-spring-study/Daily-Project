package com.week.zumgnmarket.service.product;

import com.week.zumgnmarket.endpoint.product.dto.ProductRequest;
import com.week.zumgnmarket.entity.Member;
import com.week.zumgnmarket.entity.Product;
import com.week.zumgnmarket.entity.Town;
import com.week.zumgnmarket.repository.member.MemberRepository;
import com.week.zumgnmarket.repository.product.ProductRepository;
import com.week.zumgnmarket.repository.town.TownRepository;
import com.week.zumgnmarket.repository.product.ProductDynamicRepository;
import com.week.zumgnmarket.service.product.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final MemberRepository memberRepository;

    private final ProductRepository productRepository;

    private final ProductDynamicRepository productDynamicRepository;

    private final TownRepository townRepository;

    @Transactional(readOnly = true)
    public List<ProductDTO> getAllProducts(String location, Pageable pageable) {

        List<Product> products = productDynamicRepository.getAllProducts(location, pageable);

       return  products.stream().map(product -> {
            Town town = townRepository.findByProductId(product.getId());
            return ProductDTO.buildToDTO(ProductDTO.Product.of(product, town.getCityName()));
              }).collect(Collectors.toList());


    }

    @Transactional(readOnly = true)
    public ProductDTO getProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));

        Town town = townRepository.findByProductId(product.getId());

        return ProductDTO.buildToDTO(ProductDTO.Product.of(product, town.getCityName()));
    }

    @Transactional
    public ProductDTO createProduct(ProductRequest productRequest) {
        Member member = memberRepository.findByName(productRequest.getMemberName())
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));

        Product product = productRepository.save(productRequest.toEntity(member, productRequest));

        Town town = new Town(productRequest.getTown(), product);

        townRepository.save(town);

        return ProductDTO.buildToDTO(ProductDTO.Product.of(product, town.getCityName()));
    }

    @Transactional
    public ProductDTO editProduct(Long id, ProductRequest productRequest) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));

        product.update(productRequest.getProductName(), productRequest.getDescription(), productRequest.getPrice(), productRequest.getTradeStatus());
        Town town = townRepository.findByProductId(product.getId());

        productRepository.save(product);

        return ProductDTO.buildToDTO(ProductDTO.Product.of(product, town.getCityName()));
    }

}
