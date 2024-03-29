package com.green.Shop.admin.service;

import com.green.Shop.admin.vo.SearchVO;
import com.green.Shop.buy.vo.ShopBuyVO;
import com.green.Shop.item.vo.ImgVO;
import com.green.Shop.item.vo.ItemVO;

import java.util.List;

public interface AdminService {

    int selectNextItemCode(); // 다음에 들어갈 아이템 코드 조회

    void insertItem(ItemVO itemVO); // 상품 등록 + 상품 이미지 등록

    List<ShopBuyVO> selectAdminBuyList(SearchVO searchVO); // 관리자메뉴 구매이력관리

    ShopBuyVO selectAdminBuyDetailList(ShopBuyVO shopBuyVO); // 관리자메뉴 구매상세내역조회

    List<ItemVO> selectItemList(); //상품 정보 변경 상품 목록 조회

    ItemVO selectItemDetail(int itemCode); // 상품 정보 변경 상품 하나 조회

    void updateItemDetail(ItemVO itemVO); // 상품 정보 변경하기
}
