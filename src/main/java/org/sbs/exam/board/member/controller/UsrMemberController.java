package org.sbs.exam.board.member.controller;

import org.sbs.exam.board.Rq;
import org.sbs.exam.board.container.Container;
import org.sbs.exam.board.member.dto.Member;

import java.util.ArrayList;
import java.util.List;

public class UsrMemberController {
  int memberLastId;
  List<Member> members;

  public UsrMemberController() {
    memberLastId = 0;
    members = new ArrayList<>();

    makeTestData();

    if (members.size() > 0) {
      memberLastId = members.get(members.size() - 1).getId();
    }
  }

  void makeTestData() {
    for (int i = 1; i <= 3; i++) {
      members.add(new Member(i, "user" + i, "user" + i, "유저" + i));
    }
  }

  public void actionJoin() {
    System.out.println("== 회원 가입 ==");
    System.out.printf("로그인 아이디 : ");
    String loginId = Container.sc.nextLine();

    Member member = getMemberByLoginId(loginId);

    if (member.getLoginId().equals(loginId)) {
      System.out.printf("\"%s\"은(는) 중복 된 로그인 아이디입니다.\n", loginId);
      return;
    }

    String loginPw;

    while (true) {
      System.out.printf("로그인 비밀번호 : ");
      loginPw = Container.sc.nextLine();

      System.out.printf("로그인 비밀번호 확인 : ");
      String loginPwConfirm = Container.sc.nextLine();

      if(loginPw.equals(loginPwConfirm) == false) {
        System.out.println("비밀번호가 일치하지 않습니다.");
        continue;
      }
      else {
        break;
      }
    }

    System.out.printf("이름 : ");
    String name = Container.sc.nextLine();

    int id = memberLastId + 1;

    member = new Member(id, loginId, loginPw, name);
    members.add(member);

    System.out.printf("\"%s\"님 회원가입 되었습니다.\n", loginId);
    memberLastId = id;
  }

  public void actionLogin(Rq rq) {
    if(rq.isLogined()) {
      System.out.println("이미 로그인 되어 있습니다.");
      System.out.println("로그아웃 후 이용해주세요.");
      return;
    }

    System.out.println("== 로그인 ==");
    System.out.printf("로그인 아이디 : ");
    String loginId = Container.sc.nextLine();

    if(loginId.trim().length() == 0) {
      System.out.println("로그인 아이디를 입력해주세요.");
      return;
    }

    Member member = getMemberByLoginId(loginId);

    if (member == null) {
      System.out.println("해당 로그인 아이디는 존재하지 않습니다.");
      return;
    }

    System.out.printf("로그인 비밀번호 : ");
    String loginPw = Container.sc.nextLine();

    if(loginPw.trim().length() == 0) {
      System.out.println("로그인 비밀번호를 입력해주세요.");
      return;
    }

    if(member.getLoginPw().equals(loginPw) == false) {
      System.out.println("로그인 비밀번호가 일치하지 않습니다.");
      return;
    }

    rq.login(member);

    System.out.printf("\"%s\"님 로그인 되었습니다.\n", loginId);
  }

  public void actionLogout(Rq rq) {
    if(rq.isLogout()) {
      System.out.println("이미 로그아웃 상태입니다.");
      return;
    }

    rq.logout();

    System.out.println("로그아웃 되었습니다.");
  }

  private Member getMemberByLoginId(String loginId) {
    for(Member member : members) {
      if(member.getLoginId().equals(loginId)) {
        return member;
      }
    }

    return null;
  }
}
