package practice.promotion;

import java.util.ArrayList;
import java.util.List;

/**
 * 객체 승격(Promotion) 관찰 실습
 *
 * 오래 사는 객체가 yG에서 OG로
 * 승격되는 과정을 관촬
 *
 */
public class PromotionDemo {
    //오래 사는 객체들을 보관할 리스트 (OG로 승격될 예정)
    private static List<byte[]> longLivedObjects = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {

        System.out.println("=== 객체 승격(Promotion) 관찰 시작===");
        System.out.println("오래 사는 객체가 OG로 승격되는 과정을 관찰한다");
        System.out.println();

        Runtime runtime = Runtime.getRuntime();

        System.out.println("=== 초기 메모리 상태 ===");
        System.out.println("최대 메모리: " + (runtime.maxMemory() / 1024 / 1024) + "MB");
        System.out.println("여유 메모리: " + (runtime.freeMemory() / 1024 / 1024) + "MB");
        System.out.println();

        System.out.println("=== 1단계: 오래 사는 객체 할당 시작 ===");
        System.out.println("이 객체들은 계속 참조되므로 여러 번의 GC를 살아남아Old로 승격됨");

        // 여러 번의 Gc 사이클을 만들어서 객체 승격 시키기
        for(int cycle = 0; cycle < 50; cycle++){
            //각 사이클마다 새로운 객체를 할당하고 lonLiveObjects에 추가
            for(int i = 0; i < 50; i++){
                byte[] obj = new byte[1024 * 10];
                longLivedObjects.add(obj);
            }

            //짧게 사는 객체들도 할당 (바로 GC되는 친구들)
            for (int i = 0; i < 200; i++){
                byte[] shortLived = new byte[1024 * 10];
                //참조를 유지 하지 않아서 다음 GC에서 회수됨
            }

            if (cycle % 5 == 0) {
                long used = runtime.totalMemory() - runtime.freeMemory();
                long max = runtime.maxMemory();
                long free = runtime.freeMemory();

                System.out.println("[사이클 " + cycle + "]");
                System.out.println("  오래 사는 객체 수: " + longLivedObjects.size());
                System.out.println("  사용 메모리: " + (used / 1024 / 1024) + "MB");
                System.out.println("  여유 메모리: " + (free / 1024 / 1024) + "MB");
                System.out.println("  사용률: " + (used * 100 / max) + "%\n");
            }

            Thread.sleep(100);
        }
        System.out.println("=== 2단계: 최종 상태 확인 ===");
        System.out.println("총 오래 사는 객체 수: " + longLivedObjects.size());
        System.out.println("이 객체들은 여러 번의 GC를 살아남아 Old Generation으로 승격되었을 것입니다.\n");

        System.out.println("=== GC 로그 분석 포인트 ===");
        System.out.println("1. Young GC가 여러 번 발생하는지 확인");
        System.out.println("2. Survivor 영역의 변화 관찰 (Eden -> Survivor -> Old)");
        System.out.println("3. Old Generation 메모리 증가 확인");
        System.out.println("4. MaxTenuringThreshold=1이므로 1번만 살아남으면 Old로 승격됨");

        System.out.println("\n=== 객체 승격(Promotion) 관찰 종료 ===");

    }
}
