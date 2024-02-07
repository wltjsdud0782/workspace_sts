package com.green.Shop.study.upload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.cert.Extension;
import java.util.UUID;

// 파일 업로드, 다운로드 학습용 컨트롤러
@Controller
@RequestMapping("/file")
public class FileController {

    // 파일 첨부 페이지로 이동
    @GetMapping("/uploadForm")
    public String uploadForm(){

        // 파일 확장자 추출 !!!!!!!!! 연습
        String file1 = "ab.c.jpg";
        System.out.println(file1.substring(1, 5)); // 1 <= x < 5 번째 글자 출력

        System.out.println(file1.indexOf(".")); // 처음 나오는 . 의 위치 순번
        System.out.println(file1.lastIndexOf(".")); // 마지막에 나오는 . 의 위치 순번

        file1.substring(file1.lastIndexOf(".")); //최종

        return "test/upload/upload_form";
    }


    // 파일 업로드
    // 첨부 파일 받을 때는 multipartFile 사용 !!!!!!!!!!!!!!!!!!!!!!!
    @PostMapping("/upload")
    public String upload(@RequestParam(name = "img1")MultipartFile img1
                        , @RequestParam(name = "img2")MultipartFile img2){
        // 첨부한 파일명 받아오는 법 ▼ !!!!!!!!!!!!!!
        String originFileName = img1.getOriginalFilename();
        System.out.println(originFileName);

        // 업로드 경로 !!!!!!!!!!!
        // 특수문자도 문자열로 저장하기 위해서는 특수문자 앞 \ 를 붙인다.
//        String str = " \"하이\" ";
//        System.out.println(str); // "하이"
        String uploadPath = "D:\\01-STUDY\\dev\\workspace_sts\\Shop\\src\\main\\resources\\static\\upload\\";

        // 파일 업로드를 위한 경로 및 파일명을 하나의 문자열로 나열 !!!!!!!!!!!!
        String fileName = uploadPath + originFileName;

        // 파일 업로드 (예외처리)
        // java.io.File 선택
        try {
            File file = new File(fileName);
            img1.transferTo(file);
        } catch (IOException e) {
            System.out.println("!!! 파일 첨부 중 오류 발생 !!!");
            throw new RuntimeException(e);
        }

        // 두번째 파일 업로드
        // 원본 파일의 확장자만 추출 (ex > .jpg , .txt, .png)
        String originFileName2 = img2.getOriginalFilename();

        String extension = originFileName2.substring(originFileName2.lastIndexOf("."));


        // 서버에 저장할 파일명 생성
        String uuid = UUID.randomUUID().toString(); // 랜덤 문자 생성
        String attachedFileName = uuid + extension;

        try {
            File file1 = new File(uploadPath + attachedFileName);
            img2.transferTo(file1);
        } catch (Exception e){
            System.out.println("!!! 파일 첨부 중 오류 발생2 !!!");
            e.printStackTrace();
        }

        return "";
    }

    // 다중 첨부 페이지 이동
    @GetMapping("/multiForm")
    public String multiForm(){

        return "test/upload/multi_form";
    }

    // 다중 첨부 실행
    @PostMapping("/multi")
    public String multi(@RequestParam(name = "imgs")MultipartFile[] imgs){
        // 첨부 파일 경로
        String uploadPath = "D:\\01-STUDY\\dev\\workspace_sts\\Shop\\src\\main\\resources\\static\\upload\\";

        for (MultipartFile img : imgs){
            System.out.println(img.getOriginalFilename());

           // 확장자 추출 (원본이름.자르겠다(원본이름.마지막"."이후부터 끝까지))
            String extension = img.getOriginalFilename().substring(img.getOriginalFilename().lastIndexOf("."));

            // 첨부될 파일명 ( 파일명 = 랜덤이름 + 확장자 )
            String attachedFileName = UUID.randomUUID().toString() + extension;

            // 첨부 try : 새로운 파일 (경로 + 파일명) 으로 첨부파일 하나마다 파일 저장
            try {
                File file = new File(uploadPath + attachedFileName);
                img.transferTo(file);

            } catch (Exception e){
                System.out.println("!!! 다중 첨부 중 오류 발생 !!!");
                e.printStackTrace();
            }


        }

        return "";
    }
}
