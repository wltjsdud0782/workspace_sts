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
        console.log(data);
        updateItemDetail.innerHTML='';
        let str = '';

        str = `
        <form action="/admin/updateItemDetail" method="post">
        <div class="row">
                    <div class="col">
                        <h3>상품 기본 정보</h3>
                        <input type="hidden" name="itemCode" value="${data.itemDetail.itemCode}">
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
                                    <select name="cateCode" class="form-select">`
                                data.cateList.forEach(function(cate, index) {
                                  str += `<option ${cate.cateCode == data.itemDetail.cateCode ? 'selected' : ''} value="${cate.cateCode}">${cate.cateName}</option>`
                                });      
                            str +=  `</select>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-start">상품명</td>
                                <td>
                                    <input name="itemName" type="text" class="form-control" value="${data.itemDetail.itemName}">
                                </td>
                            </tr>
                            <tr>
                                <td class="text-start">상품수량</td>
                                <td>
                                    <input name="itemStock" type="text" class="form-control" style="width: 100%;" value="${data.itemDetail.itemStock}">
                                </td>
                            </tr>
                            <tr>
                                <td class="text-start">상품상태</td>
                                <td colspan="4" class="text-start">
                                    <div class="form-check form-check-inline">
                                        <input name="itemStatus" class="form-check-input" type="radio" id="inlineRadio1" value="1" ${data.itemDetail.itemStatus == 1 ? 'checked' : ''}>
                                        <label class="form-check-label" for="inlineRadio1">준비중</label>
                                      </div>
                                      <div class="form-check form-check-inline">
                                        <input name="itemStatus" class="form-check-input" type="radio" id="inlineRadio2" value="2" ${data.itemDetail.itemStatus == 2 ? 'checked' : ''}>
                                        <label class="form-check-label" for="inlineRadio2">판매중</label>
                                      </div>
                                      <div class="form-check form-check-inline">
                                        <input name="itemStatus" class="form-check-input" type="radio" id="inlineRadio3" value="3" ${data.itemDetail.itemStatus == 3 ? 'checked' : ''}>
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

                <div class="row ms-1 mt-2">
                    <div class="col">
                        <div class="row" style="font-size: 12pt;">
                            <div class="col-4">
                                메인 이미지
                            </div>
                            <div class="col-8">`;

            data.itemDetail.imgList.forEach(function(img , index){
            str += `<span class="pointer-span" onclick="showImg(${img.imgCode})">
            ${img.isMain == 'Y' ? img.originFileName : ''}
            </span>`
        });
        str += `
                 </div>
                </div>
                <div class="row" style="font-size: 12pt;">
                    <div class="col-4">
                        상세 이미지
                    </div>
                    <div class="col-8">`;

        data.itemDetail.imgList.forEach(function(img , index){
            str += `<div class="row">`
            str += `    <div class="col">`
            str += `        <span class="pointer-span" onclick="showImg(${img.imgCode})">
                                ${img.isMain == 'N' ? img.originFileName : ''}
                            </span>`
            str += `    </div>`
            str += `</div>`
        });

str += `
                </div>
            </div>
                <div class="row mt-3">
                    <div class="offset-9 col-3 d-grid">
                        <input type="submit" class="btn btn-success" value="변경">
                    </div>
                </div>
                </form>
        `
        updateItemDetail.insertAdjacentHTML("afterbegin", str);
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}



// -----------상세 이미지 확인 모달 비동기 열기------------- //
const img_modal_open = new bootstrap.Modal('#item-img');
function showImg(imgCode){
    fetch('/admin/selectImg', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
           imgCode : imgCode
        })
    })
    .then((response) => {
        if(!response.ok){
            alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
            return ;
        }
    
        return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
        //return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        document.querySelector('.modal-body').innerHTML = '';

        let str = '';

        str = `
            <div class="row text-center">
                <div class="col">
                    <img src="/upload/${data}" style="width:50%; height:100%;">
                </div>
            </div>
        `
            

        document.querySelector('.modal-body').insertAdjacentHTML("afterbegin", str);

        img_modal_open.show();
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}