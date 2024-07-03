package org.koreait.articleManager;

import org.koreait.controller.ArticleController;
import org.koreait.controller.Controller;
import org.koreait.controller.MemberController;
import org.koreait.system.SystemController;

import java.util.Scanner;

public class App {

    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("==프로그램 시작==");

        MemberController memberController = new MemberController(sc);
        ArticleController articleController = new ArticleController(sc);

        articleController.makeTestData();
        memberController.makeTestData();

        Controller controller = null;


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

            String[] cmdBits = cmd.split(" ");

            String controllerName = cmdBits[0];

            if (cmdBits.length == 1) {
                System.out.println("명령어 확인해");
                continue;
            }

            String actionMethodName = cmdBits[1];

            if (controllerName.equals("article")) {
                controller = articleController;         // 형변환.
            } else if (controllerName.equals("member")) {
                controller = memberController;          // 형변환.
            } else {
                System.out.println("사용불가 명령어");
                continue;
            }

            controller.doAction(cmd, actionMethodName);
            // controller는 뭐가 될지 모름. a.c & m.c 들어오는 값에 따라서 달라짐.



//            if (cmd.equals("member join")) {
//                memberController.doJoin();
//            } else if (cmd.equals("article write")) {
//                articleController.doWrite();
//            } else if (cmd.startsWith("article list")) {
//                articleController.showList(cmd);
//            } else if (cmd.startsWith("article detail")) {
//                articleController.showDetail(cmd);
//            } else if (cmd.startsWith("article delete")) {
//                articleController.doDelete(cmd);
//            } else if (cmd.startsWith("article modify")) {
//                articleController.doModify(cmd);
//            } else {
//                System.out.println("사용할 수 없는 명령어입니다");
//            }
        }
        System.out.println("==프로그램 종료==");

        sc.close();
    }


}

