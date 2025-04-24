package com.example.SpringbootEcommerce2025.services.customer.payment;

import com.stripe.Stripe;
import com.example.SpringbootEcommerce2025.dto.PaymentRequest;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Value("${stripe.api.key}")
    private String stripeApiKey;


    public String createPaymentLink(PaymentRequest paymentRequestDto) throws Exception {
        Stripe.apiKey = stripeApiKey;
//        if(paymentRequestDto.getCustomerEmail()==null || paymentRequestDto.getCustomerEmail().isEmpty()){
//            throw new RuntimeException("Customer email is required for payment");
//        }
        if(paymentRequestDto.getOrderId()==null && paymentRequestDto.getAmount()==null){
            throw new RuntimeException("Amount and order Id is required for payment");
        }
        System.out.println("Payment api is called");
        System.out.println(paymentRequestDto.toString());
        SessionCreateParams.Builder paramsBuilder = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                //while deploying using docker this needs to be changed to localhost 800 as it won't be running on 4200 anymore
               // .setSuccessUrl("http://localhost:4200/customer/payment-success?orderId="+paymentRequestDto.getOrderId())
                .setCancelUrl("http://localhost:4200/customer/payment-failure?orderId="+paymentRequestDto.getOrderId())
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                //.setCustomerEmail(paymentRequestDto.getCustomerEmail())
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setQuantity(1L)
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency("usd")
                                                .setUnitAmount(paymentRequestDto.getAmount()) // Amount must be in cents
                                                .setProductData(
                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                .setName(paymentRequestDto.getProductName())
                                                                .build()
                                                )
                                                .build()
                                )
                                .build()
                );

        if (paymentRequestDto.getCustomerEmail() != null && !paymentRequestDto.getCustomerEmail().isBlank()) {
            paramsBuilder.setCustomerEmail(paymentRequestDto.getCustomerEmail());
        }
        SessionCreateParams params = paramsBuilder.build();
        Session session=Session.create(params);

        return session.getUrl(); // Return Stripe Checkout URL
    }

}
