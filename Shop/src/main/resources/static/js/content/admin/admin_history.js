// 부트스트랩이 제공하는 모달 태그 선택하는 법
const modal_open = new bootstrap.Modal('#buy-detail-modal');

// // Modal 여는 방법 , 닫는 방법
// modal_open.show();
// modal_open.hide();

function showBuyDetail(buyCode){
    fetch('/admin/selectAdminBuyDetailList', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
           buyCode : buyCode
        })
    })
    .then((response) => {
        if(!response.ok){
            alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
            return ;
        }
    
        //return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
        return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        document.querySelector('.modal-body').innerHTML = '';

        let str = '';

        str +=
        `
        <div class="modal-body">
                        <div class="row mt-3">
                            <div class="col">
                                <table class="table text-center align-middle" style="font-size: 13pt;">
                                    <colgroup>
                                        <col width="20%">
                                        <col width="30%">
                                        <col width="20%">
                                        <col width="30%">
                                    </colgroup>
                                    <tr>
                                        <td>구매번호</td>
                                        <td>${data.buyCode}</td>
                                        <td>구매자ID</td>
                                        <td>${data.memberId}</td>
                                    </tr>
                                    <tr>
                                        <td>구매금액</td>
                                        <td>${data.buyPrice}</td>
                                        <td>구매일시</td>
                                        <td>${data.buyDate}</td>
                                    </tr>
                                </table>
                            </div>
                        </div>

                        <div class="row mt-4">
                            <div class="col">
                                <table class="table table-hover text-center align-middle table-bordered">
                                    <colgroup>
                                        <col width="10%">
                                        <col width="60%">
                                        <col width="10%">
                                        <col width="20%">
                                    </colgroup>
                                    <thead>
                                        <tr class="table-success">
                                            <td>No</td>
                                            <td>구매 상품</td>
                                            <td>수량</td>
                                            <td>결제 금액</td>
                                        </tr>
                                    </thead>
                                    <tbody>
        `
        let str2 = '';
        data.buyDetailList.forEach(function(buyDetail , index){
            str2 +=
            `
                <tr>
                    <td>${data.buyDetailList.length - index}</td>
                    <td>
                        <div class="row align-items-center">
                            <div class="col-3">
                                <img src="/upload/${buyDetail.itemVO.imgList[0].attachedFileName}" style="width:100%; height:100%;">
                            </div>
                            <div class="col-9 text-start">
                                ${buyDetail.itemVO.itemName}
                            </div>
                        </div>
                    </td>
                    <td>${buyDetail.buyCnt}</td>
                    <td>${buyDetail.totalPrice}</td>
                </tr>
        `
        });
      
        let str3 = '';
        str3 =
        `
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
        `
        document.querySelector('.modal-body').insertAdjacentHTML('afterbegin', str + str2 + str3);

        modal_open.show();
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}