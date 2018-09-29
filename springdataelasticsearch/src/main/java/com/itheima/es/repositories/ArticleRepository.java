package com.itheima.es.repositories;

import com.itheima.es.entity.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ArticleRepository extends ElasticsearchRepository<Article, Long> {

    public List<Article> findByTitle(String title);

    public List<Article> findByTitleOrContent(String title,String content);

    public List<Article> findByTitleOrContent(String title, String content, Pageable pageable);
}
