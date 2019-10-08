package com.aulapds.code.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.aulapds.code.dto.CategoryDTO;
import com.aulapds.code.dto.ProductCategoriesDTO;
import com.aulapds.code.dto.ProductDTO;
import com.aulapds.code.entities.Category;
import com.aulapds.code.entities.Product;
import com.aulapds.code.repositories.CategoryRepository;
import com.aulapds.code.repositories.ProductRepository;
import com.aulapds.code.services.exceptions.DatabaseException;
import com.aulapds.code.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private CategoryRepository  CategoryRepository;
	
	public List<ProductDTO> findAll(){
		
		List<Product> list = repository.findAll();
		return list.stream().map(e -> new ProductDTO(e)).collect(Collectors.toList());
	}
	
	public ProductDTO findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		Product entity= obj.orElseThrow(()-> new ResourceNotFoundException(id));
		return new ProductDTO(entity);
	}
	
	@Transactional 
	public ProductDTO insert(ProductCategoriesDTO dto) {
		Product entity =  dto.toEntity();
		setProductCategories(entity, dto.getCategories());
		entity = repository.save(entity);
		return new ProductDTO(entity);
	}
	
	@Transactional
	public ProductDTO update(Long id, ProductCategoriesDTO dto) {
		try {
		Product entity = repository.getOne(id);
		updateData(entity, dto);
		entity = repository.save(entity);
		return new ProductDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void setProductCategories(Product entity, List<CategoryDTO> categories) {
		entity.getCategories().clear();
		for (CategoryDTO dto : categories){
			Category category = CategoryRepository.getOne(dto.getId());
			entity.getCategories().add(category);
		}
	}
	
	private void updateData(Product entity, ProductCategoriesDTO dto) {
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
		entity.setImgUrl(dto.getImgUrl());
		if (dto.getCategories() != null && dto.getCategories().size() > 0) {
			setProductCategories(entity, dto.getCategories());
		}
	}
	
	public void delete(Long id) {
		try {
		repository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}catch (DataIntegrityViolationException e){
			throw new DatabaseException(e.getMessage());
			}
	}
}