package org.sbs.exam.board.article.service;

import org.sbs.exam.board.article.dto.Article;
import org.sbs.exam.board.article.repository.ArticleRepository;
import org.sbs.exam.board.container.Container;

import java.util.List;

public class ArticleService {
  private ArticleRepository articleRepository;

  public ArticleService() {
    articleRepository = Container.articleRepository;
  }

  public void makeTestData() {
    for (int i = 1; i <= 100; i++) {
      String title = "제목" + i;
      String body = "내용" + i;
      write(title, body);
    }
  }

  public int write(String title, String body) {
    return articleRepository.write(title, body);
  }

  public List<Article> getArticles() {
    return articleRepository.getArticles();
  }

  public Article getArticleById(int id) {
    return articleRepository.getArticleById(id);
  }

  public void remove(Article article) {
    articleRepository.remove(article);
  }
}
