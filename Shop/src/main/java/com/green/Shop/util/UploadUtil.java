package com.green.Shop.util;

import com.green.Shop.item.vo.ImgVO;
import com.green.Shop.item.vo.ItemVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// 파일 첨부와 관련된 기능 모음집
public class UploadUtil {
    // 파일 확장자를 문자열로 리턴하는 메소드
    public static String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    //랜덤 이름 메소드
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    // 단일 파일 업로드 메소드
    public static ImgVO uploadFile(MultipartFile uploadFile) {
        ImgVO imgVO = null;

        if (!uploadFile.isEmpty()) { // 첨부된 파일이 존재할 때
            imgVO = new ImgVO();

            String extension = getExtension(uploadFile.getOriginalFilename()); // 상수로 다른 클래스에 저장해놓은 확장자명 조회 !!!!!!!!!!!!!!!!!!!!!!

            String attachedFileName = getUUID() + extension; // 새로운 랜덤 명 + 확장자명

            try {
                File file = new File(ConstantVariable.UPLOAD_PATH + attachedFileName); // 클래스에 저장한 파일경로!!!!! + 파일명
                uploadFile.transferTo(file); // ~로 전송

                imgVO.setAttachedFileName(attachedFileName); // 바뀐 파일명을 vo에 삽입 !!!!!!!!
                imgVO.setOriginFileName(uploadFile.getOriginalFilename()); // 원본 파일명
                imgVO.setIsMain("Y"); // 메인 이미지

            } catch (Exception e) {
                System.out.println("!!! 단일 파일 첨부 중 오류 발생 !!!");
                e.printStackTrace();
            }
        }
        return imgVO;
    }

    // 다중 파일 업로드 메소드
    public static List<ImgVO> multiUploadFile(MultipartFile[] uploadFiles) {
        List<ImgVO> imgList = new ArrayList<>();

        for (MultipartFile uploadFile : uploadFiles) {
            ImgVO vo = uploadFile(uploadFile);
            if (vo != null) {
                vo.setIsMain("N");
                imgList.add(vo);
            }
        }
            return imgList;
    }






}
