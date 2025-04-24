package com.example.SpringbootEcommerce2025.dto;

import java.util.List;

public class ProductDetailDto {
    private ProductDto productDto;
    private List<ReviewDto> reviewDtoList;
    private List<FAQDto> faqDtoList;

    public ProductDetailDto() {
    }

    public ProductDetailDto(ProductDto productDto, List<ReviewDto> reviewDtoList, List<FAQDto> faqDtoList) {
        this.productDto = productDto;
        this.reviewDtoList = reviewDtoList;
        this.faqDtoList = faqDtoList;
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
    }

    public List<ReviewDto> getReviewDtoList() {
        return reviewDtoList;
    }

    public void setReviewDtoList(List<ReviewDto> reviewDtoList) {
        this.reviewDtoList = reviewDtoList;
    }

    public List<FAQDto> getFaqDtoList() {
        return faqDtoList;
    }

    public void setFaqDtoList(List<FAQDto> faqDtoList) {
        this.faqDtoList = faqDtoList;
    }
}
