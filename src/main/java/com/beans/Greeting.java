package com.beans;

/**
 * Represents a greeting duh!
 * @author Administrador
 */
public class Greeting {
	
	public long id;
	public String content;	
	
	public Greeting (long id, String content) {
		this.id=id;
		this.content=content;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getId() {
		return id;
	}
	public String getContent() {
		return content;
	}	
	

}
