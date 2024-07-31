package com.se4f7.prj301.model.request;


public class ProductModelRequest {
	private String nameMovie;
	private String categoryId;
	private String country;
	private String director;
	private String releaseDate;
	private String description;
	private String images;

	public ProductModelRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
   

	public ProductModelRequest(String nameMovie, String categoryId, String country, String director, String releaseDate,
			String description, String images) {
		super();
		this.nameMovie = nameMovie;
		this.categoryId = categoryId;
		this.country = country;
		this.director = director;
		this.releaseDate = releaseDate;
		this.description = description;
		this.images = images;
	}


	public String getNameMovie() {
		return nameMovie;
	}

	public void setNameMovie(String nameMovie) {
		this.nameMovie = nameMovie;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

}