package org.sbs.exam.board.member.dto;

public class Member {
  private int id;
  private String loginId;
  private String loginPw;
  private String name;

  public int getId() {
    return id;
  }

  public String getLoginId() {
    return loginId;
  }

  public String getLoginPw() {
    return loginPw;
  }

  public String getName() {
    return name;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setLoginId(String loginId) {
    this.loginId = loginId;
  }

  public void setLoginPw(String loginPw) {
    this.loginPw = loginPw;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Member(int id, String loginId, String loginPw, String name) {
    this.id = id;
    this.loginId = loginId;
    this.loginPw = loginPw;
    this.name = name;
  }

  @Override
  public String toString() {
    return "Member{" +
        "id=" + id +
        ", loginId='" + loginId + '\'' +
        ", loginPw='" + loginPw + '\'' +
        ", name='" + name + '\'' +
        '}';
  }
}
