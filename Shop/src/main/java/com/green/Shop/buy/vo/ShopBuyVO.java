package com.green.Shop.buy.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ShopBuyVO {
    private int buyCode;
    private String memberId;
    private int buyPrice;
    private String buyDate;
    // insert 쿼리에 의한 추가 변수
    // 구매정보:구입상세정보 >> 1:n 관계
    private List<BuyDetailVO> buyDetailList;
//    private BuyDetailVO buyOne;


}
