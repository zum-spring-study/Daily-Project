# 1주차 
----

## 줌근마켓 : 동네 직거래 시스템 구현 ( 2.24 ~ 3.8 ) [ Feat. 라영지 ] 

Special Thanks to : Pir 

### 요구사항 

1. 동네 정보, 상품 정보, 직거래 시간, 상품 거래 목록을 데이터 베이스에 저장합니다.
2. 사용자는 동네와 직거래 시간을 선택 후 상품을 등록할 수 있습니다.
3. 사용자는 원하는 상품을 선택하여 구매할 수 있습니다. 상품거래가 완료되는 동안 다른 사용자는 해당 상품을 구매할 수 없습니다.
4. 사용자는 동일한 직거래 시간에 여러 상품을 구매할 수 없습니다.
5. 사용자는 자신이 거래 또는 판매한 상품 목록을 조회할 수 있습니다.

### 구현해야 할 기능 리스트 

- 구매 또는 판매한 상품 조회
- 전체 판매되고있는 상품 조회
- 상품 구매 - 이미 동일한 거래 시간에 상품 구매 예정이 되어있다면 다른 상품은 구매가 불가
- 상품 판매 - 상품을 등록, 수정, 삭제 할 수 있음


# 2주차 
---

## 단독) 한국에 드디어 상륙한 캣츠! 티켓 1000장을 잡아라! ( 3.10 ~ 3.22 ) [ Feat. 김의빈 ] 

### 요구사항 
- 하나의 예매 서비스에 다량의 유저들이 접속한다는걸 가정합니다.
    - 유저들은 접속하는 환경이 다를겁니다 ( ex. 모바일 , 컴퓨터 ) 하나의 서버가 아닌 여러 서버를   가정합니다.
- 뮤지컬의 경우 (날짜는 무관) 3일동안 1000장의 티켓이 발행됩니다.
- 1000장의 수량이 떨어질 경우 1001번째 사람은 티켓을 구매할 수 없습니다.
- 티켓을 이미 구매한 사람은 사제기의 요소를 방지하기 위해 더이상의 티켓을 구매할 순 없습니다.
    - 티켓을 구매했을 경우 당일 뿐만이 아닌 다른날도 구매가 불가합니다.

# 3주차
---

## 줌콜센터의 빠른 조회, 어디까지 찾아봤니? [ Feat. 김민수 ]

줌콜센터는 고객 정보를 단일 MySQL 데이터베이스 테이블에 저장하고 있습니다.
고객 상담 시스템을 구축하여 고객이 전화로 문의할 경우, 콜센터 상담원이 고객 정보를 조회하여 신속한 대응이 가능하도록 합니다.
대용량 데이터 처리를 위해 줌콜센터는 데이터베이스 테이블의 구조를 최적화합니다. 예를 들어 필요한 인덱스를 적절하게 설정합니다.
대용량 데이터 조회를 위한 쿼리는 단일 테이블에서 직접 작성됩니다. 예를 들어, 특정 기간 동안의 고객 정보를 조회하는 쿼리는 WHERE 절과 ORDER BY 절을 사용하여 작성됩니다.
줌콜센터는 서버 캐시를 구성하여 데이터베이스 조회를 할 때 매번 데이터베이스에 접근하지 않도록 합니다. 서버 캐시를 활용하여 쿼리 처리 속도를 높여 고객 상담원의 대응 시간을 줄일 수 있습니다.

## 구현 시 참고 사항

- 인덱스: 인덱스는 테이블에서 특정 컬럼에 대한 검색을 빠르게 하기 위해 사용됩니다.
- 쿼리 조건: 쿼리에서 사용되는 조건은 데이터 조회 속도에 영향을 미칩니다.
- 데이터 유형: 데이터 유형도 쿼리 처리 속도에 영향을 미칩니다.
