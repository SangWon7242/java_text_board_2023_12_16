package org.sbs.exam.board;

import lombok.NoArgsConstructor;
import org.sbs.exam.board.container.Container;
import org.sbs.exam.board.member.dto.Member;
import org.sbs.exam.board.session.Session;

import java.util.Map;

@NoArgsConstructor
public class Rq {
  String url;
  Map<String, String> params;
  String urlPath;

  Rq(String url) {
    this.url = url;
    params = Util.getParamsFromUrl(url);
    urlPath = Util.getUrlPathFromUrl(url);
  }

  public Map<String, String> getParams() {
    return params;
  }

  public String getUrlPath() {
    return urlPath;
  }

  public void setCommand(String cmd) {
    urlPath = Util.getUrlPathFromUrl(cmd);
    params = Util.getParamsFromUrl(cmd);
  }

  public int getIntParam(String paramName, int defaultValue) {
    // id 값이 없는 경우
    if(params.containsKey(paramName) == false) {
      return defaultValue;
    }

    // id 있다고 하더라도 그 값이 정수가 아닌 경우
    try {
      return Integer.parseInt(params.get(paramName));
    }
    catch (NumberFormatException e) {
      return defaultValue;
    }
  }

  public String getParam(String paramName, String defaultValue) {
    if(params.containsKey(paramName) == false) {
      return defaultValue;
    }

    return params.get(paramName);
  }

  public Object getSessionAttr(String key) {
    Session session = Container.getSession();

    return session.getAttribute(key);
  }

  public Member getLoginedMember() {
    return (Member) getSessionAttr("loginedMember");
  }

  public void setSessionAttr(String key, Object value) {
    Session session = Container.getSession();

    session.setAttribute(key, value);
  }

  public boolean hasSessionAttr(String key) {
    Session session = Container.getSession();

    return session.hasAttribute(key);
  }

  public void removeSessionAttr(String loginedMember) {
    Session session = Container.getSession();

    session.removeAttribute(loginedMember);
  }

  public boolean isLogined() {
    return hasSessionAttr("loginedMember");
  }

  public boolean isLogout() {
    return !isLogined();
  }

  public void login(Member member) {
    setSessionAttr("loginedMember", member);
  }

  public void logout() {
    removeSessionAttr("loginedMember");
  }

}
