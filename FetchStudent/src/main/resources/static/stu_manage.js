// const selectTag = document.querySelector('#type');
//         selectTag.addEventListener('change',function(){
//             location.href = "/stu/main?classCode="+ document.getElementById("type").value;
//         })

function fetchSelect(){
    classCode = document.querySelector('#class-selector').value;
    fetch('/stu/fetchSelect', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
           classCode : classCode
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
        //기존 테이블 내용 삭제
        const tbodyTag = document.querySelector('#stu-list-table > tbody');
        
        tbodyTag.innerHTML = ''; //안에 빈값으로 바꾸겠다.
        
        //채우기
        let str = '';
        data.forEach(function(e, i){
            str += `<tr>
                        <td>${data.length - i}</td>
                        <td>${e.className}</td>
                        <td>${e.stuNum}</td>
                        <td><span onclick="selectScore(${e.stuNum})" id="name-span">${e.stuName}</span></td>
                    </tr>`
                });

        // for(const stu of data){    for-each 문
        //     str += `<tr>
        //                 <td>???</td>
        //                 <td>${stu.className}</td>
        //                 <td>${stu.stuNum}</td>
        //                 <td>${stu.stuName}</td>
        //             </tr>`;
        // }
        
        //         let str =`<tr>      일반 for 문
        //                 <td>${data.length - i}</td>
        //                 <td>${data[i].className}</td>
        //                 <td>${data[i].stuNum}</td>
        //                 <td>${data[i].stuName}</td>
        //         </tr>`;
        tbodyTag.insertAdjacentHTML('afterbegin', str);
        // }
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}

// 오른쪽 화면
function selectScore(stuNum){
    fetch('/stu/selectScore', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
           stuNum : stuNum
           
        })
    })
    .then((response) => {
        if(!response.ok){
            alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
            return ;
        }
    
        // return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
        return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!

        const divTag = document.querySelector('.stu-detail-div');

        divTag.innerHTML = '';

            let str = `
                <div>
                <h2>학생 기본 정보</h2>
                <div>
                    <table>
                        <thead>
                            <tr>
                                <td>학번</td>
                                <td>소속반</td>
                                <td>학생명</td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td class="stuNumTd">${data.stuNum}</td>
                                <td>${data.className}</td>
                                <td>${data.stuName}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div>
                <h2>학생 점수 정보</h2>
                <div>
                    <table>
                        <thead>
                            <tr>
                                <td>국어점수</td>
                                <td>영어점수</td>
                                <td>수학점수</td>
                                <td>평균</td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td class="scoreTd">${data.korScore}</td>
                                <td class="scoreTd">${data.engScore}</td>
                                <td class="scoreTd">${data.mathScore}</td>
                                <td>${((data.korScore + data.engScore + data.mathScore) / 3)
                                .toFixed(2)}</td>
                                // 소숫점 2자리까지만 고정
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div>
                    <input type="button" value="점수 입력" onclick="setInput()" class="scoreBt">
                </div>
            </div>
            `;

        divTag.insertAdjacentHTML('afterbegin', str);
        
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}

function setInput(){
    const tds = document.querySelectorAll('.scoreTd');
    const bt = document.querySelector('.scoreBt');

    if(bt.value == '점수 입력'){
                    //일반 for
    //for(let i = 0; i < tds.length; i++){
    //    tds[i].innerHTML = '<input type="text">'
    //}

                //for-each
    //for(const e of tds){
    //    e.innerHTML = '<input type="text">'
    //}

    //forEach(function(for-each의 e 역할 , 일반for의 i 역할))
    
    tds.forEach(function(e, i){
        e.innerHTML = '<input type="text" class="inputScore">'
    })
        
        //버튼 누른 후 버튼 안 글 변경
        const bt = document.querySelector('.scoreBt').value = '저장';
    }
    else if(bt.value == '저장'){
        const result = confirm('입력한 정보로 점수를 등록할까요?');
        if(result){    //result ▶ 확인 = true , 취소 = false
            insertScore();
        }
    }
    
}

function insertScore(){
    const inputs = document.querySelectorAll('.inputScore');
    const stuNum = document.querySelector('.stuNumTd').textContent;

    fetch('/stu/insertScore', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
           korScore : inputs[0].value
           , engScore : inputs[1].value
           , mathScore : inputs[2].value
           , stuNum : stuNum
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
        //alert('점수가 등록되었습니다.')
        selectScore(stuNum);
        
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}