package com.example.SpringbootEcommerce2025.services.customer.payment;

import com.example.SpringbootEcommerce2025.dto.PaymentRequest;

public interface PaymentService {
    String createPaymentLink(PaymentRequest paymentRequestDto) throws Exception;
}
