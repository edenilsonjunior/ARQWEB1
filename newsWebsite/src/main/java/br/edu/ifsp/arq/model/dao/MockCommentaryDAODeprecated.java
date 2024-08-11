package br.edu.ifsp.arq.model.dao;

/*
import br.edu.ifsp.arq.model.entity.Commentary;
import br.edu.ifsp.arq.model.entity.User;

import java.util.ArrayList;
import java.util.List;

public class MockCommentaryDAODeprecated extends CommentaryDAO {

    private static MockCommentaryDAODeprecated instance;
    private final List<Commentary> mockCommentaryList = new ArrayList<>();

    private MockCommentaryDAODeprecated() {
        super();
        // Adicionando comentários mock para a notícia com ID 1
        User user1 = new User(1L, "user1", "user1@example.com");
        User user2 = new User(2L, "user2", "user2@example.com");

        mockCommentaryList.add(new Commentary(1L, user1, "Comentário 1 para a notícia 1"));
        mockCommentaryList.add(new Commentary(1L, user2, "Comentário 2 para a notícia 1"));
    }

    public static MockCommentaryDAODeprecated getInstance() {
        if (instance == null) {
            instance = new MockCommentaryDAODeprecated();
        }
        return instance;
    }

    @Override
    public void addCommentary(Commentary comment, Long newsArticleId) {
        comment.setId(newsArticleId);
        mockCommentaryList.add(comment);
    }

    @Override
    public List<Commentary> getCommentary() {
        return new ArrayList<>(mockCommentaryList);
    }

    @Override
    public List<Commentary> getCommentsById(Long newsArticleId) {
        List<Commentary> cmmtsList = new ArrayList<>();
        for (Commentary c : mockCommentaryList) {
            if (c.getId().equals(newsArticleId)) {
                cmmtsList.add(c);
            }
        }
        return cmmtsList;
    }

    @Override
    public void editCommentary(Commentary commentEdited) {
        for (Commentary c : mockCommentaryList) {
            if (c.getId().equals(commentEdited.getId()) && c.getUser().getId().equals(commentEdited.getUser().getId())) {
                c.setText(commentEdited.getText());
            }
        }
    }

    @Override
    public void deleteCommentary(Commentary commentToDelete) {
        mockCommentaryList.removeIf(c -> c.getId().equals(commentToDelete.getId()) && c.getUser().getId().equals(commentToDelete.getUser().getId()));
    }

    public void addCommentary(Commentary commentary, Long newsId, User user) {
        commentary.setId(newsId);
        commentary.setUser(user);
        commentary.setText(commentary.getText());
        mockCommentaryList.add(commentary);
    }
}*/
