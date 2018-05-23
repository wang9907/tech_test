package com.summer.tech.jws.rs.javase.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Book {
	
	private Long bookId;
	private String bookName;
	private String publisher;
	
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
}