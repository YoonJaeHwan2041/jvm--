# 바이트코드 실습

## 목표
- Java 소스 코드가 바이트코드(.class)로 컴파일되는 과정 이해
- 바이트코드를 직접 확인하고 분석하기
- JVM이 이해하는 중간 코드 형태 확인

## 실습 단계

### 1단계 : 소스코드 작성
`ByteCodeDemo.java` 파일 확인

### 2단계 : 컴파일 및 실행
코드를 컴파일하고 실행하기(요즘은 IDE에서 저장하면 자동으로 컴파일해줌)
![img.png](img/img_1.png)

### 3단계 : 바이트코드 확인
컴파일된 .class 파일의 바이트코드를 확인

확인방법 (IntelliJ에서는 쉽게 볼수있게 지원해준다.)

- ByteCodeDemo.java 파일 열기
- Ctrl+Shift+A (또는 Cmd+Shift+A on Mac)
- "Show Bytecode" 입력 후 실행
- 또는 프로젝트 뷰에서 ByteCodeDemo.class 파일 찾아서 우클릭 → "Show Bytecode"

```java
// class version 61.0 (61)
// access flags 0x21
public class practice/bytecode/ByteCodeDemo {

  // compiled from: ByteCodeDemo.java
  // access flags 0x19
  public final static INNERCLASS java/lang/invoke/MethodHandles$Lookup java/lang/invoke/MethodHandles Lookup

  // access flags 0x1
  public <init>()V
   L0
    LINENUMBER 8 L0
    ALOAD 0
    INVOKESPECIAL java/lang/Object.<init> ()V
    RETURN
   L1
    LOCALVARIABLE this Lpractice/bytecode/ByteCodeDemo; L0 L1 0
    MAXSTACK = 1
    MAXLOCALS = 1

  // access flags 0x9
  public static main([Ljava/lang/String;)V
   L0
    LINENUMBER 12 L0
    BIPUSH 10
    ISTORE 1
   L1
    LINENUMBER 13 L1
    BIPUSH 20
    ISTORE 2
   L2
    LINENUMBER 14 L2
    ILOAD 1
    ILOAD 2
    IADD
    ISTORE 3
   L3
    LINENUMBER 16 L3
    GETSTATIC java/lang/System.out : Ljava/io/PrintStream;
    ILOAD 3
    INVOKEDYNAMIC makeConcatWithConstants(I)Ljava/lang/String; [
      // handle kind 0x6 : INVOKESTATIC
      java/lang/invoke/StringConcatFactory.makeConcatWithConstants(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
      // arguments:
      "\uacb0\uacfc : \u0001"
    ]
    INVOKEVIRTUAL java/io/PrintStream.println (Ljava/lang/String;)V
   L4
    LINENUMBER 17 L4
    RETURN
   L5
    LOCALVARIABLE args [Ljava/lang/String; L0 L5 0
    LOCALVARIABLE a I L1 L5 1
    LOCALVARIABLE b I L2 L5 2
    LOCALVARIABLE result I L3 L5 3
    MAXSTACK = 2
    MAXLOCALS = 4
}

```



