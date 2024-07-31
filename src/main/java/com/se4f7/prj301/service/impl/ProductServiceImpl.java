package com.se4f7.prj301.service.impl;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.Part;

import com.se4f7.prj301.constants.ErrorMessage;
import com.se4f7.prj301.model.PaginationModel;
import com.se4f7.prj301.model.request.ProductModelRequest;
import com.se4f7.prj301.model.response.ProductModelResponse;
import com.se4f7.prj301.repository.ProductRepository;
import com.se4f7.prj301.service.ProductService; 
import com.se4f7.prj301.utils.FileUtil; 
import com.se4f7.prj301.utils.StringUtil;
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository = new ProductRepository();
    @Override
    public boolean create(ProductModelRequest request, Collection<Part> images, String username) {
        // Validate title is exists.
        ProductModelResponse oldAds = productRepository.getByPosition(request.getDescription());
        if (oldAds != null) {
            throw new RuntimeException(ErrorMessage.NAME_IS_EXISTS);
        }
        // Handle file uploads
        if (images != null && !images.isEmpty()) {
            List<String> fileNames = images.stream()
                .filter(part -> part.getSize() > 0) // Ensure the file part is not empty
                .map(FileUtil::saveFile) // Save the file and get the file name
                .collect(Collectors.toList());
            // Set filenames saved to Model. Assuming images can be a list of filenames
            request.setImages(String.join(",", fileNames)); // Join filenames with comma or any other delimiter
        }
        // Call repository to save Ads
        return productRepository.create(request, username);
    }

    @Override
    public boolean update(String id, ProductModelRequest request, Collection<Part> images, String username) {
        // Parse String to Long.
        Long idNumber = StringUtil.parseLong("id", id);
        // Get old Ads.
        ProductModelResponse oldAds = productRepository.getById(idNumber);
        if (oldAds == null) {
            throw new RuntimeException(ErrorMessage.RECORD_NOT_FOUND);
        }
        // Compare is title change.
        if (!request.getDescription().equalsIgnoreCase(oldAds.getDescription())) {
            // Compare new title with other name in database.
            ProductModelResponse otherAds = productRepository.getByPosition(request.getDescription());
            if (otherAds != null) {
                throw new RuntimeException(ErrorMessage.NAME_IS_EXISTS);
            }
        }
        // Handle file uploads
        if (images != null && !images.isEmpty()) {
            // Delete old images if exist
            if (oldAds.getImages() != null) {
                String[] oldImageFiles = oldAds.getImages().split(",");
                for (String oldImage : oldImageFiles) {
                    FileUtil.removeFile(oldImage);
                }
            }
            // Save new images
            List<String> fileNames = images.stream()
                .filter(part -> part.getSize() > 0)
                .map(FileUtil::saveFile)
                .collect(Collectors.toList());
            request.setImages(String.join(",", fileNames));
        } else {
            // If no new images, reuse old ones
            request.setImages(oldAds.getImages());
        }

        // Call repository to update Ads
        return productRepository.update(idNumber, request, username);
    }

    @Override
    public boolean deleteById(String id) {
        Long idNumber = StringUtil.parseLong("id", id);
        ProductModelResponse oldAds = productRepository.getById(idNumber);
        if (oldAds == null) {
            throw new RuntimeException(ErrorMessage.RECORD_NOT_FOUND);
        }

        // Delete old images if exist
        if (oldAds.getImages() != null) {
            String[] imageFiles = oldAds.getImages().split(",");
            for (String image : imageFiles) {
                FileUtil.removeFile(image);
            }
        }
        return productRepository.deleteById(idNumber);
    }

    @Override
    public ProductModelResponse getByPosition(String position) {
        ProductModelResponse oldAds = productRepository.getByPosition(position);
        if (oldAds == null) {
            throw new RuntimeException(ErrorMessage.RECORD_NOT_FOUND);
        }
        return oldAds;
    }

    @Override
    public ProductModelResponse getById(String id) {
        Long idNumber = StringUtil.parseLong("id", id);
        ProductModelResponse oldAds = productRepository.getById(idNumber);
        if (oldAds == null) {
            throw new RuntimeException(ErrorMessage.RECORD_NOT_FOUND);
        }
        return oldAds;
    }
    
    @Override
    public PaginationModel filter(String page, String size, String name) {
        int pageNumber = StringUtil.parseInt("Page", page);
        int sizeNumber = StringUtil.parseInt("Size", size);
        return productRepository.filterByName(pageNumber, sizeNumber, name);
    }
}
