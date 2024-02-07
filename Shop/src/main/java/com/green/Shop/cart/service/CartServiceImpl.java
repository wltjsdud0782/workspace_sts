package com.green.Shop.cart.service;

import com.green.Shop.cart.vo.CartVO;
import com.green.Shop.cart.vo.CartViewVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("cartService")
public class CartServiceImpl implements CartService{
    @Autowired
    SqlSessionTemplate sqlSession;

    @Override
    public void insertCart(CartVO cartVO) { // 상품 장바구니 등록
        sqlSession.selectOne("cartMapper.insertCart", cartVO);
    }

    @Override
    public List<CartViewVO> selectCart(String memberId) { // 장바구니 목록 조회
        return sqlSession.selectList("cartMapper.selectCart", memberId);
    }

    @Override
    public CartViewVO findCartItem(CartVO cartVO) { // 장바구니 중복 상품 조회
        return sqlSession.selectOne("cartMapper.findCartItem", cartVO);
    }

    @Override
    public void updateCartItem(CartVO cartVO) { // 장바구니 중복 상품 등록 시 수량 변경
        sqlSession.update("cartMapper.updateCartItem", cartVO);
    }

    @Override
    public void deleteCartItem(CartVO cartVO) { //장바구니 상품 삭제
        sqlSession.delete("cartMapper.deleteCartItem", cartVO);
    }

    @Override
    public void updateCartCnt(CartVO cartVO) { // 장바구니 개수 변경
        sqlSession.update("cartMapper.updateCartCnt", cartVO);
    }

    @Override
    public void deleteChk(CartVO cartVO) {
        sqlSession.delete("cartMapper.deleteChk", cartVO);
    }

    @Override
    public List<CartViewVO> selectForBuy(CartVO cartVO) {
        return sqlSession.selectList("cartMapper.selectForBuy", cartVO);
    }


}
