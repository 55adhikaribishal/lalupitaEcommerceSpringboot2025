package com.example.SpringbootEcommerce2025.controller.customer;

import com.example.SpringbootEcommerce2025.dto.AddProductInCartDto;
import com.example.SpringbootEcommerce2025.dto.OrderDto;
import com.example.SpringbootEcommerce2025.dto.PlaceOrderDto;
import com.example.SpringbootEcommerce2025.entity.Order;
import com.example.SpringbootEcommerce2025.entity.User;
import com.example.SpringbootEcommerce2025.exceptions.ValidationException;
import com.example.SpringbootEcommerce2025.repository.UserRepository;
import com.example.SpringbootEcommerce2025.services.customer.cart.CartService;
import com.example.SpringbootEcommerce2025.services.customer.order.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
public class CartController {

    @Autowired
    private final CartService cartService;
    private final UserRepository userRepository;
    private final CustomerOrderService customerOrderService;

    public CartController(CartService cartService, UserRepository userRepository, CustomerOrderService customerOrderService) {
        this.cartService = cartService;
        this.userRepository = userRepository;
        this.customerOrderService = customerOrderService;
    }

    @PostMapping("/cart")
    public ResponseEntity<?> addProductToCart(@RequestBody AddProductInCartDto addProductInCartDto){
        System.out.println("Cart access: " + SecurityContextHolder.getContext().getAuthentication());
        String email=SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userOpt=userRepository.findByEmail(email);
        if(userOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid User passed");
        }
        return cartService.addProductToCart(addProductInCartDto);
    }

    @GetMapping("/cart/{userId}")
    public ResponseEntity<?> getCartByUserId(@PathVariable Long userId){
        String email=SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userOpt=userRepository.findByEmail(email);
        if(userOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid User");
        }
        //OrderDto orderDto=cartService.getCartByUserId(userId);
        OrderDto orderDto=cartService.getCartByUserId(userOpt.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }

    @GetMapping("/coupon/{userId}/{code}")
    public ResponseEntity<?> applyCoupon(@PathVariable Long userId, @PathVariable String code){
        try{
            OrderDto orderDto= cartService.applyCoupon(userId,code);
            return ResponseEntity.ok(orderDto);
        }catch(ValidationException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
    @PostMapping("/addition")
    public ResponseEntity<?> increaseProductQuantity(@RequestBody AddProductInCartDto addProductInCartDto){
        // System.out.println("Cart access: " + SecurityContextHolder.getContext().getAuthentication());
        String email=SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userOpt=userRepository.findByEmail(email);
        if(userOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid User passed");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.increaseProductQuantity(addProductInCartDto));
        //return cartService.addProductToCart(addProductInCartDto);
    }

    @PostMapping("/deduction")
    public ResponseEntity<?> decreaseProductQuantity(@RequestBody AddProductInCartDto addProductInCartDto){
        // System.out.println("Cart access: " + SecurityContextHolder.getContext().getAuthentication());
        String email=SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userOpt=userRepository.findByEmail(email);
        if(userOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid User passed");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.decreaseProductQuantity(addProductInCartDto));
        //return cartService.addProductToCart(addProductInCartDto);
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<?> placeOrder(@RequestBody PlaceOrderDto placeOrderDto){
        // System.out.println("Cart access: " + SecurityContextHolder.getContext().getAuthentication());
        String email=SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userOpt=userRepository.findByEmail(email);
        if(userOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid User passed");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.placeorder(placeOrderDto));

    }

    @GetMapping("/myOrders/{userId}")
    public ResponseEntity<List<OrderDto>> getMyPlacedOrder(@PathVariable Long userId){
        return ResponseEntity.ok(cartService.getMyPlacedOrder(userId));
    }

    @GetMapping("/confirm-payment")
    public ResponseEntity<Order> confirmOrderPayment(@RequestParam("orderId") Long orderId){
        Order confirmedOrder=customerOrderService.confirmOrderPayment(orderId);
        return ResponseEntity.ok(confirmedOrder); //sends order as json to angular
    }


    //chnaged the return type to make angular read json format
//    public ResponseEntity<String> confirmPaymenet(@PathVariable Long orderId){
//        cartService.confirmPayment(orderId);
//        return ResponseEntity.ok("Payment Confirmed and order updated!");
//    }
    //we could have done this on angular instead
   // this.http.get('http://localhost:8080/api/customer/confirm-payment/' + orderId, { responseType: 'text' });
//    public ResponseEntity<Map<String,String>> confirmPayment(@PathVariable Long orderId){
//        cartService.confirmPayment(orderId);
//        Map<String,String> response=new HashMap<>();
//        response.put("message","Payment Confirmed and order Updated!");
//        return ResponseEntity.ok(response);
//    }
}
