package org.koreait.controller;

import org.koreait.articleManager.Container;
import org.koreait.dto.Member;
import org.koreait.util.Util;

import java.util.List;
import java.util.Scanner;

public class MemberController extends Controller {

    private Scanner sc;
    private List<Member> members;
    private String cmd;

    private int lastMemberId = 3;

    public MemberController(Scanner sc) {

        this.sc = sc;
//        members = new ArrayList<>();
        members = Container.memberDao.members;

    }

    public void doAction(String cmd, String actionMethodName) {
        this.cmd = cmd;

        switch (actionMethodName) {
            case "join":
                doJoin();
                break;
            case "login":
                doLogin();      // 내가 누구인지 알려주는 행위.
                break;
            case "logout":
                doLogout();     // 내가 누군지 몰라도 돼~
                break;
            default:
                System.out.println("명령어 확인 (actionMethodName) 오류");
                break;
        }
    }

    private void doLogout() {
        loginedMember = null;
        System.out.println("로그아웃 되었습니다.");
    }

    private void doLogin() {
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

    private void doJoin() {
        System.out.println("==회원가입==");
        int id = lastMemberId + 1;
        String regDate = Util.getNow();
        String loginId = null;

        // 중복확인
        while (true) {
            System.out.print("로그인 아이디 : ");
            loginId = sc.nextLine().trim();
            if (isJoinableLoginId(loginId) == false) {
                System.out.println("이미 사용중인 아이디 입니다.");
                continue;
            }
            break;
        }
        String loginPw = null;
        while (true) {
            System.out.print("비밀번호 : ");
            loginPw = sc.nextLine();
            System.out.print("비밀번호 확인 : ");
            String loginPwConfirm = sc.nextLine();

            if (loginPw.equals(loginPwConfirm) == false) {
                System.out.println("비밀번호를 다시 확인해주세요. : X");
                continue;
            }
            break;
        }

        System.out.print("이름 : ");
        String name = sc.nextLine();

        Member member = new Member(id, regDate, loginId, loginPw, name);
        members.add(member);

        System.out.println(id + "번 회원이 가입되었습니다");
        lastMemberId++;
    }

    private boolean isJoinableLoginId(String loginId) {
        for (Member member : members) {
            if (member.getLoginId().equals(loginId)) {
                return false;
            }
        }
        return true;
    }

    private Member getMemberByLoginId(String loginId) {
        for (Member member : members) {
            if (member.getLoginId().equals(loginId)) {
                return member;
            }
        }
        return null;
    }

    public void makeTestData() {
        System.out.println("회원 테스트 데이터 생성");
        members.add(new Member(1, Util.getNow(), "KGB", "test1", "강기범"));
        members.add(new Member(2, Util.getNow(), "SSS", "test2", "신여사"));
        members.add(new Member(3, Util.getNow(), "KDM", "test3", "강주임"));
    }
}


