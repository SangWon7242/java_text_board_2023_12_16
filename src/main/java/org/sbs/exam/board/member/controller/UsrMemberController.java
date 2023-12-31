package org.sbs.exam.board.member.controller;

import org.sbs.exam.board.Rq;
import org.sbs.exam.board.article.dto.Article;
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

    boolean isDupLoginId = isDuplicatedLoginId(loginId);

    if(isDupLoginId == true) {
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

    Member member = new Member(id, loginId, loginPw, name);
    members.add(member);

    System.out.printf("\"%s\"님 회원가입 되었습니다.\n", loginId);
    memberLastId = id;
  }


  private boolean isDuplicatedLoginId(String loginId) {
    for(Member member : members) {
      if(member.getLoginId().equals(loginId)) {
        return true;
      }
    }

    return false;
  }
}
