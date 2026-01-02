package practice.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * GC 동작 관찰 실습
 * GC로그 켜고 실행해서 GC언제 발생하는지 확인
 *실행 : -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xmx128m -Xms64m practice.gc.GCObservation
 * -XX:+PrintGCDetails : GC 발생 시 상세 로그 출력
 *    - 어떤 Gc가 발생했는지
 *    - 마이너 풀GC
 *    - Eden / survivor / Old 영역 변화
 *    - GC 전후 메모리 사용량
 * - GC분석용 필수 옵션
 * -XX:+PrintGCTimeStamps
 *     - JVM 시작 후 몇 초 뒤에 GC가 발생했는지 출력
 *     - GC타이밍 분석 가능
 */
public class GCObservationFirst {
    public static void main(String[] args) throws InterruptedException {
        List<byte[]> list = new ArrayList<>();

        System.out.println("=== GC 관찰 시작 ===");
        System.out.println("메모리를 할당하며 GC 발생을 관찰합니다...\n");

        for(int i = 0; i < 100; i ++){
            //1MB씩 할당
            list.add(new byte[1024 * 1024]);
            Thread.sleep(100);

            if(i % 10 == 0) {
                //Runtime : JVM메모리 관찰의 핵심 API
                //.getRuntime() : 현재 JVM프로세스의 실행 환경을 나타내는 객체
                //JVM은 단 하나의 "Runtime" 인스턴스만 존재(싱글톤 느낌)
                Runtime runtime = Runtime.getRuntime();
                // totalMemory() : JVM 현재 확보해서 쓰고 있는 힙 메모리 크기
                // freeMemory() : 현재 힙 안에서 비어 있는 메모리
                // used = 현재 확보된 힙 - 그중 비어있는 공간
                long used = runtime.totalMemory() - runtime.freeMemory();
                // JVM이 최대로 사용할 수 있는 힙 크기
                long max = runtime.maxMemory();

                System.out.println("할당 횟수 : " + i);
                System.out.println("사용 메모리 : " + (used / 1024 / 1024) + "MB");
                System.out.println("최대 메모리 : " + (max / 1024 / 1024) + "MB");
                System.out.println("사용률 : " + (used * 100 / max) + "%\n");
            }
        }

        System.out.println("=== GC 관찰 종료 ===");
        System.out.println("콘솔에 출력된 GC 로그를 확인하셈!");
    }
}
