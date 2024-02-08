function itemDetailInfo(itemCode){
    const updateItemDetail = document.querySelector('.updateItemDetail');
    fetch('/admin/itemDetailInfo', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
           itemCode : itemCode
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
        updateItemDetail.innerHTML='';
        let str = '';

        str = `
        <div class="row">
                    <div class="col">
                        <h3>상품 기본 정보</h3>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <table class="table table-borderless text-center align-middle" style="font-size: 12pt;">
                            <colgroup>
                                <col width="30%">
                                <col width="70%">
                            </colgroup>
                            <tr>
                                <td class="text-start">카테고리</td>
                                <td>
                                    <select class="form-select">
                                        <option value="">1</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-start">상품명</td>
                                <td>
                                    <input type="text" class="form-control" value="${data.itemName}">
                                </td>
                            </tr>
                            <tr>
                                <td class="text-start">상품수량</td>
                                <td>
                                    <input type="text" class="form-control" style="width: 100%;" value="${data.itemStock}">
                                </td>
                            </tr>
                            <tr>
                                <td class="text-start">상품상태</td>
                                <td colspan="4" class="text-start">
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" id="inlineRadio1" value="">
                                        <label class="form-check-label" for="inlineRadio1">준비중</label>
                                      </div>
                                      <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" id="inlineRadio2" value="" checked>
                                        <label class="form-check-label" for="inlineRadio2">판매중</label>
                                      </div>
                                      <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" id="inlineRadio3" value="">
                                        <label class="form-check-label" for="inlineRadio3">매진</label>
                                      </div>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>

                <div class="row mt-4">
                    <div class="col">
                        <h3>상품 이미지 정보</h3>
                    </div>
                </div>

                <div class="row">
                    <div class="col">
                        <table class="table table-borderless text-start align-middle" style="font-size: 12pt;">
                            <colgroup>
                                <col width="30%">
                                <col width="70%">
                            </colgroup>
                            <tr>
                                <td>메인 이미지</td>
                                <td>a</td>
                            </tr>
                            <tr>
                                <td>상세 이미지</td>
                                <td>b</td>
                            </tr>
                        </table>
                    </div>
                </div>
        `
        

        updateItemDetail.insertAdjacentHTML("afterbegin", str);
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}