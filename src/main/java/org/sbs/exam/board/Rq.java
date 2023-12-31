package org.sbs.exam.board;

import org.sbs.exam.board.container.Container;
import org.sbs.exam.board.member.dto.Member;
import org.sbs.exam.board.session.Session;

import java.util.Map;

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

  public void setSessionAttr(String key, Object value) {
    Session session = Container.getSession();

    session.setAttribute(key, value);
  }

  public boolean isLogined(String loginedMember) {
    Session session = Container.getSession();

    return session.hasAttribute(loginedMember);
  }

  public void removeSessionAttr(String loginedMember) {
    Session session = Container.getSession();

    session.removeAttribute(loginedMember);
  }

  public boolean isLogout(String loginedMember) {
    return !isLogined(loginedMember);
  }
}
