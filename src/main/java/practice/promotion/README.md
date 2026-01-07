# 객체 승격(Promotion) 실습

## 목표
- Young Generation -> Old Generation 승격 과정 이해
- Survivor 영역의 역할과 동작 확인
- Tenuring Threshold(승격 임계값)의 읨미 이해
- 짧게 사는 객체 vs 오래 사는 객체의 GC 동작 차이 관찰

### 객체 생명주기와 승격(Promotion)
1. Eden 할당 : 새로운 객체는 Eden
2. Minor GC : Eden이 가득 차면 Minor Gc 발생
3. Survivor 이동 : 살아남은 객체는 Survivor 0 또는 Survivor 1로 이동함
4. Survivor 간 이동 : 다음 Minor GC에서 살아남으면 다른 Survivor로 이동함
5. Old 승격 : 일정 횟수(보통 15회) 이상 Survivor에서 살아남으면 Old Generation으로 승격

### Tenuring Threshold

- 기본값 : 15(JDK 8+)
- 의미 : Survivor에서 몇 번 살아남아야 승격되는지
- 조절 : `-XX:MaxTenuringThreshold=N` 옵션으로 변경 가능

## 실행 환경

- JDK : 17이상
- VM 옵션
- -Xlog:gc:time -Xlog:gc+heap:time -Xmx256m -Xms128m -XX:MaxTenuringThreshold=3
- 빠른 관찰용으로 3번 살아남으면 Old로 승격

## 실습 단계

### 1단계 : 소스 코드 작성
`PromotionDemo.java` 확인

### 2단계 : 프로그램 실행 및 GC 로그 관찰
