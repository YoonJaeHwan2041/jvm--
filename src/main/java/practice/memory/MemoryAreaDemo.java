package practice.memory;

/**
 * 메모리 영역 실습 예제
 *
 * Method Area, Stack Area, Heap Area의 차이 확인
 */
public class MemoryAreaDemo {

    //Method Area에 저장 (static 변수)
    private static int staticVar = 100;

    //Heap에 저장 (인스턴스 변수)
    private int instanceVar = 50;

    public static void main(String[] args) {
        //Stack에 저장 (지역 변수)
        int localVar = 10;

        // Heap에 저장 (참조는 Stack에)
        //인스턴스 변수는 static에 접근 불가능
        //그래서 객체를 생성해서 인스턴스 변수에 접근해야함
        MemoryAreaDemo obj = new MemoryAreaDemo();

        System.out.println("=== 메모리 영역 확인 ===");
        //같은 Method Area 라서 따로 생성자 생성을 안해도 불러와짐
        System.out.println("Static 변수 (Method Area): " + staticVar);
        //메서드 안에서 선언해서 불러와짐(int즉 기본타입이라 바로 stack에 저장됨)
        System.out.println("Local 변수 (Stack): " + localVar);
        //static은 인스턴스 변수가 접근을 할수 없어서 따로 객체를 만듬(그래서heap에 들어감)
        System.out.println("Instance 변수 (Heap): " + obj.instanceVar);
        //
        System.out.println("객체 주소 (Hash): " + System.identityHashCode(obj));
    }


}
