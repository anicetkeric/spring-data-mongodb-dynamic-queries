package com.tutorial.springdatamongodbdynamicqueries.service.support;

import com.tutorial.springdatamongodbdynamicqueries.helpers.FilterSortRegister;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

/**
 * @param <T> Entity class
 * @param <D> Entity Id Type
 */
public interface GenericService<T, D extends Serializable> {

    /**
     * Get all documents
     *
     * @return list of document T
     */
    List<T> getAll();

    /**
     * Get all data for document and querye
     *
     * @param request pageable param
     * @return Page of document T
     */
    List<T> getAll(FilterSortRegister request);

    /**
     * Get all paginate data for document
     *
     * @param request pageable param
     * @return Page of document T
     */
    List<T> getAllSort(FilterSortRegister request);

    /**
     * Get all custom paginate data for document T
     *
     * @param request pageable param
     * @return Page of document T
     */
    Page<T> getPage(FilterSortRegister request);

}
