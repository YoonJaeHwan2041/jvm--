package practice.memory;

/**
 * Stack Frame 동작 실습 예제
 *
 * 메서드 호출 시 Stack Frame이 어떻게 쌓이고 제거되는지 확인
 */
public class StackFrameDemo {
    public static void main(String[] args){
        System.out.println("==== Main 메서드 시자아아아아악 ====");
        System.out.println("Stack Frame : main ()");
        method1();
        /**
         * 지역 변수는 각 메서드 스택 프레임에 독립적으로 존재
         *
         * 값 복사(pass by value) → 다른 메서드에 영향 없음
         *
         * 그래서 method1의 a가 바뀌지 않는 건 자연스러운 현상
         */
        System.out.println("==== Main 메서드 종료오오오오오 ====");
    }

    //앞에 public 안붙으면 패키지 안에서만 사용가능(패키지 프라이빗)
    static void method1(){
        int a = 10;
        System.out.println("==== Method1 시자아아아아아아악 ====");
        System.out.println("Stack Frame : main() -> method1 ()");
        System.out.println("Method1의 지역변수 a = " + a);
        method2(a);
        System.out.println("==== Method1 종료오오오오오오오 ====");
        System.out.println("MEthod1의 지역 변수 a = " + a + " (변하지 않음)");
    }

    static void method2(int param){
        int b = 20;
        System.out.println("==== Method2 시자아아아아아아악 ====");
        System.out.println("Stack Frame: main() -> method1() -> method()");
        System.out.println("Method2의 매개변수 param = " + param);
        System.out.println("Method2의 지역 변수 b = " + b);
        System.out.println("==== Method2 종료오오오오오오오 ====");
        //Method 종료 시 Stack Frame 제거
    }
}
