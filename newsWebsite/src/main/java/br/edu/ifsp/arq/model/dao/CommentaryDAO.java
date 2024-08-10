package br.edu.ifsp.arq.model.dao;

import br.edu.ifsp.arq.model.entity.Commentary;
import br.edu.ifsp.arq.model.entity.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CommentaryDAO {
    private static CommentaryDAO instance;
    private static final String fileCSV =  "/data/commentaryData.csv";

    private CommentaryDAO() {}

    public static CommentaryDAO getInstance() {
        if (instance == null) {
            instance = new CommentaryDAO();
        }
        return instance;
    }

    public void addCommentary(Commentary comment, Long newsArticleId) {

        var f = new File(fileCSV);

        try {
            FileWriter fw = new FileWriter(f, true);
            System.out.println(f.getAbsolutePath());
            PrintWriter pw = new PrintWriter(fw);
            comment.setId(newsArticleId);
            pw.println(comment);
            pw.close();
            fw.close();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Commentary> getCommentary() {
        List<Commentary> commentaryList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(new File(fileCSV)))) {
            String row;
            while ((row = reader.readLine()) != null) {
                String[] parts = row.split(";");
                if (parts.length == 4) {
                    Long id = Long.parseLong(parts[0]);
                    Long userId = Long.parseLong(parts[1]);
                    User user = getUserById(userId);
                    String text = parts[3];
                    Commentary comment = new Commentary(id, user, text);
                    commentaryList.add(comment);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return commentaryList;
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

    public List<Commentary> getCommentsById(Long newsArticleId) {
        List<Commentary> commentaryList = getCommentary();
        List<Commentary> cmmtsList = new ArrayList<>();
        for (Commentary c : commentaryList) {
            if (c.getId() == newsArticleId) {
                cmmtsList.add(c);
            }
        }
        return cmmtsList;
    }

    public void editCommentary(Commentary commentEdited) {
        List<Commentary> commentaryList = getCommentary();
        deleteFile();

        for (Commentary c: commentaryList) {
            if (c.getId() == commentEdited.getId() && c.getUser().getId() == commentEdited.getUser().getId()) {
                c.setText(commentEdited.getText());
            }
            addCommentary(c, c.getId());
        }
    }

    public void deleteCommentary(Commentary commentToDelete) {
        List<Commentary> commentaryList = getCommentary();
        deleteFile();

        for (Commentary c : commentaryList) {
            if (c.getId() == commentToDelete.getId() && c.getUser().getId() == commentToDelete.getUser().getId()) {
                addCommentary(c, c.getId());
            }
        }
    }

    private User getUserById(Long id) {
       var user = UserDAO.getInstance();
       return user.getUserById(id);
    }
}
