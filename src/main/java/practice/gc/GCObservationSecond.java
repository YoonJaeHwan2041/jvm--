package practice.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * GC 동작 관찰 실습
 *
 * 작은 객체를 할당하고 일부를 null로 설정하여 GC가 메모리를 회수하는 모습을 관찰한다.
 *
 */
public class GCObservationSecond {
    public static void main(String[] args) throws InterruptedException {
        List<byte[]> list = new ArrayList<>();

        System.out.println("=== GC 관찰 시작 ===");
        System.out.println("작은 객체를 할당하며 GC 발생을 관찰합니다...\n");

        Runtime runtime = Runtime.getRuntime();

        // 초기 메모리 상태
        long initialFree = runtime.freeMemory();
        System.out.println("=== 초기 메모리 상태 ===");
        System.out.println("최대 메모리: " + (runtime.maxMemory() / 1024 / 1024) + "MB");
        System.out.println("여유 메모리: " + (initialFree / 1024 / 1024) + "MB\n");

        // 1단계: 작은 객체들을 할당 (Eden 영역에 할당)
        System.out.println("=== 1단계: 작은 객체 할당 (Eden 영역) ===");
        for (int i = 0; i < 100; i++) {
            // 1MB씩 할당 (Humongous가 아닌 작은 객체)
            list.add(new byte[1024 * 1024]); // 1MB

            if (i % 20 == 0) {
                long used = runtime.totalMemory() - runtime.freeMemory();
                long max = runtime.maxMemory();
                long free = runtime.freeMemory();

                System.out.println("[" + i + "회 할당]");
                System.out.println("  사용: " + (used / 1024 / 1024) + "MB");
                System.out.println("  여유: " + (free / 1024 / 1024) + "MB");
                System.out.println("  사용률: " + (used * 100 / max) + "%\n");
            }
            Thread.sleep(50);
        }

        long afterAllocation = runtime.freeMemory();
        System.out.println("할당 후 여유 메모리: " + (afterAllocation / 1024 / 1024) + "MB");
        System.out.println("메모리 감소량: " + ((initialFree - afterAllocation) / 1024 / 1024) + "MB\n");

        // 2단계: 일부 객체를 null로 설정 (Unreachable 만들기)
        System.out.println("=== 2단계: 일부 객체를 null로 설정 (Unreachable 만들기) ===");
        int nullCount = 0;
        for (int i = 0; i < list.size(); i += 2) { // 절반을 null로
            list.set(i, null);
            nullCount++;
        }

        // null 제거
        list.removeIf(obj -> obj == null);

        System.out.println(nullCount + "개 객체를 null로 설정하여 GC 대상으로 만듦");
        System.out.println("남은 객체 수: " + list.size());

        long beforeGC = runtime.freeMemory();
        System.out.println("GC 전 여유 메모리: " + (beforeGC / 1024 / 1024) + "MB");

        // GC 강제 실행 시도
        System.out.println("\n=== 3단계: GC 실행 및 메모리 회수 확인 ===");
        System.gc();
        Thread.sleep(2000); // GC 시간 충분히 확보

        long afterGC = runtime.freeMemory();
        System.out.println("GC 후 여유 메모리: " + (afterGC / 1024 / 1024) + "MB");
        System.out.println("메모리 회수량: " + ((afterGC - beforeGC) / 1024 / 1024) + "MB");

        if (afterGC > beforeGC) {
            System.out.println("✅ GC가 메모리를 회수했습니다!");
        } else {
            System.out.println("⚠️ GC가 실행되었지만 메모리 회수가 미미합니다.");
            System.out.println("   (GC 로그를 확인하여 실제 회수 여부를 확인하세요)");
        }

        System.out.println("\n=== 4단계: 추가 객체 할당 (GC 재발생 관찰) ===");
        // 추가 객체 할당하여 GC 재발생 관찰
        for (int i = 0; i < 50; i++) {
            list.add(new byte[1024 * 1024]); // 1MB
            Thread.sleep(100);

            if (i % 10 == 0) {
                long used = runtime.totalMemory() - runtime.freeMemory();
                long max = runtime.maxMemory();
                long free = runtime.freeMemory();

                System.out.println("[" + i + "회 추가 할당]");
                System.out.println("  사용: " + (used / 1024 / 1024) + "MB");
                System.out.println("  여유: " + (free / 1024 / 1024) + "MB");
                System.out.println("  사용률: " + (used * 100 / max) + "%\n");
            }
        }

        System.out.println("\n=== GC 관찰 종료 ===");
        System.out.println("콘솔에 출력된 GC 로그를 확인하세요!");
        System.out.println("- Eden regions: 0->N 형태로 변화 확인");
        System.out.println("- Survivor regions: 살아남은 객체 이동 확인");
        System.out.println("- 메모리 회수량 확인");
    }
}
