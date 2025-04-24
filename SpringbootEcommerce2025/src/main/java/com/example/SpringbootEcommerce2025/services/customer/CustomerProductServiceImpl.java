package com.example.SpringbootEcommerce2025.services.customer;

import com.example.SpringbootEcommerce2025.dto.ProductDetailDto;
import com.example.SpringbootEcommerce2025.dto.ProductDto;
import com.example.SpringbootEcommerce2025.entity.FAQ;
import com.example.SpringbootEcommerce2025.entity.Product;
import com.example.SpringbootEcommerce2025.entity.Review;
import com.example.SpringbootEcommerce2025.repository.FAQRepository;
import com.example.SpringbootEcommerce2025.repository.ProductRepository;
import com.example.SpringbootEcommerce2025.repository.ReviewRepository;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class  CustomerProductServiceImpl implements CustomerProductService {

    private final ProductRepository productRepository;

    private final FAQRepository faqRepository;
    private final ReviewRepository reviewRepository;

    public CustomerProductServiceImpl(ProductRepository productRepository, FAQRepository faqRepository, ReviewRepository reviewRepository) {
        this.productRepository = productRepository;
        this.faqRepository = faqRepository;
        this.reviewRepository = reviewRepository;
    }

    public List<ProductDto> getAllProducts(){
        List<Product> products= productRepository.findAll();
        return products.stream().map(Product::getDto).collect(Collectors.toList());

    }

    public List<ProductDto> searchProductByTitle(String name){
        List<Product> products= productRepository.findAllByNameContaining(name);
        return products.stream().map(Product::getDto).collect(Collectors.toList());

    }

    public ProductDetailDto getProductDetailById(Long productId){
        Optional<Product> optionalProduct= productRepository.findById(productId);

        if(optionalProduct.isPresent()){
            Product product= optionalProduct.get();
            List<FAQ> faqList=faqRepository.findAllByProductId(productId);
            List<Review> reviewList=reviewRepository.findAllByProductId(productId);

            ProductDetailDto productDetailDto = new ProductDetailDto();
            productDetailDto.setProductDto(product.getDto());
            productDetailDto.setFaqDtoList(faqList.stream().map(FAQ::getFAQDto).collect(Collectors.toList()));

            productDetailDto.setReviewDtoList(reviewList.stream().map(Review:: getDto).collect(Collectors.toList()));

            return productDetailDto;
        }
        return null;
    }
}
