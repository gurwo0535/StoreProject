# StoreProject

# 개발기간 2025-03-10 ~03-14

# 프로젝트 개요
* 예약 시스템을 위한 웹 페이지 구축
* 사용자는 식당에 대한 정보를 조회하고 예약을 할 수 있음
* 예약 정보를 관리할 수 있는 다양한 기능을 제공
* Spring Boot를 사용하여 백엔드를 구축
* Thymeleaf 템플릿을 사용하여 프론트엔드를 구현
  
# 역활별 기능
 - 관리자 : 식당 등록이 가능
 - 개인 : 식당 등록이 불가하며 예약만 가능

# 기능 정리 
1. 로그인 페이지
   - session 방식으로 id password를 데이터베이스와 비교 후 로그인 처리
2. 식당 등록
   -로그인시 role의 값 비교하여 1 - 관리자 , 2- 고객 으로 나누어 관리
     식당 등록 버튼 클릭시 관리자는 등록화면
     고객은 onclick alert으로 알림 표시
   - 정보를 입력받으면 storeList로 이동
3. 식당 리스트 페이지
   - 식당 등록에서 입력받은 정보를 표시
   - 클릭시 예약 페이지로 이동 
4. 예약 페이지
   - 식당을 클릭하여 예약 정보 입력 가능
   - 전화번호와 예약 날짜 시간을 DateTime 방식으로 저장
   - 예약 정보는 Spring Data JPA를 통해 데이터베이스에 저장
   - 값을 입력 받으면 myPage로 이동 
5. 마이페이지
   - 입력 받은 식당정보의 수정, 삭제 기능 구현, 데이터베이스에 저장 

# 데이터베이스 테이블 구조
1. user table
id 	varchar(255)	pk	
password	varchar(255)
phone_nm	varchar(255)	
role	int(11)	
user_name	varchar(255) fk

2. storer
id int(11) pk
title	varchar(255)
description	varchar(255)
photo	varchar(255)
store_phone	varchar(255)
address	varchar(255)
created_time	date
updated_time	date
price	int(11)
business_hours	varchar(255)
user_id 	varchar(255) fk

3. reservation
id 기본	int(11) pk
rese_day	datetime
store_name	varchar(255)	
user_id 	varchar(255) fk
storer_id 	int(11) fk

7. 테이블 키(조인)정보
 - storer - userId
 - reservation - userId
 - reservation - storer
 - 
9. 기능별 영상 (각 권한마다 동작 기능 영상) : 반디캠 활용





  
