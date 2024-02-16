package com.green.Shop.item.service;

import com.green.Shop.item.vo.CategoryVO;
import com.green.Shop.item.vo.ImgVO;
import com.green.Shop.item.vo.ItemVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("itemService")
public class ItemServiceImpl implements ItemService{
    @Autowired
    SqlSessionTemplate sqlSession;

    @Override
    public List<CategoryVO> selectCate() {
        return sqlSession.selectList("itemMapper.selectCate");
    }

    @Override
    public List<ItemVO> selectItem(int itemCode) {
        return sqlSession.selectList("itemMapper.selectItem", itemCode);
    }

    @Override
    public ItemVO selectDetail(int cateCode) {
        return sqlSession.selectOne("itemMapper.selectDetail", cateCode);
    }


}



