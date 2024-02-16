package com.green.Shop.item.controller;

import com.green.Shop.item.service.ItemServiceImpl;
import com.green.Shop.item.vo.ImgVO;
import com.green.Shop.item.vo.ItemVO;
import com.green.Shop.util.UploadUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

// 상품과 관련된 모든 페이지 이동 처리 컨트롤러
@Controller
@RequestMapping("/item")
public class ItemController {
    @Resource(name = "itemService")
    private ItemServiceImpl itemService;

    @GetMapping("/list") // 상품 목록 페이지
    public String list(Model model, @RequestParam(name = "page", required = false, defaultValue = "all")String page,
                       @RequestParam(name = "cateCode", required = false, defaultValue = "0") int cateCode){
//        List<ItemVO> itemList = itemService.selectItem();
        model.addAttribute("itemList", itemService.selectItem(cateCode));
        model.addAttribute("page", page);

        return "content/item/item_list";
    }

    @GetMapping("itemDetail")
    public String itemDetail(@RequestParam(name = "itemCode")int itemCode, Model model){ // 상품 상세정보
        model.addAttribute("itemInfo", itemService.selectDetail(itemCode));
        return "content/item/item_detail.html";
    }


}
