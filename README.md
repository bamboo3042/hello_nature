# 🍀 Hello Nature 🍀
  
## 프로젝트 개요
  * 온라인 푸드테크 기업 헬로네이처의 웹사이트 제작

## 프로젝트 구성원
  * 김민지
  * 김성용
  * 박소민
  * 이수지
  * 이예솔
  * 정영훈

## 프로젝트 목적 및 필요성

  - 헬로네이처 웹사이트는 앱에 최적화 되어있어 웹을 이용하는 소비자에게 불편을 줄 수 있음. 따라서 소바자가 웹 사이트를 
  이용하면서 겪을 불편함을 고려하여 앱에 최적된 사이트를 웹버전으로 개발 하였음 

## 프로젝트 진행과정

  * 스토리 보드 
  * 테이블정의서
  * API문서
  * 디자인 및 퍼블리싱
  * 더미데이터를 활용한 프론트 서버 및 백엔드 서버 구현
  * 오류 점검 및 최종 코드 수정
  * 발표
  
## 프로젝트 활용기술

`frontend`
	<img src="https://img.shields.io/badge/HTML5-E34F26.svg?style=for-the-badge&logo=HTML5&logoColor=white"/>
	<img src="https://img.shields.io/badge/CSS-1572B6.svg?style=for-the-badge&logo=CSS3&logoColor=white"/>
	<img src="https://img.shields.io/badge/JavaScript-F7DF1E.svg?style=for-the-badge&logo=JavaScript&logoColor=black"/>
	![Thymeleaf](https://img.shields.io/badge/Thymeleaf-%23005C0F.svg?style=for-the-badge&logo=Thymeleaf&logoColor=white)
	![jQuery](https://img.shields.io/badge/jquery-%230769AD.svg?style=for-the-badge&logo=jquery&logoColor=white)
	<img src="https://img.shields.io/badge/axios-black.svg?style=for-the-badge&logo=axios&logoColor=white"/>

`backend`
	<img src="https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot"/>
	<img src="https://img.shields.io/badge/JPA-6DB33F.svg?style=for-the-badge&logo=spring-boot&logoColor=black"/>
	<img src="https://img.shields.io/badge/Spring Security-6DB33F.svg?style=for-the-badge&logo=spring-security&logoColor=white"/>
   
`database`
	<img src="https://img.shields.io/badge/Oracle-F80000.svg?style=for-the-badge&logo=Oracle&logoColor=white"/>


  ## 프로젝트 데이터 관계도
  
사진
  
  ## 프로젝트 담당 및 역할
  
`frontend` 마이페이지, 어드민 사이트 제작 및 유저 사이트 총괄
`backend` 파일업로드, ck에디터업로드, 레시피 CRUD API, 핸드폰인증, 이메일인증, sprig securiry를 활용한 로그인 구현
    
  ## 프로젝트 구현기능
  
`ADMIN`
* 전체 상품 관리
     * 상품 카테고리 관리
         * 상품을 카테고리(1차 카테고리), 세부 카테고리(2차 카테고리)로 등록.    
         * 상품 관리
            * 상품을 등록
            * 상품조회 : 판매상태 / 카테고리 / 상품명을 검색하여 등록된 상품을 검색하고 조회할 수 있도록 함. 
         * 주문 관리
            * 주문관리 조회 : 구매일자 / 주문상태 / 주문번호를 검색하고 고객이 주문한 건을 검색하고 조회할 수 있도록 함. 
         * 상품 문의
            * 상품문의 조회 : 문의일자 / 답변여부/ 검색어(작성자 / 내용)를 검색하여 고객의 상품 문의건을 검색하고 조회할 수 있도록 함.  
         * 상품 후기 
            * 상품후기 조회 : 상품명을 검색하여 고객이 남긴 후기를 조회할 수 있도록 함.
              
* 매거진 관리
    * 카테고리 관리
         * 매거진 카테고리 등록 
     * 매거진 목록
         * 매거진 목록 조회 : 카테고리 / 매거진명 / 등록기간을 검색하여 조회할 수 있도록 함.
     * 매거진 등록
         * 매거진(탐험노트, 라이프스타일, 키친가이드) 등록 
      * 레시피 등록
         * 레시피 등록 

* 헬로네이처 추천관리
     * 팝업스토어
          * 팝업스토어 등록 / 수정 
     * 기획전/이벤트
     * 브랜드관
          * 브랜드 등록 / 수정
          * 브랜드 조회 : 브랜드명 / 입접상태 / 입점기간을 검색하여 브랜드 목록을 조회할 수 있도록 함.
     
* 고객센터
     * 공지사항 관리
          * 공지사항 등록
          * 공지사항 조회 : 제목 또는 분류기준 또는 기간을 검색 후 목록을 조회할 수 있도록 함.  
      * 결제 내역 관리
         * 결제내역 조회 : 결제일 또는 고객번호를 검색 후 목록을 조회할 수 있도록 함.
      * 1:1 문의 내역
         * 1:1 문의 조회 : 등록일 또는 답변상태 / 문의유형 또는 회원 이메일을 검색 후 목록을 조회할 수 있도록 함.   
      * FAQ 관리
         * FAQ 등록
         * FAQ 조회 : FAQ 제목을 검색 후 목록을 조회할 수 있도록 함.      
      * 회원 관리
         * 회원조회 : 가입일 또는 이메일 / 이름 / 연락처를 검색 후 회원목록을 조회할 수 있도록 함.
         * 회원정보 수정 
      * 쿠폰 관리
         * 쿠폰 등록
         * 쿠폰조회 : 쿠폰명 또는 기간을 검색 후 쿠폰목록을 조회할 수 있도록 함. 
      * 주소지 관리 
         * 회원 주소지 조회 : 회원 이메일을 검색하여 조회할 수 있도록 함.
         * 회원 주소지 수정 

`USER`
      * 로그인
      * 회원가입
         * 핸드폰인증, 이메일인증
      * 장바구니
          * 품구매, 결제
      * 상품조회
          * 상품상세, 장바구니  
      * 마이페이지
          * 주문내역
            - 주문내역 및 주문상태 확인
  * 배송지 관리
      - 배송지 등록, 배송지 수정, 배송지 삭제
  * 구매 후기
      - 구매후기 등록, 답변 확인
  * 더그린배송
      - 더그린 배송 신청 및 해지
  * 상품 문의
      - 상품 문의 답변 확인
  * 1:1 문의 
      - 1:1 문의 등록 및 답변 확인
  * 개인정보수정 
     - 이메일 찾기
     - 비밀번호 찾기
             
  ## 프로젝트 진행상세
  
* 스토리보드 제작
    (사진)
    - UI/UX 디자인부터 시작하여 요구사항에 맞는 스토리보드 제작
* 테이블정의서
    (사진)
    - 짜여진 스토리 보드를 토대로 데이터베이스에 필요한 테이블과 컬럼을 정의
* API문서
    (사진)
    - 필요한 API 정리 및 요청 URL 및 응답 예상 메시지 
* 디자인 및 퍼블리싱
    (사진)
* 더미데이터를 활용한 프론트 서버 및 백엔드 서버 구현
    (사진)
	  (사진)
	  - 깃을 활용한 협업
	  (사진)
     - 포스트맨을 활용한 API 요청과 응답 확인
	구현 중 문제점
	-검색
	-선택삭제
	-이미지 업로드 rebuild 문제

	-레시피
	-관련상품
	-장바구니

      해결방안
	jsql
	array		

	create
	js
	session storege            
* 오류 점검 및 최종 코드 수정
* 발표
    (발표영상 짧게)
  
  ## 프로젝트 시연영상

* 시연연상

  ## 프로젝트를 마치며...
  
* 느낀점


