### NATS Java Demo

#### 패키지 정보
|패키지|내용|
|---|---|
|kr.co.mq.util|각 종 유틸리티|
|kr.co.mq.basic|nats 기본 Pub/Sub|
|kr.co.mq.rr|nats Request Reply Pub/Sub|

#### NATS Docker 설치 및 실행
1. NATS 이미지를 다운로드 받는다.
```
#> docker pull nats:2.8.4-alpine3.15
```

2. 컨테이너를 생성한다.
```
#> docker run -d -p 4222:4222 --name "nats" -ti nats:2.8.4-alpine3.15
```

3. 생성한 컨테이너 로그를 확인하여 정상적으로 실행이 되었는지 확인한다.
```
#> docker logs nats

[1] 2022/06/07 07:14:01.396668 [INF] Starting nats-server
[1] 2022/06/07 07:14:01.396729 [INF]   Version:  2.8.4
[1] 2022/06/07 07:14:01.396731 [INF]   Git:      [66524ed]
[1] 2022/06/07 07:14:01.396733 [INF]   Cluster:  my_cluster
[1] 2022/06/07 07:14:01.396735 [INF]   Name:     NCQGDII6M3LDNUZDYKIVYQJWJDJJGTFLU3FVYPWUIOTAHLZRKPPJ5LYT
[1] 2022/06/07 07:14:01.396739 [INF]   ID:       NCQGDII6M3LDNUZDYKIVYQJWJDJJGTFLU3FVYPWUIOTAHLZRKPPJ5LYT
[1] 2022/06/07 07:14:01.396744 [INF] Using configuration file: /etc/nats/nats-server.conf
[1] 2022/06/07 07:14:01.397104 [INF] Starting http monitor on 0.0.0.0:8222
[1] 2022/06/07 07:14:01.397212 [INF] Listening for client connections on 0.0.0.0:4222
[1] 2022/06/07 07:14:01.397464 [INF] Server is ready
[1] 2022/06/07 07:14:01.397544 [INF] Cluster name is my_cluster
[1] 2022/06/07 07:14:01.397583 [INF] Listening for route connections on 0.0.0.0:6222
```

4. 컨테이너 접속은 아래와 같다.
```
#> docker exec -it nats sh
```