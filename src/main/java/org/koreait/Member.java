package org.koreait;

public class Member {
    private int id;
    private int regDate;
    private String loginId;
    private String loginPw;
    private String name;

    public Member (int id, int regDate, String loginId, String loginPw, String name) {
        this.id = id;
        this.regDate = regDate;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.name = name;
    }
}
