package com.green.Shop.buy.controller;

import com.green.Shop.buy.service.BuyServiceImpl;
import com.green.Shop.buy.vo.BuyDetailVO;
import com.green.Shop.buy.vo.ShopBuyVO;
import com.green.Shop.cart.service.CartServiceImpl;
import com.green.Shop.cart.vo.CartVO;
import com.green.Shop.cart.vo.CartViewVO;
import com.green.Shop.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/buy")
public class BuyController {
    @Resource(name = "buyService")
    private BuyServiceImpl buyService;
    @Resource(name = "cartService")
    private CartServiceImpl cartService;

    @GetMapping("insertBuy")
    public String insertBuy(CartVO cartVO, HttpSession session){
        // 선택된 아이템 코드를 통해 구매 상세정보에 넣을 ITEM_CODE, CART_CNT, TOTAL_PRICE 가져오기
        List<CartViewVO> cartViewList = cartService.selectForBuy(cartVO);

        // 구매 정보 테이블에 들어갈 Buy_Price 셋팅 (구매 총 가격)
         int buyPrice = 0;
         for (CartViewVO e : cartViewList){
             buyPrice = buyPrice + e.getTotalPrice();
         }

         // 구매자 아이디 셋팅
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        String memberId = loginInfo.getMemberId();

        // 다음에 들어갈 buyCode 값 결정하기
        int buyCode = buyService.selectNextBuyCode();

        // 전부 set 후 구매 정보, 상세 테이블 insert
        ShopBuyVO buyVO = new ShopBuyVO();
        buyVO.setBuyPrice(buyPrice); // buyPrice
        buyVO.setMemberId(memberId); // memberId
        buyVO.setBuyCode(buyCode); // buyCode

        List<BuyDetailVO> buyDetailList = new ArrayList<>();
        for (CartViewVO cartViewVO : cartViewList){
            BuyDetailVO vo = new BuyDetailVO();
            vo.setItemCode(cartViewVO.getItemCode()); // itemCode
            vo.setBuyCnt(cartViewVO.getCartCnt()); // buyCnt
            vo.setTotalPrice(cartViewVO.getTotalPrice()); // totalPrice
            buyDetailList.add(vo); // 여러 정보를 리스트에 삽입
        }

        // 하나의 구매정보에 여러 상세 구매 정보 존재.
        buyVO.setBuyDetailList(buyDetailList); // 미리 만들어둔 변수 list 에 삽입

        buyService.insertBuys(buyVO, cartVO);

        return "redirect:/";
    }

    @PostMapping("insertBuyOne")
    public String insertBuyOne(HttpSession session, ShopBuyVO shopBuyVO, BuyDetailVO buyDetailVO){
        // 구매자 아이디 셋팅
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        String memberId = loginInfo.getMemberId();

        // 다음에 들어갈 buyCode 값 결정하기
        int buyCode = buyService.selectNextBuyCode();

        shopBuyVO.setBuyCode(buyCode); // buyCode
        shopBuyVO.setMemberId(memberId); // memberId
        // 총 가격 계산해주기 = 단일 상품 갯수 * 단가
        shopBuyVO.setBuyPrice(buyDetailVO.getBuyCnt() * shopBuyVO.getBuyPrice()); // buyPrice

        buyDetailVO.setBuyCode(buyCode); // buyCode
        //바로 구매는 결제하는 총 가격과 같다.
        buyDetailVO.setTotalPrice(shopBuyVO.getBuyPrice()); // totalPrice = buyPrice

        buyService.insertBuyOne(shopBuyVO, buyDetailVO);

        return "redirect:/";
    }

    @GetMapping("/history")
    public String history(HttpSession session
                        , @RequestParam(name = "page", required = false, defaultValue = "history")String page
                        , Model model) {
        model.addAttribute("page", page);
        // 로그인 된 회원 아이디 셋팅
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
        String memberId = loginInfo.getMemberId();
        // 구매 목록 조회
        List<ShopBuyVO> buyList = buyService.selectBuyList(memberId);
        model.addAttribute("buyList", buyList);

        return "content/buy/history";
    }
}
