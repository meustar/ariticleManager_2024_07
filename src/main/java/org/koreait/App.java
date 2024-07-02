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
            cmd = sc.nextLine();

            if (cmd.equals("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else if (cmd.length() == 0) {
                System.out.println("명령어를 입력해주세요. :)");
                continue;
            }

            if (cmd.equals("article write")) {
                System.out.print("제목: ");
                String title = sc.nextLine();
                System.out.print("내용: ");
                String content = sc.nextLine();
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
                String title = sc.nextLine();
                System.out.print("내용 : ");
                String content = sc.nextLine();
                System.out.printf("%d번 게시글이 수정되었습니다.");
                
            }
        }
    }
}
