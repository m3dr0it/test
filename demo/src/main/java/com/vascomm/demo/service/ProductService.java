package com.vascomm.demo.service;

import com.vascomm.demo.exception.NotFoundException;
import com.vascomm.demo.model.BaseResponse;
import com.vascomm.demo.model.PageableResponse;
import com.vascomm.demo.model.Product;
import com.vascomm.demo.model.User;
import com.vascomm.demo.repository.ProductRepository;
import com.vascomm.demo.repository.UserRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService implements ProductInterface {
    ProductRepository repository;

    @Autowired
    EntityManager em;

    public ProductService(ProductRepository productRepository) {
        this.repository = productRepository;
    }

    @Override
    public void add(Map<String, String> newProduct) {
        Product product = new Product();
        product.setName(newProduct.get("name"));
        product.setCategory(newProduct.get("category"));
        product.setDescription(newProduct.get("description"));
        repository.save(product);
    }

    @Override
    public void update(Map<String, String> payload) throws NotFoundException {
        String id = payload.get("id");
        Optional<Product> productOptional = repository.findById(id);
        if(productOptional.isEmpty()){
            throw new NotFoundException(id);
        }
        Product product = productOptional.get();
        product.setDescription(payload.get("description"));
        product.setName(payload.get("name"));
        product.setCategory(payload.get("category"));
        repository.save(product);
    }

    @Override
    public BaseResponse getList(Pageable pageable, Map<String,String> params) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> q = cq.from(Product.class);

        if(params.containsKey("name")){
            Predicate predicate = cb.like(cb.lower(q.get("name")), "%" + params.get("name") + "%");
            cq.where(predicate);
        }

        if(params.containsKey("description")){
            Predicate predicate = cb.like(cb.lower(q.get("address")),params.get("address"));
            cq.where(predicate);
        }

        if(params.containsKey("category")){
            Predicate predicate = cb.equal(cb.lower(q.get("phoneNumber")),params.get("phoneNumber"));
            cq.where(predicate);
        }

        TypedQuery<Product> query = em.createQuery(cq)
                .setFirstResult(pageable.getPageNumber())
                .setMaxResults(pageable.getPageSize());
        return new BaseResponse(200,"Success",query.getResultList());
    }

}
