setAllPrice(); // 장바구니 목록 가자마자 총액 계산하여 화면에 띄우기

// 체크박스 전체 선택&해제
function chkAll(){
    const isChk = document.querySelector('#checkAll').checked;
    const chkItems = document.querySelectorAll('.chk');

    if(isChk){
        for(const chkItem of chkItems){
            chkItem.checked = true;
        }
    }
    else{
        for(const chkItem of chkItems){
            chkItem.checked = false;
        }
    }
    setAllPrice();
}

//총액 계산
function setAllPrice(){
    //체크된 장바구니 상품 총액 계산
    //                                         ▼ :checked = 체크가 된 것만 가져온다
    const chks = document.querySelectorAll('.chk:checked');

    let allPrice = 0;
    chks.forEach(function(chk, i){
        //체크된 상품의 총액 찾아가기
        //             ▼.closest = 앞 선택자를 감싸고있는 가장 가까운 태그 찾아간다
        const strPrice = chk.closest('tr').children[5].textContent;
        
        const regex = /[^0-9]/g; // 정규식을 이용하여 숫자 외의 문자 선택
        //                              ▼.replace = 숫자가 아닌 것을 빈 문자로 치환
        const price = parseInt(strPrice.replace(regex,''));
        
        allPrice = allPrice + price;
    })

    document.querySelector('#allPrice-span').textContent = allPrice.toLocaleString();
}

// 상품 삭제
function deleteCartItem(cartCode){
    const result = confirm('선택한 상품을 장바구니에서 삭제하시겠습니까?');

    if(result){
        location.href = "/cart/deleteCartItem?cartCode=" + cartCode;
    }
}

// 상품 개수 변경
function updateCartCnt(selectThis, cartCode, itemPrice){
    const result = confirm('선택한 상품의 수량을 변경하시겠습니까?')
    if(result){
        const upCnt = selectThis.parentElement.previousElementSibling.firstElementChild.value;
    
        fetch('/cart/updateCartCnt', { //요청경로
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            //컨트롤러로 전달할 데이터
            body: new URLSearchParams({
            // 데이터명 : 데이터값
            cartCnt : upCnt
            , cartCode : cartCode
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
            let resultprice = itemPrice * upCnt; // 새로운 가격
            const price = selectThis.closest('td').nextElementSibling;
            price.textContent ='₩' + resultprice.toLocaleString();
            setAllPrice();
            
        })
        //fetch 통신 실패 시 실행 영역
        .catch(err=>{
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
    }

}

// 장바구니 선택 상품 삭제
function deleteChk(){
    const chks = document.querySelectorAll('.chk:checked');
    if(chks.length == 0){ // 선택 상품 X
        alert('선택한 상품이 없습니다.');
        return;
    }
    const result = confirm('선택한 상품을 장바구니에서 삭제하시겠습니까?');
    if(result){
        //컨트롤러로 선택한 cardCode 여러개 넘기기
        const chkarr = [];
        for(const chk of chks){
        chkarr.push(chk.value);
        }

        location.href = `/cart/deleteChk?cartCodeList=${chkarr}`;
    }

}

function goBuy(){
    const chks = document.querySelectorAll('.chk:checked');
    if(chks.length == 0){ // 선택 상품 X
        alert('선택한 상품이 없습니다.');
        return;
    }

    const chkarr = [];
    for(const chk of chks){
       chkarr.push(chk.value);
    }
    
    location.href = `/buy/insertBuy?cartCodeList=${chkarr}`;
}
