package org.sbs.exam.board.article.controller;

import org.sbs.exam.board.Rq;
import org.sbs.exam.board.Util;
import org.sbs.exam.board.article.dto.Article;
import org.sbs.exam.board.article.service.ArticleService;
import org.sbs.exam.board.container.Container;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UsrArticleController {

  private ArticleService articleService;
  private List<Article> articles;

  public UsrArticleController() {
    articleService = Container.articleService;
    articles = articleService.getArticles();
    articleService.makeTestData();
  }

  public void actionWrite() {
    System.out.println("== 게시물 등록 ==");
    System.out.printf("제목 : ");
    String title = Container.sc.nextLine();

    System.out.printf("내용 : ");
    String body = Container.sc.nextLine();

    int id = articleService.write(title, body);

    System.out.printf("%d번 게시물이 등록되었습니다.\n", id);
  }

  public void showList(Rq rq) {
    Map<String, String> params = rq.getParams();

    System.out.println("== 게시물 리스트 ==");
    System.out.println("-------------------");
    System.out.println("번호 / 제목");

    String searchKeyword = rq.getParam("searchKeyword" , "");

    // 검색 시작
    List<Article> filteredArticles = articleService.getArticles();

    if (searchKeyword.length() > 0) {
      filteredArticles = new ArrayList<>();

      for (Article article : articles) {
        boolean matched = article.getTitle().contains(searchKeyword) || article.getBody().contains(searchKeyword);

        if (matched) {
          filteredArticles.add(article);
        }
      }
    }
    // 검색 끝
    
    // 정렬 시작
    List<Article> sortedArticles = filteredArticles;

    String orderBy = rq.getParam("orderBy", "idDesc");

    boolean orderByIdDesc = orderBy.equals("idDesc");

    if (params.containsKey("orderBy") && params.get("orderBy").equals("idAsc")) {
      orderByIdDesc = false;
    }

    if (orderByIdDesc) {
      sortedArticles = Util.reverseList(sortedArticles);
    }
    // 정렬 끝

    for (Article article : sortedArticles) {
      System.out.printf("%d / %s\n", article.getId(), article.getTitle());
    }

    System.out.println("===================");
  }

  public void showDetail(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if(id == 0) {
      System.out.println("id를 올바르게 입력해주세요.");
      return;
    }

    if (articles.isEmpty()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    if (id > articles.size()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    Article article = articleService.getArticleById(id);

    if (article == null) {
      System.out.println("해당 게시물은 존재하지 않습니다.");
      return;
    }

    System.out.println("== 게시물 상세보기 ==");
    System.out.printf("번호 : %d\n", article.getId());
    System.out.printf("제목 : %s\n", article.getTitle());
    System.out.printf("내용 : %s\n", article.getBody());
  }

  public void actionModify(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if(id == 0) {
      System.out.println("id를 올바르게 입력해주세요.");
      return;
    }

    if (articles.isEmpty()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    Article article = articleService.getArticleById(id);

    if (article == null) {
      System.out.println("해당 게시물은 존재하지 않습니다.");
      return;
    }

    System.out.printf("새 제목 : ");
    article.setTitle(Container.sc.nextLine());
    System.out.printf("새 내용 : ");
    article.setBody(Container.sc.nextLine());

    System.out.printf("%d번 게시물을 수정하였습니다.\n", article.getId());
  }

  public void actionDelete(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if(id == 0) {
      System.out.println("id를 올바르게 입력해주세요.");
      return;
    }

    if (articles.isEmpty()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    // Article article = articles.get(id - 1);
    Article article = articleService.getArticleById(id);

    if (article == null) {
      System.out.println("해당 게시물은 존재하지 않습니다.");
      return;
    }

    articleService.remove(article);
    System.out.printf("%d번 게시물을 삭제하였습니다.\n", id);
  }
}
