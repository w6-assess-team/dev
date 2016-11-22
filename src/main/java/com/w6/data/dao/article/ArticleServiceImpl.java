package com.w6.data.dao.article;

import com.w6.data.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Article findById(Long id) {
        return articleRepository.findById(id);
    }

    @Override
    public List<Article> findAllByEventId(long eventId) {
        List<Article> result = new ArrayList<>();
        articleRepository.findAllByEventId(eventId).forEach(result::add);

        return articleRepository.findAllByEventId(eventId);
    }

    @Override
    public List<Article> findAll() {
        List<Article> result = new ArrayList<>();
        articleRepository.findAll().forEach(result::add);

        return result;
    }

    @Override
    public Article save(Article article) {
        if (article.getId() == -1) {
            long totalCount = articleRepository.count();
            article.setId(totalCount + 1);
        }
        return articleRepository.save(article);
    }

    @Override
    public long count() {
        return articleRepository.count();
    }
}
