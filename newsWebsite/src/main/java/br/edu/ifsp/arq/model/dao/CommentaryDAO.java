package br.edu.ifsp.arq.model.dao;

import br.edu.ifsp.arq.model.entity.Commentary;
import br.edu.ifsp.arq.model.entity.User;

import java.util.ArrayList;
import java.util.List;

public class CommentaryDAO extends AbstractDAO<Commentary> {
    private static CommentaryDAO instance;
    private static final String fileCSV = BASE_PATH +"/commentaryData.csv";

    private CommentaryDAO() {
        super();
    }

    public static CommentaryDAO getInstance() {
        if (instance == null) {
            instance = new CommentaryDAO();
        }
        Utils.createDirectoryIfNotExists(BASE_PATH, fileCSV);
        return instance;
    }

    @Override
    protected String getFilePath() {
        return fileCSV;
    }


    @Override
    protected Commentary parse(String[] parts) {
        Long id = Long.parseLong(parts[0]);
        Long userId = Long.parseLong(parts[1]);
        User user = getUserById(userId);
        String text = parts[3];
        return new Commentary(id, user, text);
    }

    @Override
    protected String toCsv(Commentary entity) {
        return entity.toString();
    }

    @Override
    protected Long getId(Commentary entity) {
        return entity.getId();
    }

    public List<Commentary> getCommentsById(Long newsArticleId) {

        var commentaryList = getAll();
        List<Commentary> cmmtsList = new ArrayList<>();

        commentaryList.stream()
                .filter(c -> c.getId().equals(newsArticleId))
                .forEach(c -> cmmtsList.add(c));

        return cmmtsList;
    }

    public void editCommentary(Commentary commentEdited) {
        var commentaryList = getAll();
        deleteFile();

        for (Commentary c : commentaryList) {
            if (c.getId().equals(commentEdited.getId()) && c.getUser().getId().equals(commentEdited.getUser().getId())) {
                c.setText(commentEdited.getText());
            }
            add(c);
        }
    }

    public void deleteCommentaryByNewsId(Long newsId) {
        List<Commentary> commentaryList = getAll();
        deleteFile();

        for (Commentary c : commentaryList) {
            if (!c.getId().equals(newsId)) {
                add(c);
            }
        }
    }

    private User getUserById(Long id) {
        var user = UserDAO.getInstance();
        return user.getUserById(id);
    }
}
