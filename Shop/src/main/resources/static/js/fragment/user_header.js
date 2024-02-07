  //로그아웃
  function logout(){
    const result = confirm('로그아웃 하시겠습니까?'); //확인 > true 취소 > false
    
    if(result){
      location.href='/member/logout';
    }
  }
  
  //완료 버튼 클릭시 form 태그 submit
  function join(){
    // 0. 데이터의 유효성 검사
    // (ID 입력 유무 확인)
    const idInput = document.querySelector('#memberId');
    if (idInput.value == '') {
      alert('ID는 필수 입력 사항입니다.');
      return;
    }

    // (ID 입력 문자의 길이가 20 초과 유무 검사)
    if (idInput.value.length > 20) {
      alert('ID를 20글자 내로 작성하세요.');
      return;
    }

    // (비밀번호와 비밀번호확인 일치 유무 검사)
    const pwInputs = document.querySelectorAll('input[type="password"]')

    if (pwInputs[0].value != pwInputs[1].value) {
      alert('비밀번호가 일치하지 않습니다.\n다시 입력해주세요.');
      document.querySelector('#pw1').focus();
      return;
    }

    // (비밀번호 : 4글자~12글자 & 문자 + 숫자 + 특수문자 유무 검사)
    // const regExp = /^.*(?=^.{4,12}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/
    // const expResult = regExp.test(pwInputs[0].value);
    // if(!expResult){
    //   alert('비밀번호의 조건이 일치하지 않습니다.\n다시 입력해주세요.');
    //   document.querySelector('#pw1').focus();
    //   return;
    // }

    // 1. submit 시킬 form 태그 선택
    document.querySelector('#join-form').submit();
  }


  //주소 검색 팝업창 띄우기
  function searchAddress(){
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
            // 예제를 참고하여 다양한 활용법을 확인해 보세요.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

            document.querySelector('#postCode').value = data.zonecode;
            document.querySelector('#addressCode').value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.querySelector('#detailAddress').focus();
        }
    }).open();
  }

  //회원가입 모달 창이 닫힐 때 실행되는 이벤트
  const joinModal = document.querySelector('#join-modal');
  joinModal.addEventListener('hidden.bs.modal', function(){
    document.querySelector('#join-form').reset();
  })

  //로그인
  function login(){
    location.href="/member/login";
  }
