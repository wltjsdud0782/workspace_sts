package com.green.Shop.admin.service;

import com.green.Shop.item.vo.ImgVO;
import com.green.Shop.item.vo.ItemVO;

import java.util.List;

public interface AdminService {

    int selectNextItemCode(); // 다음에 들어갈 아이템 코드 조회

    void insertItem(ItemVO itemVO); // 상품 등록 + 상품 이미지 등록


}
