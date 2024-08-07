package br.edu.ifsp.arq.model.dao;

import br.edu.ifsp.arq.model.entity.NewsArticle;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NewsArticleDAO {
    private static NewsArticleDAO instance;
    private static final String fileCSV =  "/home/henrique/newsArticleData.csv";

    private final Long counter = 0L;

    private NewsArticleDAO() {}

    public static NewsArticleDAO getInstance() {
        if (instance == null) {
            instance = new NewsArticleDAO();
        }
        return instance;
    }

    public void addNewsArticle(NewsArticle newsArticle) {

        var f = new File(fileCSV);

        try {
            FileWriter fw = new FileWriter(f, true);
            System.out.println(f.getAbsolutePath());
            PrintWriter pw = new PrintWriter(fw);
            var id = counter + 1;
            newsArticle.setId(id);
            pw.close();
            fw.close();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<NewsArticle> getNewsArticle() {
        List<NewsArticle> newsArticleList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(new File(fileCSV)))) {
            String row;
            while ((row = reader.readLine()) != null) {
                String[] parts = row.split(";");
                if (parts.length == 9) {
                    Long id = Long.parseLong(parts[0]);
                    String title = parts[1];
                    String author = parts[2];
                    String publishDate = parts[3];
                    String source = parts[4];
                    String summary = parts[5];
                    String text = parts[6];
                    List<String> images = new ArrayList<>();
                    images.add(0,parts[7]);
                    images.add(1,parts[8]);
                    NewsArticle news = new NewsArticle(id, title, author, publishDate, source, summary, text, images);
                    newsArticleList.add(news);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newsArticleList;
    }

    private void deleteFile() {
        try {
            FileWriter fw = new FileWriter(fileCSV, false);
            PrintWriter pw = new PrintWriter(fw);
            pw.print("");
            pw.close();
            fw.close();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    public NewsArticle getNewsArticleById(Long id) {
        List<NewsArticle> newsArticleList = getNewsArticle();
        for (NewsArticle n : newsArticleList) {
            if (Objects.equals(n.getId(), id)) {
                return n;
            }
        }
        return null;
    }

    public void editNewsArticle(NewsArticle newsEdited) {
        List<NewsArticle> newsArticleList = getNewsArticle();
        deleteFile();

        for (NewsArticle n: newsArticleList) {
            if (n.getId().equals(newsEdited.getId())) {
                n.setAuthor(newsEdited.getAuthor());
                n.setTitle(newsEdited.getTitle());
                n.setPublishDate(newsEdited.getPublishDate());
                n.setSource(newsEdited.getSource());
                n.setSummary(newsEdited.getSummary());
                n.setText(newsEdited.getText());
                n.addImage(0 ,String.valueOf(newsArticleList.get(0)));
                n.addImage(1 ,String.valueOf(newsArticleList.get(1)));
            }
            addNewsArticle(n);
        }
    }

    public void deleteNewsArticle(NewsArticle newsToDelete) {
        List<NewsArticle> newsArticleList = getNewsArticle();
        deleteFile();

        for (NewsArticle n : newsArticleList) {
            if (n.getId() != newsToDelete.getId()) {
                addNewsArticle(n);
            }
        }
    }
}
