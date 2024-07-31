package com.se4f7.prj301.service;

import java.util.Collection;

import javax.servlet.http.Part;

import com.se4f7.prj301.model.PaginationModel;
import com.se4f7.prj301.model.request.ProductModelRequest;
import com.se4f7.prj301.model.response.ProductModelResponse;

public interface ProductService {
	public boolean create(ProductModelRequest request, Collection<Part> images, String username);

	public boolean update(String id, ProductModelRequest request, Collection<Part> images, String username);

	public boolean deleteById(String id);

	public ProductModelResponse getByPosition(String types);

	public ProductModelResponse getById(String id);

	public PaginationModel filter(String page, String size, String name);

}

