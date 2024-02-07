package com.green.NoticeBoard.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NoticeBoardVO {
    private int boardNum;
    private String boardTitle;
    private String boardContent;
    private String writer;
    private String createDate;
    private int readCnt;
}
