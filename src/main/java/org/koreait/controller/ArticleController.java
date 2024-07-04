package org.koreait.controller;

import org.koreait.dto.Article;
import org.koreait.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArticleController extends Controller {
    private Scanner sc;
    private List<Article> articles;
    private String cmd;

    private int lastArticleId = 3;

    public ArticleController(Scanner sc) {

        this.sc = sc;
        articles = new ArrayList<>();
    }

    public void doAction(String cmd, String actionMethodName) {
        this.cmd = cmd;

        switch (actionMethodName) {
            case "write":
                doWrite();
                break;
            case "list":
                showList();
                break;
            case "detail":
                showDetail();
                break;
            case "modify":
                doModify();
                break;
            case "delete":
                doDelete();
                break;
            default:
                System.out.println("명령어 확인 (actionMethodName) 오류");
                break;
        }
    }


    private void doWrite() {

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

        Article article = new Article(id, regDate, updateDate, loginedMember.getId(), title, body);
        articles.add(article);

        System.out.println(id + "번 글이 생성되었습니다");
        lastArticleId++;
    }


    private void showList() {
        System.out.println("==게시글 보기==");
        if (articles.size() == 0) {
            System.out.println("아무것도 없어");
            return;
        }

        String searchKeyword = cmd.substring("article list".length()).trim();

        List<Article> forPrintArticles = articles;

        if (searchKeyword.length() > 0) {
            System.out.println("검색어 : " + searchKeyword);
            forPrintArticles = new ArrayList<>();


            for (Article article : articles) {
                if (article.getTitle().contains(searchKeyword)) {
                    forPrintArticles.add(article);

                }
            }
            if (forPrintArticles.size() == 0) {
                System.out.println("  번호   /    날짜    /  작성자  /   제목   /   내용   ");
                System.out.println("검색 결과 없음");
                return;
            }
        }
        System.out.println("  번호   /    날짜    /  작성자  /   제목   /   내용   ");
        // 리스트 목록을 역순으로 가져와야 함.
        for (int i = forPrintArticles.size() - 1; i >= 0; i--) {
            Article article = forPrintArticles.get(i);

            // 날자를 " " 공백기준으로 자를때, [0]은 년월일, [1]은 시분초
            // 날자[0]과 현재 날자를 비교했을 때, 같은 날자이면, [1]의 시분초만 출력
            // 다른 날자이면 [0] 년월일만 출력
            if (Util.getNow().split(" ")[0].equals(article.getRegDate().split(" ")[0])) {
                System.out.printf("  %d   /   %s     /     %d     /   %s   /   %s  \n", article.getId(), article.getRegDate().split(" ")[1], article.getMemberId(), article.getTitle(), article.getBody());
            } else {
                System.out.printf("  %d   /   %s     /     %d     /   %s   /   %s  \n", article.getId(), article.getRegDate().split(" ")[0], article.getMemberId(), article.getTitle(), article.getBody());
            }
        }

//            } else if (cmd.equals("article list 제")) {
//                System.out.println("==선택 글 보기==");
//                for (int i = articles.size() - 1; i >= 0; i--) {
//                    Article article = articles.get(i);
//                    if (article.getTitle().startsWith("제") == true) {
//                        System.out.printf("  %d   /   %s      /   %s   /   %s  \n", article.getId(), article.getRegDate(), article.getTitle(), article.getBody());
//                    }
//                }


    }

    private void showDetail() {
        System.out.println("==게시글 상세보기==");

        int id = Integer.parseInt(cmd.split(" ")[2]);

        Article foundArticle = getArticleById(id);

        if (foundArticle == null) {
            System.out.println("해당 게시글은 없습니다");
            return;
        }

        System.out.println("번호 : " + foundArticle.getId());
        System.out.println("작성날짜 : " + foundArticle.getRegDate());
        System.out.println("수정날짜 : " + foundArticle.getUpdateDate());
        System.out.println("작성자 : " + foundArticle.getMemberId());
        System.out.println("제목 : " + foundArticle.getTitle());
        System.out.println("내용 : " + foundArticle.getBody());
    }


    private void doDelete() {
        System.out.println("==게시글 삭제==");

        int id = Integer.parseInt(cmd.split(" ")[2]);

        Article foundArticle = getArticleById(id);

        if (foundArticle == null) {
            System.out.println("해당 게시글은 없습니다");
            return;
        }
        if (foundArticle.getMemberId() != loginedMember.getId()) {
            System.out.println("권한 없음");
            return;
        }
        articles.remove(foundArticle);
        System.out.println(foundArticle.getId() + "번 게시글이 삭제되었습니다");
    }

    private void doModify() {
        System.out.println("==게시글 수정==");

        int id = Integer.parseInt(cmd.split(" ")[2]);

        Article foundArticle = getArticleById(id);

        if (foundArticle == null) {
            System.out.println("해당 게시글은 없습니다");
            return;
        }

        if (foundArticle.getMemberId() != loginedMember.getId()) {
            System.out.println("권한 없음");
            return;
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
    }

    private Article getArticleById(int id) {
        // 위 for문과 같음. 더 간략화 될뿐.
        for (Article article : articles) {
            if (article.getId() == id) {
                return article;
            }
        }
        return null;
    }

    public void makeTestData() {
        System.out.println("게시글 테스트 데이터 생성");

        articles.add(new Article(1, "2023-12-12-12 12:12:12", "2023-12-12-12 12:12:12", 1, "제목1", "내용1"));
        articles.add(new Article(2, Util.getNow(), Util.getNow(), 1,"제목2", "내용2"));
        articles.add(new Article(3, Util.getNow(), Util.getNow(), 2,"제목3", "내용3"));
    }

}
