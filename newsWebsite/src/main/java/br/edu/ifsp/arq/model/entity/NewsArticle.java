
package br.edu.ifsp.arq.model.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList; 

public class NewsArticle {

	private String title;
	private String author;
	private LocalDate publishDate;
	private String source;
	private String summary;
	private String text;
	
	private List<String> images;
	private List<Commentary> comments;
	
	public NewsArticle(String title, String author, LocalDate publishDate, String source, String summary, String text) {
		super();
		this.title = title;
		this.author = author;
		this.publishDate = publishDate;
		this.source = source;
		this.summary = summary;
		this.text = text;
		this.images = new ArrayList<>();
		this.comments = new ArrayList<>();
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
	
	public LocalDate getPublishDate() {
		return publishDate;
	}
	
	public void setPublishDate(LocalDate publishDate) {
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

	public void addImage(String image) {
		this.images.add(image);
	}
	
	public void addCommentary(Commentary commentary) {
		this.comments.add(commentary);
	}
}
