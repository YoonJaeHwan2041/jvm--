# 3. 가비지 컬렉션 실습

## 목표
- GC 동작 과정 직접 관찰
- Minor GC vs Makor GC의 차이 이해
- Reachable vs Unreachable 객체 이해
- 메모리 누수 재현 및 해결

## 실습 첫번쨰
`GCObservation.java` 확인

## 실행 방법 
1. 해당 프로젝트 루트로 이동 (예 : cd C:\org\jvm-study)
2. 컴파일 : ./gradlew compileJava
3. 실행 (gc로그 + 메모리 제한) : java -Xlog:gc*:stdout:time,tags,level -Xmx256m -Xms128m -cp build/classes/java/main practice.gc.GCObservationFirst

## 실험 내용
`GCObservation_first.md` 확인

## 실습 두번쨰

## 실행방법
1. 해당 프로젝트 루트로 이동 (예 : cd C:\org\jvm-study)
2. 컴파일 : ./gradlew compileJava
3. 실행 (gc로그 + 메모리 제한) : java -Xlog:gc*:time -Xlog:gc,marking=off -Xmx256m -Xms128m -cp build/classes/java/main practice.gc.GCObservationSecond
