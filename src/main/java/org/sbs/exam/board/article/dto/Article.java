package org.sbs.exam.board.article.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Article {
  private int id;
  private String title;
  private String body;
}
