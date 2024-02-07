package com.green.Shop.util;

// 상수를 저장하는 클래스 static > 다른 클래스에서 객체 생성하지 않고 바로 사용할 수 있음.

public class ConstantVariable {
    // 자바의 상수 > final 사용 + 상수명은 대문자 사용
    final int Num = 10;
    //        num = 20; > 오류
    public final static String UPLOAD_PATH = "D:\\01-STUDY\\dev\\workspace_sts\\Shop\\src\\main\\resources\\static\\upload\\";


}
