## 2024_07_02_ 18일차
#### 1 ~ 2교시
- 구현하기


명령어) article write
제목: ~~~
내용: ~~~
1번 글이 생성되었습니다
명령어) article write
제목: @@@
내용: @@@
2번 글이 생성되었습니다
명령어) article list
번호  /  제목  / 내용
2   /   @@@ /  @@@
1   /   ~~~ /  ~~~

명령어) article detail 3
3번 게시글은 없습니다

명령어) article detail 1
번호 : 1
날짜 : 2024-12-12 12:12:12(작성 날짜 및 시각이 나와야 함)
제목 : ~~~
내용 : ~~~

명령어) article delete 3
3번 게시글은 없습니다

명령어) article delete 1
1번 게시글이 삭제되었습니다

명령어) article modify 3
3번 게시글은 없습니다

명령어) article modify 2
기존 제목 : @@@
기존 내용 : @@@
제목 : ### (사용자의 입력)
내용 : ###
2번 게시글이 수정되었습니다.



```java
package org.koreait;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        new App(sc).run();

        sc.close();
    }
}
```

```java
package org.koreait;

import java.util.Scanner;

public class App {

    private Scanner sc;

    public App(Scanner sc) {
        this.sc = sc;
    }

    public void run() {
        int number = 1;
        String cmd;

        while (true) {
            System.out.print("명령어) ");
            cmd = sc.nextLine().trim();

            if (cmd.equals("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else if (cmd.length() == 0) {
                System.out.println("명령어를 입력해주세요. :)");
                continue;
            }

            if (cmd.equals("article write")) {
                String regDate = Util.getNow();
                String updateDate = regDate;
                System.out.print("제목: ");
                String title = sc.nextLine();
                System.out.print("내용: ");
                String content = sc.nextLine();


                Article article = new Article(number, regDate, updateDate,  title, content);
                articles.add(article);

                System.out.printf("%d번 글이 생성되었습니다.\n", number);
                number++;

            } else if (cmd.equals("article list")) {
                System.out.println("번호   /   제목   /   내용   ");

            } else if (cmd.equals("article detail")) {
                System.out.printf("번호 : %d\n");
                System.out.printf("날짜 : %d\n");
                System.out.printf("제목 : %s\n");
                System.out.printf("내용 : %s\n");

            } else if (cmd.equals("article delete")) {
                System.out.printf("%d번 게시글이 삭제되었습니다.");
                
            } else if (cmd.equals("article modify")) {
                System.out.printf("기존 제목: ");
                System.out.printf("기존 내용: ");
                System.out.print("제목: ");
                String newTitle = sc.nextLine();
                System.out.print("내용 : ");
                String newContent = sc.nextLine();
                System.out.printf("%d번 게시글이 수정되었습니다.");
                
            }
        }
    }
}

```
```java
package org.koreait;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {
    public static String getNow() {
        LocalDateTime now = LocalDateTime.now();
        String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));

        return formatedNow;
    }
}

```

- 1~3교시까지 내가 했던 코드......
- 다시 파해쳐볼 생각으로 저장. 지금 코드는 이제 선생님 코드