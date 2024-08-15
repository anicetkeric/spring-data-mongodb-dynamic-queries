package com.tutorial.springdatamongodbdynamicqueries.service.support;

import com.tutorial.springdatamongodbdynamicqueries.helpers.FilterBuilder;
import com.tutorial.springdatamongodbdynamicqueries.helpers.FilterSortRegister;
import com.tutorial.springdatamongodbdynamicqueries.repository.ResourceRepository;
import com.tutorial.springdatamongodbdynamicqueries.repository.support.GenericFilterCriteriaBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

/**
 * @author AEK
 */
@Slf4j
public class GenericServiceImpl<T, D extends Serializable> implements GenericService<T, D> {

    @Autowired
    private ResourceRepository<T, D> repository;

    @Autowired
    private FilterBuilder filterBuilder;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> getAll(FilterSortRegister request) {
        var search = filterBuilder.getFilters(request.filterAnd(), request.filterOr());

        var query = new GenericFilterCriteriaBuilder(search).buildQuery();

        return repository.findAllByQuery(query);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<T> getPage(FilterSortRegister request) {
        var search = filterBuilder.getFilters(request.filterAnd(), request.filterOr());
        var pageable = filterBuilder.getPageable(request.size(), request.page(), request.orders());

        var query = new GenericFilterCriteriaBuilder(search).buildQuery();

        return repository.findAll(query, pageable);
    }
}
