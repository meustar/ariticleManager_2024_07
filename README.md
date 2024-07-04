## 24_07_02 19일차
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

#### 4.
1. makeTestData();
   - 기능 : 게시글 데이터 3개 자동생성

2. 프로그램 내 중복제거
- 내가 나눴던 중복제거.... parsingdate() 메소드, "제" 진행하다 멈춤....
```java
package org.koreait;

import org.koreait.system.SystemController;

import java.util.ArrayList;
import java.util.List;

public class Main {

    static List<Article> articles = new ArrayList<>();
    static Article foundArticle = null;


    public static void main(String[] args) {

        makeTestData();

        Util.init();

        System.out.println("==프로그램 시작==");

        int lastArticleId = 3;


        while (true) {
            System.out.print("명령어) ");
            String cmd = Util.getScanner().nextLine().trim();

            if (cmd.length() == 0) {
                System.out.println("명령어를 입력하세요");
                continue;
            }
            if (cmd.equals("exit")) {
                SystemController.exit();
                break;
            }

            if (cmd.equals("article write")) {
                System.out.println("==게시글 작성==");
                int id = lastArticleId + 1;
                // 작성 날자
                String regDate = Util.getNow();
                // 수정 날자 (지금은 작성날자)
                String updateDate = regDate;
                System.out.print("제목 : ");
                String title = Util.getScanner().nextLine();
                System.out.print("내용 : ");
                String body = Util.getScanner().nextLine();

                Article article = new Article(id, regDate, updateDate, title, body);
                articles.add(article);

                System.out.println(id + "번 글이 생성되었습니다");
                lastArticleId++;

            } else if (cmd.equals("article list")) {
                System.out.println("==전체 글 보기==");
                if (articles.size() == 0) {
                    System.out.println("아무것도 없어");
                } else {
                    System.out.println("  번호   /    날짜   /   제목   /   내용   ");

                    // 리스트 목록을 역순으로 가져와야 함.
                    for (int i = articles.size() - 1; i >= 0; i--) {
                        Article article = articles.get(i);

                        // 날자를 " " 공백기준으로 자를때, [0]은 년월일, [1]은 시분초
                        // 날자[0]과 현재 날자를 비교했을 때, 같은 날자이면, [1]의 시분초만 출력
                        // 다른 날자이면 [0] 년월일만 출력
                        if (Util.getNow().split(" ")[0].equals(article.getRegDate().split(" ")[0])) {
                            System.out.printf("  %d   /   %s      /   %s   /   %s  \n", article.getId(), article.getRegDate().split(" ")[1], article.getTitle(), article.getBody());
                        } else {
                            System.out.printf("  %d   /   %s      /   %s   /   %s  \n", article.getId(), article.getRegDate().split(" ")[0], article.getTitle(), article.getBody());
                        }
                    }
                }
            } else if (cmd.equals("article list text")) {
                if (cmd.split(" ")[2] == "제") {
                    for (int i = articles.size() - 1; i >= 0; i--) {
                        Article article = articles.get(i);
                        if (article.getTitle().startsWith("제")){
                            System.out.printf("  %d   /   %s      /   %s   /   %s  \n", article.getId(), article.getRegDate(), article.getTitle(), article.getBody());
                        }
                        
                    }
                }


            } else if (cmd.startsWith("article detail")) {
                System.out.println("==게시글 상세보기==");

                parsingdate();

                System.out.println("번호 : " + foundArticle.getId());
                System.out.println("작성날짜 : " + foundArticle.getRegDate());
                System.out.println("수정날짜 : " + foundArticle.getUpdateDate());
                System.out.println("제목 : " + foundArticle.getTitle());
                System.out.println("내용 : " + foundArticle.getBody());
            } else if (cmd.startsWith("article delete")) {
                System.out.println("==게시글 삭제==");

                parsingdate();

                articles.remove(foundArticle);
                System.out.println(foundArticle.getId() + "번 게시글이 삭제되었습니다");

            } else if (cmd.startsWith("article modify")) {
                System.out.println("==게시글 수정==");

                parsingdate();

                System.out.println("기존 제목 : " + foundArticle.getTitle());
                System.out.println("기존 내용 : " + foundArticle.getBody());
                System.out.print("새 제목 : ");
                String newTitle = Util.getScanner().nextLine();
                System.out.print("새 내용 : ");
                String newBody = Util.getScanner().nextLine();

                foundArticle.setTitle(newTitle);
                foundArticle.setBody(newBody);
                foundArticle.setUpdateDate(Util.getNow());

                System.out.println(foundArticle.getId() + "번 게시글이  수정되었습니다");
            }else {
                System.out.println("사용할 수 없는 명령어입니다");
            }

        }
        System.out.println("==프로그램 종료==");
        Util.getScanner().close();

    }

    private static void parsingdate() {

        String cmd = Util.getScanner().nextLine().trim();
        int id = Integer.parseInt(cmd.split(" ")[2]);

        for (Article article : articles) {
            if (article.getId() == id) {
                foundArticle = article;
                break;
            }
        }

        if (foundArticle == null) {
            System.out.println("해당 게시글은 없습니다");
        }
    }

    private static void makeTestData() {
        System.out.println("테스트 데이터 생성");

        articles.add(new Article(1, "2023-12-12-12 12:12:12", "2023-12-12-12 12:12:12", "제목1", "내용1"));
        articles.add(new Article(2, Util.getNow(), Util.getNow(), "제목2", "내용2"));
        articles.add(new Article(3, Util.getNow(), Util.getNow(), "제목3", "내용3"));

    }



}
```


3. > article list -> 전체 글 보기 \
   > article list 제 -> '제' 라는 글자가 제목에 포함된 글만 보기
```java
package org.koreait;

import org.koreait.system.SystemController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Article> articles = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("==프로그램 시작==");

        makeTestData();
        
        int lastArticleId = 3;

        while (true) {
            System.out.print("명령어) ");
            String cmd = sc.nextLine().trim();

            if (cmd.length() == 0) {
                System.out.println("명령어를 입력하세요");
                continue;
            }
            if (cmd.equals("exit")) {
                SystemController.exit();
                break;
            }

            if (cmd.equals("article write")) {
                System.out.println("==게시글 작성==");
                int id = lastArticleId + 1;
                // 작성 날자
                String regDate = Util.getNow();
                // 수정 날자 (지금은 작성날자)
                String updateDate = regDate;
                System.out.print("제목 : ");
                String title = sc.nextLine();
                System.out.print("내용 : ");
                String body = sc.nextLine();

                Article article = new Article(id, regDate, updateDate, title, body);
                articles.add(article);

                System.out.println(id + "번 글이 생성되었습니다");
                lastArticleId++;

            } else if (cmd.equals("article list")) {
                System.out.println("==전체 글 보기==");
                if (articles.size() == 0) {
                    System.out.println("아무것도 없어");
                } else {
                    System.out.println("  번호   /    날짜   /   제목   /   내용   ");

                    // 리스트 목록을 역순으로 가져와야 함.
                    for (int i = articles.size() - 1; i >= 0; i--) {
                        Article article = articles.get(i);

                        // 날자를 " " 공백기준으로 자를때, [0]은 년월일, [1]은 시분초
                        // 날자[0]과 현재 날자를 비교했을 때, 같은 날자이면, [1]의 시분초만 출력
                        // 다른 날자이면 [0] 년월일만 출력
                        if (Util.getNow().split(" ")[0].equals(article.getRegDate().split(" ")[0])) {
                            System.out.printf("  %d   /   %s      /   %s   /   %s  \n", article.getId(), article.getRegDate().split(" ")[1], article.getTitle(), article.getBody());
                        } else {
                            System.out.printf("  %d   /   %s      /   %s   /   %s  \n", article.getId(), article.getRegDate().split(" ")[0], article.getTitle(), article.getBody());
                        }
                    }
                }
            } else if (cmd.equals("article list 제")) {
                System.out.println("==선택 글 보기==");
                for (int i = articles.size() - 1; i >= 0; i--) {
                    Article article = articles.get(i);
                    if (article.getTitle().startsWith("제") == true) {
                        System.out.printf("  %d   /   %s      /   %s   /   %s  \n", article.getId(), article.getRegDate(), article.getTitle(), article.getBody());
                    }
                }

            } else if (cmd.startsWith("article detail")) {
                System.out.println("==게시글 상세보기==");

                int id = Integer.parseInt(cmd.split(" ")[2]);

                Article foundArticle = getArticleById(id);

                if (foundArticle == null) {
                    System.out.println("해당 게시글은 없습니다");
                    continue;
                }

                System.out.println("번호 : " + foundArticle.getId());
                System.out.println("작성날짜 : " + foundArticle.getRegDate());
                System.out.println("수정날짜 : " + foundArticle.getUpdateDate());
                System.out.println("제목 : " + foundArticle.getTitle());
                System.out.println("내용 : " + foundArticle.getBody());
            } else if (cmd.startsWith("article delete")) {
                System.out.println("==게시글 삭제==");

                int id = Integer.parseInt(cmd.split(" ")[2]);

                Article foundArticle = getArticleById(id);

                if (foundArticle == null) {
                    System.out.println("해당 게시글은 없습니다");
                    continue;
                }

                articles.remove(foundArticle);
                System.out.println(foundArticle.getId() + "번 게시글이 삭제되었습니다");

            } else if (cmd.startsWith("article modify")) {
                System.out.println("==게시글 수정==");

                int id = Integer.parseInt(cmd.split(" ")[2]);

                Article foundArticle = getArticleById(id);

                if (foundArticle == null) {
                    System.out.println("해당 게시글은 없습니다");
                    continue;
                }

                System.out.println("기존 제목 : " + foundArticle.getTitle());
                System.out.println("기존 내용 : " + foundArticle.getBody());
                System.out.print("새 제목 : ");
                String newTitle = sc.nextLine();
                System.out.print("새 내용 : ");
                String newBody = sc.nextLine();

                foundArticle.setTitle(newTitle);
                foundArticle.setBody(newBody);
                foundArticle.setUpdateDate(Util.getNow());

                System.out.println(foundArticle.getId() + "번 게시글이  수정되었습니다");
            }else {
                System.out.println("사용할 수 없는 명령어입니다");
            }

        }
        System.out.println("==프로그램 종료==");
        sc.close();
    }

    private static Article getArticleById(int id) {
//        for (int i = 0; i < articles.size(); i++) {
//            Article article = articles.get(i);
//            if (article.getId() == id) {
//                return article;
//            }
//        }
        for (Article article : articles) {
            if (article.getId() == id) {
                return article;
            }
        }
        return null;
    }

    private static void makeTestData() {
        System.out.println("테스트 데이터 생성");

        articles.add(new Article(1, "2023-12-12-12 12:12:12", "2023-12-12-12 12:12:12", "제목1", "내용1"));
        articles.add(new Article(2, Util.getNow(), Util.getNow(), "제목2", "내용2"));
        articles.add(new Article(3, Util.getNow(), Util.getNow(), "제목3", "내용3"));

    }
}
```

4. 회원가입
   - Member(id, regDate, LoginId, LoginPw, name)


## 24_07_03 20일차
#### 1. 교시
1. 회원가입
   - Member(id, regDate, LoginId, LoginPw, name)
2. 회원 테스트데이터 3명 생성
3. 로그인 --> member login
4. 로그아웃 --> member logout


#### 2. 교시

#### 3. 교시

#### 4. 교시
ArticleController 도입.
- do = 저장소(db)에 직접적인 데이터 입력이 이루어질 경우
- show = 데이터 변경 없이 가져와서 보여줄 경우
#### 5. 교시
- 부모 클래스의 doAction은 빈 깡통이지만. 리모콘을 잃어버리지 않으려면 꼭 필요하다.
- App 클래스의 간소화, 패키징, 상속, 추상
#### 6. 교시
- 로그인, 로그아웃 구현.

```java
public void doAction(String cmd, String actionMethodName) {
this.cmd = cmd;

        switch (actionMethodName) {
            case "join":
                doJoin();
                break;
            case "login":
                doLogin();      // 내가 누구인지 알려주는 행위.
            case "logout":
                doLogout();     // 내가 누군지 몰라도 돼~
            default:
                System.out.println("명령어 확인 (actionMethodName) 오류");
                break;
        }
    }

    private boolean isLogined() {
        return loginedMember != null;
    }

    private void doLogout() {
        if (!isLogined()) {
            System.out.println("이미 로그아웃 상태입니다.");
            return;
        }
        loginedMember = null;

        System.out.println("로그아웃 되었습니다.");
    }

    private void doLogin() {
        if (isLogined()) {
            System.out.println("이미 로그인 중입니다.");
            return;
        }
        System.out.println("==로그인==");

        System.out.print("로그인 아이디 : ");
        String loginId = sc.nextLine().trim();
        System.out.print("비밀번호 : ");
        String loginPw = sc.nextLine();

        // 얘 내 회원인가? -> 사용자가 방금 입력한 로그인 아이디랑 일치하는 회원이 나한태 있나?

        Member member = getMemberByLoginId(loginId);

        if (member == null) {
            System.out.println("일치하는 회원이 없습니다.");
            return;
        }

        // 있는놈이다. // 내가 알고있는 이놈의 비번이 지금 얘가 입력한거랑 일치한가?


        if (member.getLoginPw().equals(loginPw) == false) {
            System.out.println("비밀번호가 틀렸습니다.");
            return;
        }
        loginedMember = member;     // 핵심. 누가 로그인 했는지 알고있음.

        System.out.printf("%s님 로그인 성공\n", member.getName());
    }
```

#### 7. 교시
- 글쓰기 (article write)가 로그인 상태에서만 가능하도록
- 작성자만 수정/ 삭제 가능하도록


## 2024_07_04 21일차
#### 1교시.
#### 2교시.