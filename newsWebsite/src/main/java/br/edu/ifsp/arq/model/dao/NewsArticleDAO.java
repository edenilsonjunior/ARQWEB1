package br.edu.ifsp.arq.model.dao;

import br.edu.ifsp.arq.model.entity.Commentary;
import br.edu.ifsp.arq.model.entity.NewsArticle;
import br.edu.ifsp.arq.model.entity.NewsArticleCategory;


import java.util.ArrayList;
import java.util.List;

public class NewsArticleDAO extends AbstractDAO<NewsArticle> {
    private static NewsArticleDAO instance;
    private static final String fileCSV = BASE_PATH + "/newsArticleData.csv";

    NewsArticleDAO() {
        super();
    }

    public static NewsArticleDAO getInstance() {
        if (instance == null) {
            instance = new NewsArticleDAO();
        }
        Utils.createDirectoryIfNotExists(BASE_PATH, fileCSV);
        return instance;
    }


    @Override
    public boolean add(NewsArticle newsArticle) {
        var list = getAll();

        if (newsArticle.getId() == null) {
            Long id = 1L;
            for (var na : list) {
                if (na.getId() >= id) {
                    id = na.getId() + 1;
                }
            }
            newsArticle.setId(id);
        }

        return super.add(newsArticle);
    }


    @Override
    protected String getFilePath() {
        return fileCSV;
    }

    @Override
    protected NewsArticle parse(String[] parts) {
        Long id = Long.parseLong(parts[0]);
        String title = parts[1];
        String author = parts[2];
        String publishDate = parts[3];
        String source = parts[4];
        String summary = parts[5];
        String text = parts[6];
        Long category = Long.parseLong(parts[7]);
        NewsArticleCategory newsArticleCategory = getCategoryById(category);
        List<String> images = new ArrayList<>();
        images.add(0, parts[8]);
        images.add(1, parts[9]);
        List<Commentary> comments = getCommentsById(id);
        NewsArticle news = new NewsArticle(id, title, author, publishDate, source, summary, text, newsArticleCategory, images, comments);

        return news;
    }

    @Override
    protected String toCsv(NewsArticle entity) {
        return entity.toString();
    }

    @Override
    protected Long getId(NewsArticle entity) {
        return entity.getId();
    }


    public void editNewsArticle(NewsArticle newsEdited) {
        List<NewsArticle> newsArticleList = getAll();
        deleteFile();

        for (NewsArticle n : newsArticleList) {
            if (n.getId().equals(newsEdited.getId())) {
                n.setAuthor(newsEdited.getAuthor());
                n.setTitle(newsEdited.getTitle());
                n.setPublishDate(newsEdited.getPublishDate());
                n.setSource(newsEdited.getSource());
                n.setSummary(newsEdited.getSummary());
                n.setText(newsEdited.getText());
                n.getImages().clear();
                n.setImages(newsEdited.getImages());
            }
            add(n);
        }
    }


    public void deleteNewsArticle(NewsArticle newsToDelete) {
        List<NewsArticle> newsArticleList = getAll();
        deleteFile();

        for (NewsArticle n : newsArticleList) {
            if (!n.getId().equals(newsToDelete.getId()))
                add(n);
        }
    }


    public List<NewsArticle> getNewsArticleSearched(String search) {
        List<NewsArticle> allArticles = getAll();
        List<NewsArticle> searchedArticles = new ArrayList<>();

        for (NewsArticle article : allArticles) {
            if (article.getTitle().contains(search) ||
                    article.getAuthor().contains(search) ||
                    article.getSummary().contains(search) ||
                    article.getText().contains(search) ||
                    article.getCategory().getCategory().contains(search)) {
                searchedArticles.add(article);
            }
        }

        return searchedArticles;
    }

    public List<NewsArticle> getNewsArticleCategories(Long categoryId) {
        List<NewsArticle> allArticles = getAll();
        List<NewsArticle> filteredArticles = new ArrayList<>();

        for (NewsArticle article : allArticles) {
            if (article.getCategory().getId().equals(categoryId)) {
                filteredArticles.add(article);
            }
        }

        return filteredArticles;
    }


    private List<Commentary> getCommentsById(Long id) {
        var comment = CommentaryDAO.getInstance();
        return comment.getCommentsById(id);
    }


    private NewsArticleCategory getCategoryById(Long id) {
        var category = CategoryDAO.getInstance();
        return category.getById(id);
    }
}
