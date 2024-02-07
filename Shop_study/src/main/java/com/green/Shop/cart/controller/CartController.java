package com.green.Shop.cart.controller;

import com.green.Shop.cart.service.CartServiceImpl;
import com.green.Shop.cart.vo.CartVO;
import com.green.Shop.cart.vo.CartViewVO;
import com.green.Shop.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Resource(name = "cartService")
    private CartServiceImpl cartService;

    @ResponseBody
    @PostMapping("insertCart")
    public void insertCart(CartVO cartVO, HttpSession session){ // 장바구니 insert & select

        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo"); // 로그인 된 회원 아이디 셋팅
        cartVO.setMemberId(loginInfo.getMemberId());

        CartViewVO findInfo = cartService.findCartItem(cartVO); // 중복된 상품 찾기

        if (findInfo == null){ // 중복 상품 없을때
        cartService.insertCart(cartVO);
        }
        else { // 중복 상품 있을때
            cartService.updateCartItem(cartVO);
        }
    }

    @GetMapping("list")
    public String cartList(HttpSession session, Model model){ // 장바구니 목록
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");

        model.addAttribute("cartList", cartService.selectCart(loginInfo.getMemberId()));

        //총 가격 계산 후 전달
//        int sum = 0;
//        for (CartViewVO e : cartService.selectCart(loginInfo.getMemberId())){
//            sum = sum + e.getTotalPrice();
//        }
//        model.addAttribute("allPrice", sum);

        return "content/cart/cart_list";
    }

    @GetMapping("/deleteCartItem")
    public String deleteCartItem(CartVO cartVO){ // 장바구니 상품 삭제
        cartService.deleteCartItem(cartVO);
        return "redirect:/cart/list";
    }

    @ResponseBody
    @PostMapping("/updateCartCnt")
    public void updateCartCnt(CartVO cartVO, Model model, HttpSession session){
        cartService.updateCartCnt(cartVO);
    }
}
