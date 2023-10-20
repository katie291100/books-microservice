package uk.ac.york.eng2.books.dto;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Column;

@Serdeable
public class BookDTO {
	
	private String title;

	private Integer year;

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


}