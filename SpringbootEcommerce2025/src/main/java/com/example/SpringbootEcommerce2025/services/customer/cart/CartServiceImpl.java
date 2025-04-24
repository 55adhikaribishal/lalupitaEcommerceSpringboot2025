package com.example.SpringbootEcommerce2025.services.customer.cart;

import com.example.SpringbootEcommerce2025.dto.AddProductInCartDto;
import com.example.SpringbootEcommerce2025.dto.CartItemsDto;
import com.example.SpringbootEcommerce2025.dto.OrderDto;
import com.example.SpringbootEcommerce2025.dto.PlaceOrderDto;
import com.example.SpringbootEcommerce2025.entity.*;
import com.example.SpringbootEcommerce2025.enums.OrderStatus;
import com.example.SpringbootEcommerce2025.exceptions.ValidationException;
import com.example.SpringbootEcommerce2025.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartItemsRepository cartItemsRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CouponRepository couponRepository;

    public ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto){
//        Order activeOrder=orderRepository.findByUserIdAndOrderStatus(addProductInCartDto.getUserId(), OrderStatus.PENDING);
//        Optional<CartItems> optionalCartItems=cartItemsRepository.findByProductIdAndOrderIdAndUserId
//                (addProductInCartDto.getProductId(),activeOrder.getId(), addProductInCartDto.getUserId());
//
//        if(optionalCartItems.isPresent()){
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
//        }else{
//            Optional<Product> optionalProduct=productRepository.findById(addProductInCartDto.getProductId());
//            Optional<User> optionalUser=userRepository.findById(addProductInCartDto.getUserId());
//
//            if(optionalProduct.isPresent() && optionalUser.isPresent()){
//                CartItems cart= new CartItems();
//                cart.setProduct(optionalProduct.get());
//                cart.setPrice(optionalProduct.get().getPrice());
//                cart.setQuantity(1L);
//                cart.setUser(optionalUser.get());
//                cart.setOrder(activeOrder);
//
//                CartItems updatedCart=cartItemsRepository.save(cart);
//
//                activeOrder.setTotalAmount(activeOrder.getTotalAmount()+cart.getPrice());
//                activeOrder.setAmount(activeOrder.getAmount()+cart.getPrice());
//                activeOrder.getCartItems().add(cart);
//
//                orderRepository.save(activeOrder);
//
//                return ResponseEntity.status(HttpStatus.CREATED).body(cart);
//
//            } else{
//                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or product not found");
//            }
//        }

        ///online code comented above method
        Optional<User> optionalUser = userRepository.findById(addProductInCartDto.getUserId());
        Optional<Product> optionalProduct = productRepository.findById(addProductInCartDto.getProductId());

        if (optionalUser.isEmpty() || optionalProduct.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or product not found");
        }
        if (addProductInCartDto.getProductId() == null || addProductInCartDto.getUserId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing productId or userId");
        }
        User user = optionalUser.get();
        Product product = optionalProduct.get();

        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(user.getId(), OrderStatus.PENDING);

        if (activeOrder == null) {
            activeOrder = new Order();
            activeOrder.setUser(user);
            activeOrder.setOrderStatus(OrderStatus.PENDING);
            activeOrder.setAmount(BigDecimal.ZERO);
            activeOrder.setTotalAmount(BigDecimal.ZERO);
            activeOrder = orderRepository.save(activeOrder);
        }

        Optional<CartItems> existingItem = cartItemsRepository.findByProductIdAndOrderIdAndUserId(
                product.getId(), activeOrder.getId(), user.getId()
        );

        if (existingItem.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Product already in cart");
        }

        CartItems cart = new CartItems();
        cart.setProduct(product);
        cart.setUser(user);
        cart.setOrder(activeOrder);
        cart.setPrice(product.getPrice());
        cart.setQuantity(1L);

        cartItemsRepository.save(cart);

        // update order totals
        activeOrder.setAmount(activeOrder.getAmount().add(cart.getPrice()));
        activeOrder.setTotalAmount(activeOrder.getTotalAmount().add(cart.getPrice()));
        orderRepository.save(activeOrder);

        return ResponseEntity.status(HttpStatus.CREATED).body(cart);
    }

    public OrderDto getCartByUserId(Long userId){

        Order activeOrder=orderRepository.findByUserIdAndOrderStatus(userId, OrderStatus.PENDING);

        //***added for testing
        if (activeOrder == null) {
            System.out.println("No active order found for user: " + userId);
            return null;
        }
        System.out.println("Found active order: " + activeOrder.getId());

        if (activeOrder.getCartItems() != null) {
            System.out.println("Cart Items count: " + activeOrder.getCartItems().size());
        } else {
            System.out.println("Cart Items is null");
        }
        //*********
        List<CartItemsDto> cartItemsDtoList= activeOrder.getCartItems()
                .stream().map(CartItems::getCartDto)
                .collect((Collectors.toList()));
        OrderDto orderDto=new OrderDto();
        orderDto.setAmount(activeOrder.getAmount());
        orderDto.setId(activeOrder.getId());
        orderDto.setOrderStatus(activeOrder.getOrderStatus());
        orderDto.setDiscount(activeOrder.getDiscount());
        orderDto.setTotalAmount(activeOrder.getTotalAmount());
        orderDto.setCartItems(cartItemsDtoList);
        if(activeOrder.getCoupon() !=null){
            orderDto.setCouponName(activeOrder.getCoupon().getName());
        }

        return orderDto;

    }

    public OrderDto applyCoupon(Long userId, String code){
        System.out.println("Coupon is applied here");
        Order activeOrder=orderRepository.findByUserIdAndOrderStatus(userId, OrderStatus.PENDING);
        Coupon coupon=couponRepository.findByCode(code).orElseThrow(()-> new ValidationException("Coupon not Found"));
        if(couponIsExpired(coupon)){
            throw new ValidationException("Coupon has expired.");
        }
        BigDecimal discountAmount=((coupon.getDiscount().divide(BigDecimal.valueOf(100.0)).multiply(activeOrder.getTotalAmount())));
        BigDecimal netAmount=activeOrder.getTotalAmount().subtract(discountAmount);

        System.out.println("Coupon is applied"+ discountAmount);
        activeOrder.setAmount(netAmount);
        activeOrder.setDiscount(discountAmount);
        activeOrder.setCoupon(coupon);

        orderRepository.save(activeOrder);
        return activeOrder.getOrderDto();
    }

    private boolean couponIsExpired(Coupon coupon) {
        Date currentDate= new Date();
        Date expirationDate=coupon.getExpirationDate();

        return expirationDate !=null && currentDate.after(expirationDate);
    }

    public OrderDto increaseProductQuantity(AddProductInCartDto addProductInCartDto){
        Order activeOrder=orderRepository.findByUserIdAndOrderStatus(addProductInCartDto.getUserId(), OrderStatus.PENDING);
        Optional<Product> optionalProduct=productRepository.findById(addProductInCartDto.getProductId());

        Optional<CartItems> optionalCartItems=cartItemsRepository.findByProductIdAndOrderIdAndUserId(
                addProductInCartDto.getProductId(),activeOrder.getId(),addProductInCartDto.getUserId());

        if(optionalProduct.isPresent() && optionalCartItems.isPresent()){
            CartItems cartItem=optionalCartItems.get();
            Product product=optionalProduct.get();

            activeOrder.setAmount(activeOrder.getAmount().add(product.getPrice()));
            activeOrder.setTotalAmount(activeOrder.getTotalAmount().add(product.getPrice()));

            cartItem.setQuantity(cartItem.getQuantity()+1);

            if(activeOrder.getCoupon() !=null){
                BigDecimal discountAmount=((activeOrder.getCoupon().getDiscount().divide(BigDecimal.valueOf(100.0))).multiply(activeOrder.getTotalAmount()));
                BigDecimal netAmount=activeOrder.getTotalAmount().subtract(discountAmount);


                activeOrder.setAmount((BigDecimal) netAmount);
                activeOrder.setDiscount((BigDecimal)discountAmount);
            }

            cartItemsRepository.save(cartItem);
            orderRepository.save(activeOrder);
            return activeOrder.getOrderDto();
        }
        return null;
    }

    public OrderDto decreaseProductQuantity(AddProductInCartDto addProductInCartDto){
        Order activeOrder=orderRepository.findByUserIdAndOrderStatus(addProductInCartDto.getUserId(), OrderStatus.PENDING);
        Optional<Product> optionalProduct=productRepository.findById(addProductInCartDto.getProductId());

        Optional<CartItems> optionalCartItems=cartItemsRepository.findByProductIdAndOrderIdAndUserId(
                addProductInCartDto.getProductId(),activeOrder.getId(),addProductInCartDto.getUserId());

        if(optionalProduct.isPresent() && optionalCartItems.isPresent()){
            CartItems cartItem=optionalCartItems.get();
            Product product=optionalProduct.get();

            activeOrder.setAmount(activeOrder.getAmount().subtract(product.getPrice()));
            activeOrder.setTotalAmount(activeOrder.getTotalAmount().subtract(product.getPrice()));

            cartItem.setQuantity(cartItem.getQuantity()-1);

            if(activeOrder.getCoupon() !=null){
                BigDecimal discountAmount=((activeOrder.getCoupon().getDiscount().divide(BigDecimal.valueOf(100.0))).multiply(activeOrder.getTotalAmount()));
                BigDecimal netAmount=activeOrder.getTotalAmount().subtract(discountAmount);


                activeOrder.setAmount((BigDecimal) netAmount);
                activeOrder.setDiscount((BigDecimal) discountAmount);
            }

            cartItemsRepository.save(cartItem);
            orderRepository.save(activeOrder);
            return activeOrder.getOrderDto();
        }
        return null;
    }

    public OrderDto placeorder(PlaceOrderDto placeOrderDto){
        Order activeOrder=orderRepository.findByUserIdAndOrderStatus(placeOrderDto.getUserId(), OrderStatus.PENDING);
        Optional<User> optionalUser= userRepository.findById(placeOrderDto.getUserId());
        if(optionalUser.isPresent()){
            activeOrder.setOrderDescription(placeOrderDto.getOrderDescription());
            activeOrder.setAddress(placeOrderDto.getAddress());
            activeOrder.setDate(new Date());
            activeOrder.setOrderStatus(OrderStatus.PLACED);
            activeOrder.setTrackingId(UUID.randomUUID());

            orderRepository.save(activeOrder);

            //do not create a new emty order immediately here
            /*Order order= new Order();
            order.setAmount(BigDecimal.ZERO);
            order.setTotalAmount(BigDecimal.ZERO);
            order.setDiscount(BigDecimal.ZERO);
            order.setUser(optionalUser.get());
            order.setOrderStatus(OrderStatus.PENDING);
            orderRepository.save(order);*/


        }
        return activeOrder.getOrderDto();
    }

    public List<OrderDto> getMyPlacedOrder(Long userId){
        List<OrderStatus> orderStatusList=List.of(OrderStatus.PLACED,OrderStatus.SHIPPED, OrderStatus.DELIVERED);

        return orderRepository.findAllByUserIdAndOrderStatusIn(userId,orderStatusList).
                stream().map(Order::getOrderDto).collect(Collectors.toList());
    }

    public OrderDto searchOrderByTrackingId(UUID trackingId){
        Optional<Order> optionalOrder=orderRepository.findByTrackingId(trackingId);
        if(optionalOrder.isPresent()){
            return  optionalOrder.get().getOrderDto();
        }
        return null;
    }

    public void confirmPayment(Long orderId){
         Optional<Order> optionalOrder=orderRepository.findById(orderId);
         if(optionalOrder.isPresent()){
             Order order=optionalOrder.get();
             if(order.getOrderStatus()==OrderStatus.PENDING){
                 order.setOrderStatus(OrderStatus.PLACED);
                 order.setTrackingId(UUID.randomUUID());
                 order.setDate(new Date());
                 orderRepository.save(order);
             }
         }
    }

}
