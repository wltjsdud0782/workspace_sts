package com.green.Shop.buy.vo;

import com.green.Shop.item.vo.ItemVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BuyDetailVO {
    private int buyDetailCode;
    private int itemCode;
    private int buyCnt;
    private int totalPrice;
    private int buyCode;
    // selectBuyList 상세정보:아이템 >> 1:1 관계
    private ItemVO itemVO;
}
