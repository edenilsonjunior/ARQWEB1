package br.edu.ifsp.arq.model.dao;


import br.edu.ifsp.arq.model.entity.NewsArticleCategory;

public class CategoryDAO extends AbstractDAO<NewsArticleCategory> {

    private static CategoryDAO instance = null;
    private static final String fileCSV = "/data/categoryData.csv";

    private CategoryDAO() {
    }

    public static CategoryDAO getInstance() {
        if (instance == null) {
            instance = new CategoryDAO();
        }
        return instance;
    }


    @Override
    protected String getFilePath() {
        return fileCSV;
    }

    @Override
    protected NewsArticleCategory parse(String[] parts) {
        Long id = Long.parseLong(parts[0]);
        String category = parts[1];

        return new NewsArticleCategory(id, category);
    }

    @Override
    protected String toCsv(NewsArticleCategory entity) {
        return entity.toString();
    }

    @Override
    protected Long getId(NewsArticleCategory entity) {
        return entity.getId();
    }

    @Override
    public boolean add(NewsArticleCategory category) {
        var list = getAll();

        if (category.getId() == null) {
            Long id = 1L;
            for (var c : list) {
                if (c.getId() >= id) {
                    id = c.getId() + 1;
                }

                if (c.getCategory().equals(category.getCategory())) {
                    return false;
                }
            }
            category.setId(id);
        }

        return super.add(category);
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

        for (var c : categories) {
            if (c.getId().equals(categoryEdited.getId())) {
                c.setCategory(categoryEdited.getCategory());
            }
            add(c);
        }
    }


    public void delete(NewsArticleCategory category) {

        var categories = getAll();
        deleteFile();

        for (var c : categories) {
            if (!c.getId().equals(category.getId())) {
                add(c);
            }
        }
    }


    public boolean deleteById(Long id) {
        var category = getById(id);
        if (category != null) {
            delete(category);
            return true;
        }
        return false;
    }


    public boolean update(NewsArticleCategory category) {
        var categories = getAll();
        deleteFile();
        for (var c : categories) {
            if (c.getId().equals(category.getId())) {
                c.setCategory(category.getCategory());
            }
            add(c);
        }
        return true;
    }
}
