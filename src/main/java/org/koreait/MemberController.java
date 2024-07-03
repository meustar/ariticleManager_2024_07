package org.koreait;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberController {

    Scanner sc;
    List<Member> members;

    private int lastMemberId = 3;

    public MemberController(Scanner sc) {

        this.sc = sc;
        members = new ArrayList<>();

    }

    public void doJoin() {
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

        System.out.println(id + "번 유저가 생성되었습니다");
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

    public void makeTestData() {
        System.out.println("회원 테스트 데이터 생성");
        members.add(new Member(1, Util.getNow(), "KGB", "test1", "강기범"));
        members.add(new Member(2, Util.getNow(), "SSS", "test2", "신여사"));
        members.add(new Member(3, Util.getNow(), "KDM", "test3", "강주임"));
    }
}


