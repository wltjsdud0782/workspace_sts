package com.green.Shop.cart.service;

import com.green.Shop.cart.vo.CartVO;
import com.green.Shop.cart.vo.CartViewVO;

import java.util.List;

public interface CartService {

    void insertCart(CartVO cartVO); // 상품 장바구니 등록

    List<CartViewVO> selectCart(String memberId); // 장바구니 목록 조회

    CartViewVO findCartItem(CartVO cartVO); // 장바구니 중복 상품 조회

    void updateCartItem(CartVO cartVO); // 장바구니 중복 상품 등록시 수량 변경

    void deleteCartItem(CartVO cartVO); // 장바구니 상품 삭제

    void updateCartCnt(CartVO cartVO); // 장바구니 개수 변경
}
