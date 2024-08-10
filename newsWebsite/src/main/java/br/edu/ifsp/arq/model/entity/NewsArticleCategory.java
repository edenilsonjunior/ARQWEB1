package br.edu.ifsp.arq.model.entity;

public class NewsArticleCategory {

	private Long id;
	private String category;

	public NewsArticleCategory(String category) {
		this.category = category;
	}

	public NewsArticleCategory(Long id, String category) {
		this.id = id;
		this.category = category;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return id + ";" + category;
	}
}
