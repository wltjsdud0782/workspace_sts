package com.green.Shop.buy.service;

import com.green.Shop.buy.vo.BuyDetailVO;
import com.green.Shop.buy.vo.ShopBuyVO;
import com.green.Shop.cart.vo.CartVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("buyService")
public class BuyServiceImpl implements BuyService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public int selectNextBuyCode() { // 다음 buyCode 조회
        return sqlSession.selectOne("buyMapper.selectNextBuyCode");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertBuys(ShopBuyVO shopBuyVO, CartVO cartVO) { // 장바구니 상품 구매
        sqlSession.insert("buyMapper.insertBuy", shopBuyVO);
        sqlSession.insert("buyMapper.insertBuyDetails", shopBuyVO);
        sqlSession.delete("cartMapper.deleteChk", cartVO);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertBuyOne(ShopBuyVO shopBuyVO, BuyDetailVO buyDetailVO) { // 단일 상품 구매
        sqlSession.insert("buyMapper.insertBuy", shopBuyVO);
        sqlSession.insert("buyMapper.insertBuyOne", buyDetailVO);
    }

    @Override
    public List<ShopBuyVO> selectBuyList(String memberId) { // 내정보 > 구매목록 조회
        return sqlSession.selectList("buyMapper.selectBuyList", memberId);
    }

}
