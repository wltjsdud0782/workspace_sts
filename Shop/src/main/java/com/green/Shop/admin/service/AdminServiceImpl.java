package com.green.Shop.admin.service;

import com.green.Shop.admin.vo.SearchVO;
import com.green.Shop.buy.vo.ShopBuyVO;
import com.green.Shop.item.vo.ImgVO;
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
    public List<ShopBuyVO> selectAdminBuyList(SearchVO searchVO) { // 관리자메뉴 구매이력관리
        return sqlSession.selectList("adminMapper.selectAdminBuyList", searchVO);
    }

    @Override
    public ShopBuyVO selectAdminBuyDetailList(ShopBuyVO shopBuyVO) { // 구매상세내역조회
        return sqlSession.selectOne("adminMapper.selectAdminBuyDetailList", shopBuyVO);
    }

    @Override
    public List<ItemVO> selectItemList() { // 업데이트화면 상품 목록 조회
        return sqlSession.selectList("adminMapper.selectItemList");
    }

    @Override
    public ItemVO selectItemDetail(int itemCode) { // 업데이트화면 오른쪽 상품 하나 조회
        return sqlSession.selectOne("adminMapper.selectItemDetail", itemCode);
    }

    @Override
    public void updateItemDetail(ItemVO itemVO) { // 상품 정보 변경
        sqlSession.update("adminMapper.updateItemDetail", itemVO);
    }

    @Override
    public String selectImg(ImgVO imgVO) { // 사진 상세 보기
        return sqlSession.selectOne("adminMapper.selectImg", imgVO);
    }

}
