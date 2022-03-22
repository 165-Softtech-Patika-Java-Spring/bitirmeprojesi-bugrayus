package com.bitirmeprojesibugrayus.service;

import com.bitirmeprojesibugrayus.model.BaseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public abstract class BaseEntityService<E extends BaseModel, D extends JpaRepository<E, Long>> {

    private static final Integer DEFAULT_PAGE = 0;
    private static final Integer DEFAULT_SIZE = 10;

    private final D dao;

    private AuthenticationService authenticationService;

    /**
     * Circular dependency
     */
    @Autowired
    public void setAuthenticationService(@Lazy AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public List<E> findAll() {
        return dao.findAll();
    }

    public Optional<E> findById(Long id) {
        return dao.findById(id);
    }

    public E save(E entity) {

        entity = dao.save(entity);

        return entity;
    }

    public void delete(E entity) {
        dao.delete(entity);
    }

    public E getByIdWithControl(Long id) {

        Optional<E> entityOptional = findById(id);

        E entity;
        if (entityOptional.isPresent()) {
            entity = entityOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return entity;
    }

    public boolean existsById(Long id) {
        return dao.existsById(id);
    }

    public D getDao() {
        return dao;
    }

    public Long getCurrentCustomerId() {
        Long currentCustomerId = authenticationService.getCurrentUserId();
        return currentCustomerId;
    }

    protected PageRequest getPageRequest(Optional<Integer> pageOptional, Optional<Integer> sizeOptional) {
        Integer page = getPage(pageOptional);
        Integer size = getSize(sizeOptional);

        PageRequest pageRequest = PageRequest.of(page, size);
        return pageRequest;
    }

    protected Integer getSize(Optional<Integer> sizeOptional) {

        Integer size = DEFAULT_SIZE;
        if (sizeOptional.isPresent()) {
            size = sizeOptional.get();
        }
        return size;
    }

    protected Integer getPage(Optional<Integer> pageOptional) {

        Integer page = DEFAULT_PAGE;
        if (pageOptional.isPresent()) {
            page = pageOptional.get();
        }
        return page;
    }
}
