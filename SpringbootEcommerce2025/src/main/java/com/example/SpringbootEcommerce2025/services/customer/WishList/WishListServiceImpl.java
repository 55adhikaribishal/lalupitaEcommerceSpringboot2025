package com.example.SpringbootEcommerce2025.services.customer.WishList;

import com.example.SpringbootEcommerce2025.dto.WishListDto;
import com.example.SpringbootEcommerce2025.entity.Product;
import com.example.SpringbootEcommerce2025.entity.User;
import com.example.SpringbootEcommerce2025.entity.WishList;
import com.example.SpringbootEcommerce2025.repository.ProductRepository;
import com.example.SpringbootEcommerce2025.repository.UserRepository;
import com.example.SpringbootEcommerce2025.repository.WishListRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WishListServiceImpl implements WishListService{
    private final WishListRepository wishListRepository;

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public WishListServiceImpl(WishListRepository wishListRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.wishListRepository = wishListRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public WishListDto addProductToWishList(WishListDto wishListDto){
        Optional<Product> optionalProduct=productRepository.findById(wishListDto.getProductId());
        Optional<User> optionalUser=userRepository.findById(wishListDto.getUserId());

        if(optionalProduct.isPresent() && optionalUser.isPresent()){
            WishList wishList= new WishList();
            wishList.setProduct(optionalProduct.get());
            wishList.setUser(optionalUser.get());

            return wishListRepository.save(wishList).getWishListDto();
        }
        return null;
    }

    public List<WishListDto> getWishListByUserId(Long userId){
        return wishListRepository.findAllByUserId(userId).stream()
                .map(WishList::getWishListDto).collect(Collectors.toList());
    }
}
