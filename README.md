#### Spring Lv.3
### 스파르타 백오피스(Admin) 서버 만들기

🚩 요구사항 확인: 필수 구현 기능

1. 관리자 가입 기능
    - `이메일`, `비밀번호`, `부서`, `권한`을 저장할 수 있습니다.
        - 커리큘럼, 마케팅, 개발 `부서`가 있습니다.
        - MANAGER, STAFF `권한`이 있습니다.
            - 커리큘럼, 개발 `부서`만 MANAGER 권한을 부여 받을 수 있습니다.
        - `이메일`은  `올바른 이메일 형식`을 지켜야 합니다.
        - `비밀번호`는  `최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9), 특수문자`로 구성되어야 합니다.
    - 관리자가입 성공을 확인할 수 있는 값을 반환합니다.
        - ex) HTTP Status Code, Error Message …
    
2. 로그인 기능
    - 관리자는 `이메일`, `비밀번호`를 입력하여 서버에 로그인을 요청할 수 있습니다.
    - 로그인 성공 시, `회원의 정보`와 `JWT`를 활용하여 토큰을 발급하고,
    발급한 토큰을 Header에 추가한 후 로그인 성공을 확인할 수 있는 값과 함께 반환합니다.
        - ex) HTTP Status Code, Error Message …
        
3. 강사 등록 기능
    - `이름`, `경력(년차)`, `회사`, `전화번호`, `소개`를 저장할 수 있습니다.
        - 로그인을 통해 발급받은 JWT가 함께 요청됩니다.
        - 관리자만 강사 등록이 가능합니다.
    - 등록된 강사의 정보를 반환 받아 확인할 수 있습니다.

4. 선택한 강사 정보 수정 기능
    - 선택한 강사의 `경력`, `회사`, `전화번호`, `소개`를 수정할 수 있습니다.
        - 로그인을 통해 발급받은 JWT가 함께 요청됩니다.
        - MANAGER  권한을 가진 관리자만 강사 정보 수정이 가능합니다.
    - 수정된 강사의 정보를 반환 받아 확인할 수 있습니다.

5. 강의 등록 기능
    - `강의명`, `가격`, `소개`, `카테고리`, `강사`, `등록일`을 저장할 수 있습니다.
        - Spring, React, Node `카테고리`가 있습니다.
        - 강사 한 명이 여러 개의 강의를 촬영할 수도 있습니다.
        - 로그인을 통해 발급받은 JWT가 함께 요청됩니다.
        - 관리자만 강의 등록이 가능합니다.
    - 등록된 강의의 정보를 반환 받아 확인할 수 있습니다.

6. 선택한 강의 정보 수정 기능
    - 선택한 강의의 `강의명`, `가격`, `소개`, `카테고리`를 수정할 수 있습니다.
        - 로그인을 통해 발급받은 JWT가 함께 요청됩니다.
        - MANAGER  권한을 가진 관리자만 강의 정보 수정이 가능합니다.
    - 수정된 강의의 정보를 반환 받아 확인할 수 있습니다.

7. 선택한 강사 조회 기능
    - 선택한 강사의 정보를 조회할 수 있습니다.
        - 로그인을 통해 발급받은 JWT가 함께 요청됩니다.
        - 관리자만 강사 조회가 가능합니다.

8. 선택한 강의 조회 기능
    - 선택한 강의의 정보를 조회할 수 있습니다.
        - 로그인을 통해 발급받은 JWT가 함께 요청됩니다.
        - 관리자만 강의 조회가 가능합니다.

9. 선택한 강사가 촬영한 강의 목록 조회 기능
    - 선택한 강사가 촬영한 강의를 조회할 수 있습니다.
        - 로그인을 통해 발급받은 JWT가 함께 요청됩니다.
        - 관리자만 강의 조회가 가능합니다.
    - 조회된 강의 목록은 `등록일` 기준 내림차순으로 정렬 되어있습니다.
    
10. 카테고리별 강의 목록 조회 기능
    - 선택한 카테고리에 포함된 강의를 조회할 수 있습니다.
        - 로그인을 통해 발급받은 JWT가 함께 요청됩니다.
        - 관리자만 강의 조회가 가능합니다.
    - 조회된 강의 목록은 `등록일` 기준 내림차순으로 정렬 되어있습니다.
  

<img width="829" alt="과제3_다이어그램" src="https://github.com/ayoung-jeon/third_week_lv3/assets/147483798/8fc8b575-3ecf-48f5-9135-ac4aded9ee4e">


<img width="819" alt="과제3_API1" src="https://github.com/ayoung-jeon/third_week_lv3/assets/147483798/e3fb9ba7-b7f2-4d4c-bfad-38a70229fe55">
<img width="816" alt="과제3_API2" src="https://github.com/ayoung-jeon/third_week_lv3/assets/147483798/597e3657-39f0-4bdf-a102-27cdaf7890ac">
<img width="817" alt="과제3_API3" src="https://github.com/ayoung-jeon/third_week_lv3/assets/147483798/9cef2405-d84b-455f-8023-cdb6a8f1ba1c">
<img width="818" alt="과제3_API4" src="https://github.com/ayoung-jeon/third_week_lv3/assets/147483798/b920bb47-3e6c-401d-bb0b-b995d8605167">


<img width="621" alt="과제3_ERD" src="https://github.com/ayoung-jeon/third_week_lv3/assets/147483798/c9fd7114-70c8-4124-8817-736ff95dc0f7">

