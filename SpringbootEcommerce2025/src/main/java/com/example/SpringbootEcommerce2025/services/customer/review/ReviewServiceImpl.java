package com.example.SpringbootEcommerce2025.services.customer.review;

import com.example.SpringbootEcommerce2025.dto.OrderedProductResponseDto;
import com.example.SpringbootEcommerce2025.dto.ProductDto;
import com.example.SpringbootEcommerce2025.dto.ReviewDto;
import com.example.SpringbootEcommerce2025.entity.*;
import com.example.SpringbootEcommerce2025.exceptions.ValidationException;
import com.example.SpringbootEcommerce2025.repository.OrderRepository;
import com.example.SpringbootEcommerce2025.repository.ProductRepository;
import com.example.SpringbootEcommerce2025.repository.ReviewRepository;
import com.example.SpringbootEcommerce2025.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService{

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(OrderRepository orderRepository, ProductRepository productRepository, UserRepository userRepository, ReviewRepository reviewRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
    }

    public OrderedProductResponseDto getOrderedProductsDetailsByOrderId(Long orderId){
//        Optional<Order> optionalOrder= orderRepository.findById(orderId);
//
//        OrderedProductResponseDto orderedProductResponseDto= new OrderedProductResponseDto();
//        if(optionalOrder.isPresent()){
//            orderedProductResponseDto.setOrderAmount(optionalOrder.get().getAmount());
//
//            List<ProductDto> productDtoList= new ArrayList<>();
//            for(CartItems cartItems: optionalOrder.get().getCartItems()){
//                ProductDto productDto= new ProductDto();
//
//                productDto.setId(cartItems.getProduct().getId());
//                productDto.setName(cartItems.getProduct().getName());
//                productDto.setPrice(cartItems.getPrice());
//                productDto.setQuantity(cartItems.getQuantity());
//                productDto.setByteImg(cartItems.getProduct().getImg());
//
//                productDtoList.add(productDto);
//            }
//            orderedProductResponseDto.setProductDtoList(productDtoList);
//        }
//        return orderedProductResponseDto;
        Optional<Order> optionalOrder= orderRepository.findById(orderId);

        OrderedProductResponseDto orderedProductResponseDto= new OrderedProductResponseDto();
        if(optionalOrder.isPresent()){
            Order order = optionalOrder.get();
            order.getCartItems().size();//Force load cart items

            System.out.println("Order found: " + order.getId());
            System.out.println("Cart items count: " + (order.getCartItems() != null ? order.getCartItems().size() : "null"));

            orderedProductResponseDto.setOrderAmount(order.getTotalAmount());

            List<ProductDto> productDtoList= new ArrayList<>();
            if(order.getCartItems() != null){
                for(CartItems cartItems: order.getCartItems()){
                    if(cartItems != null && cartItems.getProduct() != null){
                        System.out.println("Cart Item Product: " + cartItems.getProduct().getName());
                        ProductDto productDto= new ProductDto();
                        productDto.setId(cartItems.getProduct().getId());
                        productDto.setName(cartItems.getProduct().getName());
                        productDto.setPrice(cartItems.getPrice());
                        productDto.setQuantity(cartItems.getQuantity());
                        productDto.setByteImg(cartItems.getProduct().getImg());
                        productDtoList.add(productDto);
                    }else{
                        System.out.println("Empty cartItem or product.");
                    }
                }
            }
            orderedProductResponseDto.setProductDtoList(productDtoList);
        }else{
            System.out.println("Order not found for ID: " + orderId);
        }
        return orderedProductResponseDto;

    }

    public boolean giveReview(ReviewDto reviewDto) throws IOException {
        Optional<Product> optionalProduct= Optional.ofNullable(productRepository.findById(reviewDto.getProductId()).orElseThrow(() -> new ValidationException("Invalid productId")));;
        Optional<User> optionalUser= Optional.ofNullable(userRepository.findById(reviewDto.getUserId()).orElseThrow(() -> new ValidationException("Invalid userId")));;

        if(optionalProduct.isPresent() && optionalUser.isPresent()){
            Review review= new Review();
            review.setRating(reviewDto.getRating());
            review.setDescription(reviewDto.getDescription());
            review.setUser(optionalUser.get());
            review.setProduct(optionalProduct.get());

            if (reviewDto.getImg() != null && !reviewDto.getImg().isEmpty()) {
                review.setImg(reviewDto.getImg().getBytes());
            }
            //review.setImg(reviewDto.getImg().getBytes());

            reviewRepository.save(review);
            return true;
        }

        return false;
    }
}
