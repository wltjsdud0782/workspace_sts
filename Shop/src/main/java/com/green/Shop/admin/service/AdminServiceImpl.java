package com.green.Shop.admin.service;

import com.green.Shop.buy.vo.ShopBuyVO;
import com.green.Shop.item.vo.ItemVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService{
    @Autowired
    SqlSessionTemplate sqlSession;

    @Override
    public int selectNextItemCode() {
        return sqlSession.selectOne("adminMapper.selectNextItemCode");
    }

    @Override
    @Transactional (rollbackFor = Exception.class)
    // @Transactional : 메소드 내부 실행이 전체 완료됐을 때 커밋 실행 , 하나라도 오류 떴을 땐 롤백.
    // rollbackFor = Exception.class : 언제 롤백? = 모든 오류 발생 시.
    public void insertItem(ItemVO itemVO) {
        sqlSession.insert("adminMapper.insertItem", itemVO);
        sqlSession.insert("adminMapper.insertImgs", itemVO);
    }

    @Override
    public List<ShopBuyVO> selectAdminBuyList() { // 관리자메뉴 구매이력관리
        return sqlSession.selectList("adminMapper.selectAdminBuyList");
    }

    @Override
    public ShopBuyVO selectAdminBuyDetailList(ShopBuyVO shopBuyVO) { // 구매상세내역조회
        return sqlSession.selectOne("adminMapper.selectAdminBuyDetailList", shopBuyVO);
    }

}
