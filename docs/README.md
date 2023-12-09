# 구현 목록 명세

## domain
### model
- ### classes
  - ### ExpectedVisitDay
      - 멤버
          - `LocalDate date`

      - 메소드
          - 검증 메소드
              - [ ] 1 이상 31 이하의 정수

          - 기능 메소드
              - 기능

          - 스탠다드 메소드
              - 기능

  - ### Order
      - 멤버
          - `Map<Menu, Integer> order`

      - 메소드
          - 검증 메소드
              - 기능

          - 기능 메소드
              - 기능

          - 스탠다드 메소드
              - 기능

  - ### DecemberEventPlan
      - 멤버
          - `ExpectedVisitDay expectedVisitDay`
          - `Order order`

      - 메소드
          - 검증 메소드
              - 기능

          - 기능 메소드
              - 기능

          - 스탠다드 메소드
              - 기능

- ### enums
  - ### MenuCategory
 
  - ### Menu
      - 메소드
          - 검증 메소드
              - 기능

          - 기능 메소드
              - 기능

          - 스탠다드 메소드
              - 기능

  - ### Badge
      - 총혜택 금액에 따라 다른 이벤트 배지를 부여합니다.
        - 5천 원 이상: 별
        - 1만 원 이상: 트리
        - 2만 원 이상: 산타

      - 메소드
          - 검증 메소드
              - 기능

          - 기능 메소드
              - 기능

          - 스탠다드 메소드
              - 기능

### policy
- ### EventApplyPolicy
    - 이벤트 기간: `크리스마스 디데이 할인` 을 제외한 다른 이벤트는 2023.12.1 ~ 2023.12.31 동안 적용
    - 총주문 금액 10,000원 이상부터 이벤트가 적용됩니다.
    - 음료만 주문 시, 주문할 수 없습니다.
    - 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.
      - (e.g. 시저샐러드-1, 티본스테이크-1, 크리스마스파스타-1, 제로콜라-3, 아이스크림-1의 총개수는 7개)

- ### ChristmasDDayDiscountPolicy
    - 이벤트 기간: 2023.12.1 ~ 2023.12.25
    - 1,000원으로 시작하여 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 증가
    - 총주문 금액에서 해당 금액만큼 할인
      - (e.g. 시작일인 12월 1일에 1,000원, 2일에 1,100원, ..., 25일엔 3,400원 할인)
    
- ### WeekdayDiscountPolicy
    - 평일(일요일~목요일)에는 디저트 메뉴를 메뉴 1개당 2,023원 할인

- ### WeekendDiscountPolicy
    - 주말(금요일, 토요일)에는 메인 메뉴를 메뉴 1개당 2,023원 할인

- ### SpecialDayDiscountPolicy
    - 특별 할인: 이벤트 달력에 별(3,10,17,24,25,31)이 있으면 총주문 금액에서 1,000원 할인  

- ### GiveawayEventPolicy
    - 할인 전 총주문 금액이 12만 원 이상일 때, 샴페인 1개 증정
---

## DTO
- ### Dto
  - ### record
    - ### `OrderMenuDto(Menu menu, int quantity)`

---

## 어플리케이션 서비스
### Controller
- ### ChristmasPromotionController
  - [ ] 기능

### 공용 (util)
- ### ErrorMessagePrefix

### Handler
- ### InputHandler
    - ### parseExpectedVisitDay
        - 입력받은 `예상 방문 날짜`의 형식을 검증한다.
            - [ ] null 을 입력한다.
            - [ ] 빈 문자열(공백) 을 입력한다.
            - [ ] 공백으로 시작하거나 끝난다.
            - [ ] 정수가 아니다.
            - 
                - 위 조건에 해당하면 `IllegalArgumentException` 예외 처리한다. 

    - ### parseOrderList
        - 입력받은 `주문 메뉴`의 형식을 검증한다.
            - [ ] null 을 입력한다.
            - [ ] 빈 문자열(공백) 을 입력한다.
            - [ ] 공백으로 시작하거나 끝난다.
            - [ ] 주문 메뉴와 수량의 구분이 하이픈(-)이 아니다.
            - [ ] 주문 메뉴 간 구분이 쉼표(,)가 아니다.
              - 위 조건에 해당하면 `IllegalArgumentException` 예외 처리한다.

### UI(View)
- ### InputView
  - [ ] 예상 방문 날짜를 입력받는다.
  - [ ] 주문 메뉴를 입력받는다.
  
  - ### InputValidator
    - [ ] null 이면 `IllegalArgumentException` 예외 처리한다.
    - [ ] 빈 문자열 이면 `IllegalArgumentException` 예외 처리한다.
    - [ ] 문자열의 시작 또는 끝이 공백이면 `IllegalArgumentException` 예외 처리한다.

- ### OutputView
  - 예상 날짜와 주문 메뉴를 통해 받을 수 있는 이벤트 혜택 미리 보기를 출력한다.
    - [ ] 주문 메뉴를 출력한다.
      - 순서 상관 없음
    - [ ] 할인 전 총주문 금액을 출력한다.
    - [ ] 증정 메뉴를 출력한다.
    - [ ] 혜택 내역을 출력한다.
        - 순서 상관 없음
    - [ ] 총혜택 금액을 출력한다.
    - [ ] 할인 후 예상 결제 금액을 출력한다.
    - [ ] 부여받을 12월 이벤트 배지를 출력한다. 