package org.sbs.exam.board.container;

import org.sbs.exam.board.article.controller.UsrArticleController;

import java.util.Scanner;

public class Container {
  public static Scanner sc;
  public static UsrArticleController usrArticleController;

  static {
    sc = new Scanner(System.in);
    usrArticleController = new UsrArticleController();
  }
}
