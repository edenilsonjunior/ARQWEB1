
package br.edu.ifsp.arq.model.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList; 

public class NewsArticle {

	private Long id;
	private String title;
	private String author;
	private String publishDate;
	private String source;
	private String summary;
	private String text;
	private final List<String> images;
	private final List<Commentary> comments;
	
	public NewsArticle(Long id, String title, String author, String publishDate, String source, String summary, String text, List<String> images) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.publishDate = publishDate;
		this.source = source;
		this.summary = summary;
		this.text = text;
		this.images = new ArrayList<>(images);
		this.comments = new ArrayList<>();
	}

	public NewsArticle(String title, String author, String publishDate, String source, String summary, String text, List<String> images) {
		this.title = title;
		this.author = author;
		this.publishDate = publishDate;
		this.source = source;
		this.summary = summary;
		this.text = text;
		this.images = new ArrayList<>(images);
		this.comments = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getPublishDate() {
		return publishDate;
	}
	
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	public String getSummary() {
		return summary;
	}
	
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}

	public void addImage(int index, String img) {
		 this.images.add(index, img);
	}

	public String imagesToString() {
		StringBuilder sb = new StringBuilder();

		for (String img : images) {
			sb.append(";").append(img);
		}

		return sb.toString();
	}

	public String commentsToString() {
		StringBuilder sb = new StringBuilder();

		for (Commentary c : comments) {
			sb.append(id);
			sb.append(";").append(c.toString());
		}

		return sb.toString();
	}

	@Override
	public String toString() {
		return id + ";" + title + ";" + author + ";" + publishDate + ";" + source + ";" + summary + ";" + text + imagesToString();
	}
}
