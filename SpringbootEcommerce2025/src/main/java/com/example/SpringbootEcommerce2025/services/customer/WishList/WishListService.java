package com.example.SpringbootEcommerce2025.services.customer.WishList;

import com.example.SpringbootEcommerce2025.dto.WishListDto;

import java.util.List;

public interface WishListService {
    WishListDto addProductToWishList(WishListDto wishListDto);
    List<WishListDto> getWishListByUserId(Long userId);
}
