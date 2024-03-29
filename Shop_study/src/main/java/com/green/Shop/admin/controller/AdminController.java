package com.green.Shop.admin.controller;

import com.green.Shop.admin.service.AdminServiceImpl;
import com.green.Shop.item.service.ItemServiceImpl;
import com.green.Shop.item.vo.CategoryVO;
import com.green.Shop.item.vo.ImgVO;
import com.green.Shop.item.vo.ItemVO;
import com.green.Shop.util.ConstantVariable;
import com.green.Shop.util.UploadUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Resource(name = "itemService")
    private ItemServiceImpl itemService;
    @Resource(name = "adminService")
    private AdminServiceImpl adminService;

    // 관리자의 상품페이지 이동
    @GetMapping("/regItemForm")
    public String regItemForm(Model model){
        model.addAttribute("cateList", itemService.selectCate()); // 카테고리 목록 표시
        return "content/admin/reg_item_form";
    }

    // 상품 등록하기
    @PostMapping("/regItem")
    public String insertItem(ItemVO itemVO, @RequestParam(name = "itemMainImg")MultipartFile itemMainImg
                                ,@RequestParam(name = "itemInfoImgs")MultipartFile[] itemInfoImgs){

        int itemCode = adminService.selectNextItemCode(); // 다음에 들어갈 아이템 코드 조회

        itemVO.setItemCode(itemCode); // 상품 아이템코드 삽입

        //------------------------------------------- 파일 첨부 코드 -------------------------------------------//

        ImgVO mainImgVO = UploadUtil.uploadFile(itemMainImg); // !!!!!!!!! UploadUtil 클래스에서 생성한 단일 파일 업로드 코드 !!!!!!!!!!!!!!!

        List<ImgVO> imgList = UploadUtil.multiUploadFile(itemInfoImgs); // !!!!!!!!! UploadUtil 클래스에서 생성한 다중 파일 업로드 코드 !!!!!!!!!!!!!

        //------------------------------------------- 이미지 정보 등록 코드 -------------------------------------------//

        mainImgVO.setItemCode(itemCode); // 첨부된 파일 아이템 코드 변경

        for (ImgVO img : imgList){ // 첨부된 다중 파일 아이템 코드 변경
            img.setItemCode(itemCode);
        }
        imgList.add(mainImgVO); // 하나의 상품코드에 포함된 첨부 파일 ALL
        itemVO.setImgList(imgList); // vo 코드가 작동 될 수 잇게 vo imgList 에 첨부파일ALL 을 넣어준다.

        System.out.println(itemVO); // 파일 전체 정보 확인할 출력문

        adminService.insertItem(itemVO); // 상품, 상품 이미지 insert

        return "redirect:/admin/regItemForm";
    }

}
