package com.green.Shop.item.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ItemVO{
    private int itemCode;
    private String itemName;
    private int itemPrice;
    private int itemStock;
    private String itemIntro;
    private String regDate;
    private int cateCode;
    private int itemStatus;
    private String statusName;
    // 아이템:아이템이미지 >> 1:n 관계
    private List<ImgVO> imgList;
    // 아이템:카테고리 >> 1:1 관계
    private CategoryVO categoryVO;
}
