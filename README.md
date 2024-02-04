# 지하철 노선도 미션

[ATDD 강의](https://edu.nextstep.camp/c/R89PYi5H) 실습을 위한 지하철 노선도 애플리케이션

---

## 🚀 0단계 - 리뷰 사이클 연습

### Todo

- [x] "https://google.com"로 요청을 보낼 경우 200응답 코드가 오는지를 검증하는 테스트를 작성
    - Response 객체로 검증
    - .statusCode()로 검증

---

## 🚀 1단계 - 지하철역 인수 테스트 작성

### Todo

- [x] 지하철역 인수 테스트 완성하기
    - [x] 지하철역 목록 조회 인수 테스트 작성하기
    - [x] 지하철역 삭제 인수 테스트 작성하기

### 프로그래밍 요구사항

- [x] 인수 테스트의 재사용성과 가독성, 빠른 테스트 의도 파악을 위해 인수 테스트 리팩터링

### Feedback 24.01.27

- [x] 지하철역 생성 메서드는 앞으로 자주 사용 예정으로, 함수로 추출해 볼 것

---

## 🚀 2단계 - 지하철 노선 관리

### Todo

- 인수 조건을 기반으로 지하철 노선 관리 기능 구현
- [x] 지하철 노선 생성
- [x] 지하철 노선 목록 조회
- [x] 지하철 노선 조회
- [x] 지하철 노선 수정
- [x] 지하철 노선 삭제

### 인수 조건

- [x] 지하철 노선 생성
    - When 지하철 노선을 생성하면 Then 지하철 노선 목록 조회 시 생성한 노선을 찾을 수 있다.
- [x] 지하철 노선 목록 조회
    - Given 2개의 지하철 노선을 생성하고 When 지하철 노선 목록을 조회하면 Then 지하철 노선 목록 조회 시 2개의 노선을 조회할 수 있다.
- [x] 지하철 노선 조회
    - Given 지하철 노선을 생성하고 When 생성한 지하철 노선을 조회하면 Then 생성한 지하철 노선의 정보를 응답받을 수 있다.
- [x] 지하철 노선 수정
    - Given 지하철 노선을 생성하고 When 생성한 지하철 노선을 수정하면 Then 해당 지하철 노선 정보는 수정된다.
- [x] 지하철 노선 삭제
    - Given 지하철 노선을 생성하고 When 생성한 지하철 노선을 삭제하면 Then 해당 지하철 노선 정보는 삭제된다.

### 프로그래밍 요구사항

- [x] 인수 테스트 작성, 인수 테스트 충족하는 기능 구현의 순서로 진행
- [x] 인수 테스트간에 영향을 끼치지 않도록 테스트 격리
- [x] 인수 테스트 리팩터링 진행 (중복 코드 처리)

### Feedback 24.01.30

- [x] 추후 노선에 종속된 기능에 대한 인수테스트 작성 시 기능별로 분리한 메서드를 클래스를 분리하여 재사용성을 높일 수 있다.
- [x] 이전 단계에서 진행한 단계까지 포함하여 전체 인수테스트 시 지하철역 목록 조회 기능 실패 이슈
    - 지하철 역 테스트에서 id 값으로 검증하도록 수정
    - 지하철 노선 테스트에서 setUp() 메서드 삭제, 각 테스트별로 진행 후 truncate table 하도록 수정

---

## 🚀 3단계 - 지하철 구간 관리

### 기능 요구사항

- [x] 요구사항 정의 후 인수 조건 도출
- [x] 인수 조건을 검증하는 __인수 테스트__ 작성
- [x] 예외 케이스 검증 포함

### 프로그래밍 요구사항

- [x] 인수 테스트 주도 개발 프로세스에 맞춘 기능 구현
    - 인수 조건 정의 - 인수 테스트 작성 - 기능 구현
- [x] 인수 테스트 상단에 인수 조건 주석 작성
- [x] 인수테스트 격리
- [ ] 인수 테스트 리팩터링

### Todo

- [x] 노선 구간 등록
  - [x] 새 구간의 상행역은 노선에 등록되어 있는 하행 종점역이 된다.
  - [x] 이미 등록되어 있는 역은 새로운 구간의 하행역이 될 수 없다.
  - [x] 위 조건에 부합하지 않는 경우 에러 처리
- [x] 노선 구간 조회
  - 라인 id로 조회하면 해당 라인과 라인의 구간정보를 함께 보여준다.
- [x] 노선 구간 제거
  - [x] 기존에 등록된 하행 종점역만 제거할 수 있다. (stationId 1개를 받는다.)
  - [x] 노선에 상행 종점역과 하행 종점역만 있다면 삭제할 수 없다.
  - [x] 위 조건에 부합하지 않는 경우 에러 처리

### 인수 조건
- [x] 노선 구간 등록
  - given 1개의 지하철 노선을 등록하고
  - when 1개의 지하철 구간을 추가 등록하면
  - then 해당 지하철 노선의 구간 조회 시 생성한 구간을 찾을 수 있다.
- [x] 이미 등록된 구간 등록 예외 검증
  - given 지하철 노선을 생성 후 1개의 구간을 더 등록하고
  - when 이미 등록된 역을 등록하면
  - then 구간 등록 에러가 발생한다.
- [x] 구간 등록 시 하행 종점역이 아닌 역을 상행역으로 등록 예외 검증
  - given 지하철 노선을 생성 후
  - when 하행 종점역이 아닌 역을 상행선으로 등록하려고 하면
  - then 구간 등록 에러가 발생한다.
- [x] 노선 구간 제거
  - given 2개의 지하철 구간을 등록하고
  - when 1개의 구간을 삭제하면
  - then 지하철 노선 조회 시 1개의 구간만 노출된다.
- [x] 하행 종점역이 아닌 역 제거 예외 검증
  - given 2개의 지하철 구간을 등록하고
  - when 하행 종점역이 아닌 역을 제거하면
  - then 구간 제거 에러가 발생한다.
- [x] 노선에 1개의 구간만 있는 경우 구간 제거 예외 검증
  - given 1개의 지하철 구간을 등록하고
  - when 하행 종점역인 역을 제거하면
  - then 구간 제거 에러가 발생한다.

### Feedback 24.02.01
- [ ] 예외 분기 처리 부분을 @ControllerAdvice 혹은 @ExceptionHandler 활용해보기
- [ ] Section 컬렉션에 대해서 일급 컬렉션으로 리팩토링 해보기
- [x] 노선 삭제 인수 테스트 실패 이슈
  - Section - Line 간 FK 설정, Line 삭제 시 Section이 함께 삭제되도록 설정
- [ ] 테스트별로 반복되는 코드들을 fixture로 관리해보기
- Section / Line 도메인 구조 수정
