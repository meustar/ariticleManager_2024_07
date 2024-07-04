package org.koreait.controller;

import org.koreait.dto.Member;

public abstract class Controller {

    protected static Member loginedMember = null;

    //    public abstract
    public abstract void doAction(String cmd, String actionMethodName);
//    {
//      2024_07_03 . 5교시 11강 영상 꼭 참고.
//    }


    // 마지막 7교시 힌트....
    public static boolean isLogined(){
        return loginedMember != null;
    }
    public void makeTestData() {


        // 자바기초 46강 생성자01에...  static에 대한 강의영상.
    }
}
