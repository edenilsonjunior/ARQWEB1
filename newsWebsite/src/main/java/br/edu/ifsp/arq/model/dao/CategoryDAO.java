package br.edu.ifsp.arq.model.dao;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

import br.edu.ifsp.arq.model.entity.NewsArticleCategory;

public class CategoryDAO {

    private static CategoryDAO instance = null;
    private static final String directoryPath = "/data/";
    private static final String fileCSV = directoryPath + "categories.csv";

    private CategoryDAO() {}

    public static CategoryDAO getInstance() {

        if(instance == null) {
            instance = new CategoryDAO();

            File directory = new File(directoryPath);
            if (!directory.exists()) {
                if (directory.mkdirs()) {
                    System.out.println("Diretório criado com sucesso: " + directoryPath);
                } else {
                    System.out.println("Falha ao criar o diretório: " + directoryPath);
                }
            }
        }

        return instance;
    }


    public void add(NewsArticleCategory category) {

        try(var pw = new PrintWriter(new FileWriter(new File(fileCSV),true))) {

            var list = getAll();
            Long id = 1L;

            for (var c: list) {
                if (c.getId() >= id) {
                    id = c.getId() + 1;
                }
            }
            category.setId(id);

            pw.println(category);

        }  catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<NewsArticleCategory> getAll() {

        var categories = new ArrayList<NewsArticleCategory>();

        try (var reader = new BufferedReader(new FileReader(new File(fileCSV)))) {

            String row;
            while ((row = reader.readLine()) != null) {
                String[] parts = row.split(";");

                Long id = Long.parseLong(parts[0]);
                String category = parts[1];

                categories.add(new NewsArticleCategory(id, category));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return categories;
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

    public NewsArticleCategory getByCategory(String category) {

        var categories = getAll();

        return categories.stream()
                .filter(c -> c.getCategory().equals(category))
                .findFirst()
                .orElse(null);
    }


    public void edit(NewsArticleCategory categoryEdited) {

        var categories = getAll();
        deleteFile();

        for (var c: categories) {
            if (c.getId().equals(categoryEdited.getId())) {
                c.setCategory(categoryEdited.getCategory());
            }
            add(c);
        }
    }

    public void delete(NewsArticleCategory category) {

        var categories = getAll();
        deleteFile();

        for (var c: categories) {
            if (!c.getId().equals(category.getId())) {
                add(c);
            }
        }
    }
}
