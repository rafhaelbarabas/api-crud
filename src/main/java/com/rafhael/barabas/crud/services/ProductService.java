package com.rafhael.barabas.crud.services;

import com.rafhael.barabas.crud.entities.Product;
import com.rafhael.barabas.crud.exception.ResourceNotFoundException;
import com.rafhael.barabas.crud.repositories.ProductRepository;
import com.rafhael.barabas.crud.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    private ProductVO convertEntityToVO(Product product) {
        return ProductVO.create(product);
    }

    private Product convertVOToEntity(ProductVO productVo) {
        return Product.create(productVo);
    }

    public ProductVO create(ProductVO productVO) {
        Product save = repository.save(convertVOToEntity(productVO));
        return convertEntityToVO(save);
    }

    public Page<ProductVO> findAll(Pageable pageable) {
        var page = repository.findAll(pageable);
        return page.map(this::convertEntityToVO);
    }

    public ProductVO findById(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this ID"));
        return convertEntityToVO(entity);
    }

    public ProductVO update(ProductVO productVO){
        findById(productVO.getId());
        Product save = repository.save(convertVOToEntity(productVO));
        return convertEntityToVO(save);
    }

    public ProductVO update(Long id){
        ProductVO productVO = findById(id);
        Product save = repository.save(convertVOToEntity(productVO));
        return convertEntityToVO(save);
    }

    public void delete(Long id){
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this ID"));
        repository.delete(entity);
    }

}
