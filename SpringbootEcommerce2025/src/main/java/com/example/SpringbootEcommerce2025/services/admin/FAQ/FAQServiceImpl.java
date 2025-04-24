package com.example.SpringbootEcommerce2025.services.admin.FAQ;

import com.example.SpringbootEcommerce2025.dto.FAQDto;
import com.example.SpringbootEcommerce2025.entity.FAQ;
import com.example.SpringbootEcommerce2025.entity.Product;
import com.example.SpringbootEcommerce2025.repository.FAQRepository;
import com.example.SpringbootEcommerce2025.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FAQServiceImpl implements  FAQService {
    private final FAQRepository faqRepository;
    private final ProductRepository productRepository;

    public FAQServiceImpl(FAQRepository faqRepository, ProductRepository productRepository) {
        this.faqRepository = faqRepository;
        this.productRepository = productRepository;
    }

    public FAQDto postFAQ(Long productId, FAQDto faqDto){
        Optional<Product> optionalProduct=productRepository.findById(productId);
        if(optionalProduct.isPresent()){
            FAQ faq=new FAQ();
            faq.setQuestion(faqDto.getQuestion());
            faq.setAnswer(faqDto.getAnswer());
            faq.setProduct(optionalProduct.get());

            return faqRepository.save(faq).getFAQDto();
        }
        return null;
    }
}
