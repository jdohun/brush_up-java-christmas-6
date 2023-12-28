# 구현 목록 명세

## domain

### model

- ### classes
    - ### ExpectedVisitDay
        - 멤버
            - `LocalDate date`

        - 메소드
            - 정적 팩토리 메소드
                - [x] 정수(dayOfMonth)를 입력받아서 검증 후 `LocalDate`로 저장한다.

            - 검증 메소드
                - [x] 전달받은 정수가 1 이상 31 이하의 범위를 벗어난다.
                    - 위 조건에 해당하면 `IllegalArgumentException` 예외를 발생시킨다.

                - 기능 메소드
                    - 함수형 인터페이스를 이용한 기능
                        - [x] 저장된 날짜가 이벤트 기간을 충족 여부를 반환한다.
                        - [x] 저장된 날짜를 이용한 혜택을 계산 결과를 반환한다.
                    - [x] 저장된 날짜를 `DTO`로 변환해 반환한다.

    - ### OrderInfo
        - 멤버
            - `EunmMap<Menu, Integer> orderInfo`

        - 메소드
            - 정적 팩토리 메소드
                - `EunmMap<Menu, Integer> orderInfo`을 입력바아서 검증 후 저장한다.
            - 검증 메소드
                - [x] 주문한 메뉴의 총 수량이 20개 초과한다.
                    - (e.g. 시저샐러드-1, 티본스테이크-1, 크리스마스파스타-1, 제로콜라-3, 아이스크림-1의 총개수는 7개)
                - [x] 주문한 모든 메뉴의 카테고리가 음료이다.
            - 검증 실패 시 `IllegalArgumentException` 예외를 발생시킨다.

            - 기능 메소드
                - [x] 총 주문 금액을 계산한다.
                - [x] 주문된 메인 메뉴의 수량을 계산한다.
                - [x] 주문된 디저트 메뉴의 수량을 계산한다.

        - ### DecemberEventPlan
            - 멤버
                - `ExpectedVisitDay expectedVisitDay`
                - `OrderInfo orderInfo`

            - 메소드
                - 기능 메소드
                    - [x] `OrderInfo`로부터 계산된 총금액을 반환한다.
                    - [x] `OrderInfo`로부터 계산된 메인 메뉴의 개수를 반환한다.
                    - [x] `OrderInfo`로부터 계산된 디저트 메뉴의 개수를 반환한다.
                    - [x] `OrderInfo`로부터 반환된 DtoList 를 반환한다.
                    - [x] `ExpectedVisitDay`로부터 반환된 Dto 를 반환한다.

                - 함수형 인터페이스를 이용한 기능
                    - [x] 구현된 `DateCheckStrategy`를 전달하여 기간 충족 여부를 반환한다.
                    - [x] 구현된 `DateBasedDiscountStrategy`를 전달하여 계산된 혜택을 반환한다.

        - ### DiscountInfos
            - 멤버
                - `List<DiscountInfo> discountInfoList`

                    - 메소드
                        - 기능 메소드
                            - [x] `List<DiscountInfo>`로부터 `TotalDiscountAmount`를 생성하여 반환한다.

        - ### GiveawayInfos
            - 멤버
                - `List<GiveawayInfo> giveawayInfoList`

                    - 메소드
                        - 기능 메소드
                            - [x] `List<GiveawayInfo>`로부터 `TotalGiveawayAmount`를 생성하여 반환한다.

- ### enums
    - ### menu
        - ### MenuCategory
            - 메뉴 카테고리를 상수로 저장

        - ### Menu
            - 메소드
                - 기능 메소드
                    - 정적
                        - [x] 이름을 통해 메뉴의 존재 여부를 반환한다.
                        - [x] 이름을 통해 메뉴를 반환한다.

                    - 인스턴스
                        - [x] 메뉴의 카테고리가 음료인지 여부를 반환한다.
                        - [x] 메뉴의 카테고리가 메인인지 여부를 반환한다.
                        - [x] 메뉴의 카테고리가 디저트인지 여부를 반환한다.
                        - [x] 메뉴의 이름을 반환한다.
                        - [x] 메뉴의 가격을 반환한다.

    - ### Giveaway
        - 메소드
            - 기능 메소드
                - [x] 증정 상품의 이름을 반환한다.
                - [x] 증정 상품의 가격을 반환한다.

    - ### Badge
        - 총혜택 금액에 따라 다른 이벤트 배지를 부여합니다.
            - 5천 원 이상: 별
            - 1만 원 이상: 트리
            - 2만 원 이상: 산타

        - 메소드
            - 정적
                - `TotalBenefitAmount`를 통해 조건을 만족하는 `Badge`를 `Optional`로 반환합니다.
                    - 만족하는 조건이 없으면 `Optional.empty()` 반환

            - 인스턴스
                - 총혜택 금액의 조건 만족 여부를 반환합니다.
                - `Badge`의 이름을 반환합니다.

### promotion

- ### strategy
    - ### dateCheckStrategy
        - [x] 전달받은 `DecemberEventPlan`에 구현한 `DateCheckStrategy` 메소드를 전달하여 조건 만족 여부를 반환한다.
            - 요일 조건
            - 기간 조건
            - 특정 날짜 조건
    - ### discountStrategy
        - [x] 전달받은 `DecemberEventPlan`에 구현한 `DiscountStrategy` 메소드를 전달하여 조건 만족 여부를 반환한다.
            - 날짜 기반
            - 메뉴 개수 기반
    - ### totalAmountCheckStrategy
        - [x] `DecemberEventPlan`로부터 계산된 `TotalAmount`의 미리 저장한 `TotalAmountCondition` 최소금액 만족 여부를 반환한다.

- ### context
    - ### discount
        - ### DiscountPromotion
            - ### ChristmasPromotionPrecondition
                - 이벤트 참여 전제 조건 만족 여부를 반환한다.
                    - 이벤트 기간: `크리스마스 디데이 할인` 을 제외한 다른 이벤트는 2023.12.1 ~ 2023.12.31 동안 적용
                    - 총주문 금액 10,000원 이상부터 이벤트가 적용됩니다.

            - ### ChristmasDDayPromotion
                - 이벤트 참여 전제 조건을 만족함에 따라 할인을 적용한다.
                    - 이벤트 기간: 2023.12.1 ~ 2023.12.25
                - 1,000원으로 시작하여 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 증가
                - 총주문 금액에서 해당 금액만큼 할인
                    - (e.g. 시작일인 12월 1일에 1,000원, 2일에 1,100원, ..., 25일엔 3,400원 할인)

            - ### WeekdayPromotion
                - 평일(일요일~목요일)에는 디저트 메뉴를 메뉴 1개당 2,023원 할인

            - ### WeekendPromotion
                - 주말(금요일, 토요일)에는 메인 메뉴를 메뉴 1개당 2,023원 할인

            - ### SpecialDayPromotion
                - 특별 할인: 이벤트 달력에 별(3,10,17,24,25,31)이 있는 날에 예약을 하면 총주문 금액에서 1,000원 할인

    - ### giveaway
        - ### GiveawayPromotion
            - ### GiveawayByTotalAmountPromotion
                - 할인 전 총주문 금액이 12만 원 이상일 때, 샴페인 1개 증정

- ### enums
    - ### PromotionName
        - 적용된 이벤트 정책이 출력될 헤더 문자열을 열겨형으로 관리한다.

    - ### DiscountPromotionType
        - 구현한 `DiscountPromotion`을 열거형으로 관리한다.

    - ### GiveawayPromotionType
        - 구현한 `GiveawayPromotion`을 열거형으로 관리한다.

---

## DTO

- ### Dto
    - ### ChristmasPromotionPreview
        - ExpectedVisitDayDto
        - MenuAndQuantityDto
        - TotalAmount

        - DiscountInfo
        - GiveawayInfo

        - TotalDiscountAmount
        - TotalGiveawayAmount
        - TotalBenefitAmount

---

## 어플리케이션 서비스

### Controller

- ### DecemberEventPlanController
    - 입력된 정보를 통해 `DecemberEventPlan`을 반환한다.
        - [x] 입력된 예상 방문 날짜를 저장한다.
        - [x] 입력된 주문 메뉴를 저장한다.

- ### ChristmasPromotionController
    - [x] `DecemberEventPlan`을 `ChristmasPromotion`에 적용한다.
    - [x] 프로모션 적용을 통해 도출된 결과를 `ChristmasPromotionPreview`로 저장한다.
    - [x] 저장된 `ChristmasPromotionPreview`를 출력한다.

### parser

- ### InputDayOfMonthParser
    - 입력된 값을 파싱하여 정수로 변환한다.
        - 파싱 전 한 자리 또는 두 자리 정수인지 검증한다.
            - 검증 실패 시 `IllegalArgumentException` 예외를 발생시킨다.

- ### InputOrderParser
    - 입력된 값을 파싱하여 OrderInfo 에 저장할 수 있는 형태로 변환한다.
        - 검증 후 멤버 자료구조와 동일하도록 Mapping 한다.
            - 검증과 Split
                - [x] 주문 형식을 검증한다.
                - [x] 검증을 통과한 주문을 `주문 구분자`로 split 한다.
            - Split 한 주문 아이템의 형식을 검증한다.
                - [x] 주문 아이템의 형식이 `한글 문자열` `-` `1이상의 정수` 가 아니다. (주문 수량이 0개인 경우를 동시에 처리)
            - 주문 아이템 형식 검증을 통과한 주문 아이템 리스트를 Mapping 과 동시에 검증한다.
                - [x] 입력된 주문 메뉴가 존재하지 않는다.
                - [x] 중복된 주문 메뉴가 존재한다.
                    - 검증 실패 시 `IllegalArgumentException` 예외를 발생시킨다.

### UI(View)

- ### InputView
    - [x] 예상 방문 날짜를 입력받는다.
    - [x] 주문 메뉴를 입력받는다.

    - ### InputValidator
        - [x] null 이면 `IllegalArgumentException` 예외를 발생시킨다.
        - [x] 빈 문자열 이면 `IllegalArgumentException` 예외를 발생시킨다.

- ### OutputView
    - [x] 인사말을 출력한다.

    - ### Preview
        - 예상 날짜와 주문 메뉴를 통해 받을 수 있는 이벤트 혜택 미리 보기(`ChristmasPromotionPreview`)를 출력한다.
            - [x] 미리보기 타이틀을 출력한다.
            - [x] 타이틀과 함께 주문 메뉴를 출력한다.
                - 순서 상관 없음
            - [x] 타이틀과 함께 할인 전 총주문 금액을 출력한다.
            - [x] 타이틀과 함께 증정 메뉴를 출력한다.
            - [x] 타이틀과 함께 혜택 내역을 출력한다.
                - 순서 상관 없음
            - [x] 타이틀과 함께 총혜택 금액을 출력한다.
            - [x] 타이틀과 함께 할인 후 예상 결제 금액을 출력한다.
            - [x] 타이틀과 함께 부여될 12월 이벤트 배지를 출력한다.
        
- ### ErrorView
    - ### IllegalArgumentErrorView
        - [x] ErrorMessagePrefix 를 정적 변수로 저장한다.
        - [x] `IllegalArgumentException`을 전달받아 메시지를 출력한다.
            - [x] 저장한 접두사를 메시지를 출력할 때마다 앞에 붙인다.
