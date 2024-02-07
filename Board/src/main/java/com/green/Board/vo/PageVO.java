package com.green.Board.vo;

//페이지 정보를 담고 있는 클래스
public class PageVO {
    private int nowPage; // 현재 선택된 페이지 번호
    private int totalDataCnt; // 전체 데이터 수 (모든 게시글 수)
    private int displayDataCnt; // 페이지 당 표시할 데이터 수
    private int displayPageCnt; // 페이지 당 표시할 페이지 수

    private int beginPage; // 화면에 보이는 첫번째 페이지 번호
    private int endPage; // 화면에 보이는 마지막 페이지 번호
    //1 2 3 4 5 다음 > beginPage = 1 , endPage = 5;
    //이전 6 7 8 9 10 다음 > beginPage = 6, endPage = 10;

    private boolean prev; // 이전 버튼의 유무
    private boolean next; // 다음 버튼의 유무


    public PageVO(){ //생성자 기본 세팅
        nowPage = 1;
        displayDataCnt = 5;
        displayPageCnt = 5;

    }

    //Math.ceil (올림)
    public void setPageInfo(){
//    endPage     (nowPage, displayPageCnt)
      endPage = (int)Math.ceil(nowPage / (double)displayPageCnt) * displayPageCnt;

        beginPage = endPage - (displayPageCnt-1);

        //전체 페이지 수
        int totalPageCnt = (int)Math.ceil(totalDataCnt / (double)displayDataCnt);

        //next 버튼의 유무
         if (endPage < totalPageCnt){
             next = true;
         }
         else {
             endPage = totalPageCnt; //비어있는 부분 지우기
             next = false;
         }

         //prev 버튼의 유무
        prev = beginPage != 1;


    }

    public void setTotalDataCnt(int totalDataCnt) {
        this.totalDataCnt = totalDataCnt;
    }

    public int getBeginPage() {
        return beginPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public boolean isPrev() {
        return prev;
    }

    public boolean isNext() {
        return next;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public int getDisplayDataCnt() {
        return displayDataCnt;
    }

    public int getNowPage() {
        return nowPage;
    }
}
