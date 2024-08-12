
package br.edu.ifsp.arq.model.entity;

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
	private NewsArticleCategory category;
	private final List<String> images;
	private final List<Commentary> comments;
	
	public NewsArticle(Long id, String title, String author, String publishDate, String source, String summary, String text, NewsArticleCategory category, List<String> images, List<Commentary> comments) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.publishDate = publishDate;
		this.source = source;
		this.summary = summary;
		this.text = text;
		this.category = category;
		this.images = new ArrayList<>(images);
		this.comments = new ArrayList<>(comments);
	}

	public NewsArticle(String title, String author, String publishDate, String source, String summary, String text, NewsArticleCategory category, List<String> images) {
		this.title = title;
		this.author = author;
		this.publishDate = publishDate;
		this.source = source;
		this.summary = summary;
		this.text = text;
		this.category = category;
		this.images = new ArrayList<>();
		if (!(images.isEmpty())) {
			this.images.add(0, images.get(0));
			this.images.add(1, images.get(1));
		}
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

	public NewsArticleCategory getCategory() {
		return category;
	}

	public void setCategory(NewsArticleCategory category) {
		this.category = category;
	}

	public void addImage(int index, String img) {
		 this.images.add(index, img);
	}

	public List<String> getImages() {
		return images;
	}

	public List<Commentary> getComments() {
		return comments;
	}

	public void setImages(List<String> images) {
		this.images.clear();
		this.images.addAll(images);
	}


	public String imagesToString() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < images.size(); i++) {

			if (i == images.size() - 1) {
				sb.append(images.get(i));
			} else {
				sb.append(images.get(i) + ";");
			}
		}

		return sb.toString();
	}

	@Override
	public String toString() {
		return id + ";" + title + ";" + author + ";" + publishDate + ";" + source + ";" + summary + ";" + text + ";" + category.getId() + ";" + imagesToString();
	}
}
