package com.se4f7.prj301.model.response;
import com.se4f7.prj301.enums.ProductStatusEnum;
import com.se4f7.prj301.model.BaseModel;
	public class ProductModelResponse extends BaseModel {
		private String images;
		private ProductStatusEnum status;
		private String nameMovie;
		private String categoryId;
		private String country;
		private String director;
		private String releaseDate;
		private String description;
		

		public ProductModelResponse() {
			super();
			// TODO Auto-generated constructor stub
		}
	   
		public ProductModelResponse(String images, ProductStatusEnum status, String nameMovie, String categoryId,
				String country, String director, String releaseDate, String description) {
			super();
			this.images = images;
			this.status = status;
			this.nameMovie = nameMovie;
			this.categoryId = categoryId;
			this.country = country;
			this.director = director;
			this.releaseDate = releaseDate;
			this.description = description;
		}

		public String getImages() {
			return images;
		}

		public void setImages(String images) {
			this.images = images;
		}

		public ProductStatusEnum getStatus() {
			return status;
		}

		public void setStatus(ProductStatusEnum status) {
			this.status = status;
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

    
	}
