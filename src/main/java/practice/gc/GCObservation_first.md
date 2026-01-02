## 실험 설정
- **메모리 설정** : `-Xmx256m -Xms128m` (최대 256MB, 초기 128MB)
- **객체 할당** : 10MB 크기의 byte 배열을 100회 할당
- **GC 알고리즘** : G1 GC (JDK 17 기본 GC)

### 관촬된 현상

#### 1. Humongous 객체 할당
- **10MB 객체는 G1 Gc에서 Humongous 객체로 분류됨**
- Humongous 객체는 Region 크기의 50% 이상이므로 Eden 영역을 거치지 않고 바로 Humongous region에 할당
- 로그에서 `Humongous regions : 162 -> 164 -> 166 ... ` 형태로 계속 증가하는 것을 확인

#### 2. Minor GC(Pause Young)
- **메모리 사용률 약 60% 이상에서 발생**
- `Pause Young (Concurrent start) (G1 Humongous Allocation)` 형태로 발생
- Eden regions가 0으로 줄어드는 것을 확인 (`Eden regions : 1 ->0`)
- GC 소요 시간 : 약 2.5ms

#### 3. Major GC (Concurrent Mark Cycle)
- Minor Gc 후 자동으로 시작되는 Concurrent Mark Cycle
- 애플리케이션과 동시에 실행되어 Stop-The-World 시간 최소화
- 전체 사이클 소요 시간 : 약 4.8ms

#### 4. 메모리 회수 실패
- **메모리가 회수되지 않고 계속 증가** (163m -> 164m -> 166m...)
- 이유 : 모든 객체가 `list`에 참조되어 있어 reachable 상태
- GC가 실행되어도 회수할 수 있는 Unreachable 객체가 없음

### 핵심 학습 포인트
1. **Humongous 객체**: 큰 객체(일반적으로 1MB 이상)는 Eden을 거치지 않고 바로 Old/Humongous 영역에 할당
2. **Minor GC**: Young Generation에서 발생, 매우 빠름 (2~3ms)
3. **Concurrent Mark Cycle**: G1 GC의 특징, 애플리케이션과 동시 실행
4. **Reachable 객체**: 참조가 있는 객체는 GC 대상이 아님
5. **메모리 회수**: Unreachable 객체만 회수 가능

### 실험 개선 방안
- 객체를 null로 설정하거나 list에서 제거하면 GC가 메모리를 회수할 수 있음
- 작은 크기(예: 1MB)의 객체로 할당하면 Eden 영역을 거쳐 일반적인 GC 과정 관찰 가능