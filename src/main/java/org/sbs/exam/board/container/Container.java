package org.sbs.exam.board.container;

import org.sbs.exam.board.article.controller.UsrArticleController;
import org.sbs.exam.board.member.controller.UsrMemberController;

import java.util.Scanner;

public class Container {
  public static Scanner sc;
  public static UsrArticleController usrArticleController;
  public static UsrMemberController usrMemberController;

  static {
    sc = new Scanner(System.in);
    usrArticleController = new UsrArticleController();
    usrMemberController = new UsrMemberController();
  }
}
