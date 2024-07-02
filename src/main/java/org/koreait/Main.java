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

        System.out.println("1. 로그인");
        System.out.println("2. 회원가입");

        if (sc.nextLine() == "1") {
            System.out.println("===== 로그인 ======");
            System.out.print("ID : ");

            System.out.print("PW : ");
        } else if (sc.nextLine() == "2") {
            System.out.println("====== 회원가입 ======");
            System.out.print("ID : ");
            System.out.print("PW : ");
        }



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