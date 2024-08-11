package br.edu.ifsp.arq.model.dao;


/*
import br.edu.ifsp.arq.model.entity.NewsArticle;
import br.edu.ifsp.arq.model.entity.NewsArticleCategory;
import java.util.ArrayList;
import java.util.List;


public class MockNewsArticleDAO extends NewsArticleDAO {

    private static MockNewsArticleDAO instance;
    List<NewsArticle> mockNewsList = new ArrayList<>();
    NewsArticleCategory category = new NewsArticleCategory(1L, "Technology");


    private MockNewsArticleDAO() {
        super();
    }

    public static MockNewsArticleDAO getInstance() {
        if (instance == null) {
            instance = new MockNewsArticleDAO();
        }
        return instance;
    }

    @Override
    public List<NewsArticle> getNewsArticleSearched(String keyword) {
        mockNewsList.add(new NewsArticle(1L, "Title 1", "Author 1", "2023-10-01", "Source 1", "Summary 1", "Text 1", category, new ArrayList<>(), new ArrayList<>()));
        mockNewsList.add(new NewsArticle(2L, "Title 2", "Author 2", "2023-10-02", "Source 2", "Summary 2", "Text 2", category, new ArrayList<>(), new ArrayList<>()));
        mockNewsList.add(new NewsArticle(3L, "Title 3", "Author 3", "2023-10-03", "Source 3", "Summary 3", "Text 3", category, new ArrayList<>(), new ArrayList<>()));

        return mockNewsList;
    }

    public List<NewsArticle> getNewsArticle() {
        return mockNewsList;
    }

    public NewsArticle getNewsArticleById(Long id) {
        for (NewsArticle news : mockNewsList) {
            if (news.getId().equals(id)) {
                return news;
            }
        }
        return null;
    }
}*/
