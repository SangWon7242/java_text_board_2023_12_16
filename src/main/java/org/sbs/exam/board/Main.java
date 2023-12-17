package org.sbs.exam.board;

import java.util.Scanner;
/*
- [o] /usr/article/detail 입력처리
- [] /usr/article/detail 입력되면 가장 최근 게시물 정보 노출
*/

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int articleLastId = 0;
    Article lastArticle = null;

    System.out.println("== 게시판 v 0.1 ==");
    System.out.println("== 게시판 시작 ==");

    while(true) {
      System.out.printf("명령 ) ");
      String cmd = sc.nextLine();

      if(cmd.equals("/usr/article/write")) {
        System.out.println("== 게시물 등록 ==");
        System.out.printf("제목 : ");
        String title = sc.nextLine();

        System.out.printf("내용 : ");
        String body = sc.nextLine();

        int id = articleLastId + 1;

        Article article = new Article(id, title, body);
        lastArticle = article;

        System.out.println("생성 된 게시물 객체 : " + article);
        System.out.printf("%d번 게시물이 등록되었습니다.\n", article.id);
        articleLastId++;
      }
      else if(cmd.equals("/usr/article/detail")) {
        Article article = lastArticle;

        if (article == null) {
          System.out.println("게시물이 존재하지 않습니다.");
          continue;
        }

        System.out.println("== 게시물 상세보기 ==");
        System.out.printf("번호 : %d\n", article.id);
        System.out.printf("제목 : %s\n", article.title);
        System.out.printf("내용 : %s\n", article.body);
      }
      else if(cmd.equals("exit")) {
        System.out.println("프로그램을 종료합니다.");
        break;
      }
      else {
        System.out.println("명령어를 다시 입력해주세요.");
      }
    }

    System.out.println("== 게시판 실행 끝 ==");

    sc.close();
  }
}

class Article {
  int id;
  String title;
  String body;

  Article(int id, String title, String body) {
    this.id = id;
    this.title = title;
    this.body = body;
  }

  @Override
  public String toString() {
    return String.format("{id : %d, title : \"%s\", body : \"%s\"}", id, title, body);
  }
}