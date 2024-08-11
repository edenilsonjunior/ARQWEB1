package br.edu.ifsp.arq.model.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAO<T> {

    protected abstract String getFilePath();

    protected abstract T parse(String[] parts);

    protected abstract String toCsv(T entity);

    protected abstract Long getId(T entity);


    public List<T> getAll() {

        List<T> entities = new ArrayList<>();
        String filePath = getFilePath();

        try (BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)))) {
            String row;
            while ((row = reader.readLine()) != null) {
                String[] parts = row.split(";");
                entities.add(parse(parts));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entities;
    }

    public boolean add(T entity) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(new File(getFilePath()), true))) {
            pw.println(toCsv(entity));
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public void deleteFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(new File(getFilePath()), false))) {
            pw.print("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public T getById(Long id) {
        return getAll().stream()
                .filter(entity -> getId(entity).equals(id))
                .findFirst()
                .orElse(null);
    }
}
