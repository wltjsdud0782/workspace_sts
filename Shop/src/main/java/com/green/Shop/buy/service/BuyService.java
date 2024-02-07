package com.green.Shop.buy.service;

import com.green.Shop.buy.vo.BuyDetailVO;
import com.green.Shop.buy.vo.ShopBuyVO;
import com.green.Shop.cart.vo.CartVO;

import java.util.List;

public interface BuyService {

    int selectNextBuyCode(); // 다음 buyCode 조회

    void insertBuys(ShopBuyVO shopBuyVO, CartVO cartVO); // 장바구니 상품 구매

    void insertBuyOne(ShopBuyVO shopBuyVO, BuyDetailVO buyDetailVO); // 단일 상품 구매

    List<ShopBuyVO> selectBuyList(String memberId);  // 내정보 > 구매목록 조회
}
