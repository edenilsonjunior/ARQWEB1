package br.edu.ifsp.arq.model.entity;

public class Commentary {

	private Long id;
	private User user;
	private String title;
	private String text;
	
	public Commentary(Long id, User user, String title, String text) {
		super();
		this.id = id;
		this.user = user;
		this.title = title;
		this.text = text;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
