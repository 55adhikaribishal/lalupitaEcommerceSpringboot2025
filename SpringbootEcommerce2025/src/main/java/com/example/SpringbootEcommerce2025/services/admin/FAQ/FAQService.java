package com.example.SpringbootEcommerce2025.services.admin.FAQ;

import com.example.SpringbootEcommerce2025.dto.FAQDto;

public interface FAQService {
    FAQDto postFAQ(Long productId, FAQDto faqDto);
}
