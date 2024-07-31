package com.se4f7.prj301.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.se4f7.prj301.constants.ErrorMessage;
import com.se4f7.prj301.enums.StatusEnum;
import com.se4f7.prj301.model.PaginationModel;
import com.se4f7.prj301.model.request.ProductModelRequest;
import com.se4f7.prj301.model.response.ProductModelResponse;

import com.se4f7.prj301.utils.DBUtil;

public class ProductRepository {
	private static final String INSERT_SQL = "INSERT INTO ads (images, createdBy, updatedBy, width, height, position, url) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_SQL = "UPDATE ads SET images = ?, updatedBy = ?, width = ?, height = ?, position = ?, url = ? WHERE id = ?";
	private static final String GET_BY_POSITION_SQL = "SELECT * FROM ads WHERE position = ? ";
	private static final String GET_BY_ID_SQL = "SELECT * FROM ads WHERE id = ?";
	private static final String DELETE_BY_ID_SQL = "DELETE FROM ads  WHERE id= ? ";
	private static final String SEARCH_LIST_SQL = "SELECT * FROM ads WHERE position LIKE ? LIMIT ? OFFSET ?";
	private static final String COUNT_BY_NAME_SQL = "SELECT COUNT(id) AS totalRecord FROM ads WHERE position LIKE ?";

	public boolean create(ProductModelRequest request, String username) {
		// Open connection and set SQL query into PreparedStatement.
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {
			// Set parameters.
			preparedStatement.setString(1, request.getImages());;
			preparedStatement.setString(2, username);
			preparedStatement.setString(3, username);
			preparedStatement.setString(4, request.getNameMovie());
			preparedStatement.setString(5, request.getReleaseDate());
			preparedStatement.setString(6, request.getCategoryId());
			preparedStatement.setString(7, request.getCountry());
			preparedStatement.setString(8, request.getDescription());
			preparedStatement.setString(9, request.getDirector());
			
			
			
			// Show SQL query.
			System.out.println(preparedStatement);
			// Execute query.
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SQL_ERROR + e.getMessage());
		}
	}

	public boolean update(Long id, ProductModelRequest request, String username) {
		// Open connection and set SQL query into PreparedStatement.
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
			// Set parameters.
			preparedStatement.setString(1, request.getImages());;
			preparedStatement.setString(2, username);
			preparedStatement.setString(3, request.getNameMovie());
			preparedStatement.setString(4, request.getReleaseDate());
			preparedStatement.setString(5, request.getCategoryId());
			preparedStatement.setString(6, request.getCountry());
			preparedStatement.setString(7, request.getDescription());
			preparedStatement.setString(8, request.getDirector());
		
			// Show SQL query.
			System.out.println(preparedStatement);
			// Execute query.
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SQL_ERROR + e.getMessage());
		}
	}

	public ProductModelResponse getById(Long id) {
		// Open connection and set SQL query into PreparedStatement.
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_SQL)) {
			// Set parameters.
			preparedStatement.setLong(1, id);
			// Show SQL query.
			System.out.println(preparedStatement);
			// Execute query.
			ResultSet rs = preparedStatement.executeQuery();
			if (!rs.isBeforeFirst()) {
				return null;
			}
			ProductModelResponse response = new ProductModelResponse();
			while (rs.next()) {
				response.setId(rs.getLong("id"));
				response.setImages(rs.getString("images"));
				response.setCreatedDate(rs.getString("createdDate"));
				response.setUpdatedDate(rs.getString("updatedDate"));
				response.setCreatedBy(rs.getString("createdBy"));
				response.setUpdatedBy(rs.getString("updatedBy"));
				response.setNameMovie("nameMovie");
				response.setCategoryId("categoryId");
				response.setCountry("country");
				response.setDirector("director");
				response.setReleaseDate("releaseDate");
				response.setDescription("description");
			}
			return response;
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SQL_ERROR + e.getMessage());
		}
	}

	public ProductModelResponse getByPosition(String position) {
		// Open connection and set SQL query into PreparedStatement.
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_POSITION_SQL)) {
			// Set parameters.
			preparedStatement.setString(1, position);
			// Show SQL query.
			System.out.println(preparedStatement);
			// Execute query.
			ResultSet rs = preparedStatement.executeQuery();
			if (!rs.isBeforeFirst()) {
				return null;
			}
			ProductModelResponse response = new ProductModelResponse();
			while (rs.next()) {
				response.setId(rs.getLong("id"));
				response.setImages(rs.getString("images"));
				response.setCreatedDate(rs.getString("createdDate"));
				response.setUpdatedDate(rs.getString("updatedDate"));
				response.setCreatedBy(rs.getString("createdBy"));
				response.setUpdatedBy(rs.getString("updatedBy"));
				response.setNameMovie("nameMovie");
				response.setCategoryId("categoryId");
				response.setCountry("country");
				response.setDirector("director");
				response.setReleaseDate("releaseDate");
				response.setDescription("description");
			}
			return response;
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SQL_ERROR + e.getMessage());
		}
	}

	public boolean deleteById(Long id) {
		// Open connection and set SQL query into PreparedStatement.
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID_SQL)) {
			// Set parameters.
			preparedStatement.setLong(1, id);
			// Show SQL query.
			System.out.println(preparedStatement);
			// Execute query.
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SQL_ERROR + e.getMessage());
		}
	}

	public PaginationModel filterByName(int page, int size, String name) {
		// Open connection and set SQL query into PreparedStatement.
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement stmtSelect = connection.prepareStatement(SEARCH_LIST_SQL);
				PreparedStatement stmtCount = connection.prepareStatement(COUNT_BY_NAME_SQL)) {
			// Set parameters.
			stmtSelect.setString(1, name != null ? "%" + name + "%" : "%%");
			stmtSelect.setInt(2, size);
			stmtSelect.setInt(3, page * size);
			// Show SQL query.
			System.out.println(stmtSelect);
			// Execute query.
			// Select records.
			ResultSet rs = stmtSelect.executeQuery();
			System.out.println(123);
			List<ProductModelResponse> results = new ArrayList<ProductModelResponse>();
			while (rs.next()) {
				ProductModelResponse response = new ProductModelResponse();
				response.setId(rs.getLong("id"));
				response.setImages(rs.getString("images"));
				response.setCreatedDate(rs.getString("createdDate"));
				response.setUpdatedDate(rs.getString("updatedDate"));
				response.setCreatedBy(rs.getString("createdBy"));
				response.setUpdatedBy(rs.getString("updatedBy"));
				response.setNameMovie("nameMovie");
				response.setCategoryId("categoryId");
				response.setCountry("country");
				response.setDirector("director");
				response.setReleaseDate("releaseDate");
				response.setDescription("description");
				results.add(response);
			}

			// Count records;
			stmtCount.setString(1, name != null ? "%" + name + "%" : "%%");
			ResultSet rsCount = stmtCount.executeQuery();
			int totalRecord = 0;
			while (rsCount.next()) {
				totalRecord = rsCount.getInt("totalRecord");
			}
			return new PaginationModel(page, size, totalRecord, results);
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SQL_ERROR + e.getMessage());
		}
	}
}
