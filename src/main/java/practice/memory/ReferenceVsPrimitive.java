package practice.memory;

import java.sql.SQLOutput;

/**
 * 참조 타입 vs 기본 타입 메모리 차이 실습
 *
 * 기본 타입은 Stack에 저장
 * 참조 타입은 Heap에 저장되고 Stack에는 주소만 저장
 */
public class ReferenceVsPrimitive {

    public static void main(String[] args){
        System.out.println("===  기본 타입 (Primitive Type ===");
        //Stack 에 직접 값이 저장됨
        int num1 = 10;
        //값 복사
        int num2 = num1;
        //num1에 영향없음
        num2 = 20;

        System.out.println("num1: " + num1); // 10
        System.out.println("num2: " + num2); // 20
        System.out.println("num1은 변하지 않음 (값 복사)");

        System.out.println("\n=== 참조타입 (Reference Type) ===");
        // Heap에 배열 객체 생성, Stack에는 주소만 저장
        int[] arr1 = {1, 2, 3};
        //주소 복사
        int[] arr2 = arr1;
        // arr1도 영향받음
        arr2[0] = 999;

        System.out.println("arr1[0]: " + arr1[0]); // 999
        System.out.println("arr2[0]: " + arr2[0]); // 999
        System.out.println("arr1도 변함 (같은 객체 참조)");

        System.out.println("\n=== 객체 참조 확인 ===");
        System.out.println("arr1 주소: " + System.identityHashCode(arr1));
        System.out.println("arr2 주소: " + System.identityHashCode(arr2));
        System.out.println("같은 주소를 참조: " + (arr1 == arr2));

        // new 키워드로 생성한 객체는 항상 Heap에 새로운 인스턴스가 생성됨
        Person p1 = new Person("민수");
        Person p2 = new Person("민수");

        System.out.println("\n=== 객체 참조 확인 ===");
        System.out.println("p1 주소: " + System.identityHashCode(p1));
        System.out.println("p2 주소: " + System.identityHashCode(p2));
        System.out.println("다른 주소를 참조: " + (p1 == p2));

        // String Constant Pool에서 같은 문자열 리터럴은 재사용됨
        String str1 = "민수";
        String str2 = "민수";

        System.out.println("\n=== 객체 참조 확인 ===");
        System.out.println("str 주소: " + System.identityHashCode(str1));
        System.out.println("str2 주소: " + System.identityHashCode(str2));
        System.out.println("같은 주소를 참조: " + (str1 == str2));

        System.out.println("\n=== String 불변 ===");
        String str3 = "만수";
        System.out.println("srt 주소: " + System.identityHashCode(str3));
        str3 = "민수";
        System.out.println("srt 주소: " + System.identityHashCode(str3));

    }

    static class Person{
        String name;

        Person(String name){
            this.name = name;
        }

    }
}
