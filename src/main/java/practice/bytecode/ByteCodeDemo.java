package practice.bytecode;

/**
 * 바이트 코드 실습 예제
 * 이 클래스를 컴파일한 후 javap 명령어로 바이트코드 확인
 */
public class ByteCodeDemo {

    public static void main(String[] args) {
        //기본 타입 연산
        int a = 10;
        int b = 20;
        int result = a + b;

        System.out.println("결과 : " + result);
    }
}
