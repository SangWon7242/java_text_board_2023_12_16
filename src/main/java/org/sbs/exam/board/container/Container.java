package org.sbs.exam.board.container;

import org.sbs.exam.board.article.controller.UsrArticleController;
import org.sbs.exam.board.article.repository.ArticleRepository;
import org.sbs.exam.board.article.service.ArticleService;
import org.sbs.exam.board.member.controller.UsrMemberController;
import org.sbs.exam.board.session.Session;

import java.util.Scanner;

public class Container {
  public static Scanner sc;
  public static Session session;

  public static ArticleRepository articleRepository;

  public static ArticleService articleService;

  public static UsrArticleController usrArticleController;
  public static UsrMemberController usrMemberController;

  static {
    sc = new Scanner(System.in);
    session = new Session();

    articleRepository = new ArticleRepository();
    articleService = new ArticleService();

    usrArticleController = new UsrArticleController();
    usrMemberController = new UsrMemberController();
  }

  public static Session getSession() {
    return session;
  }
}
