package com.uptake.revenue.mongo.pagination;

import com.uptake.revenue.util.Constants;

/**
 * Basic Java Bean implementation of {@code Pageable}.
 * 
 * @author Parvish Marmat
 */
public class PageRequest {

    private final int page;
    private final int size;

    /**
     * Creates a new null-safe {@link PageRequest PageRequest} where pages are
     * <b>ONE</b> indexed. Thus providing 1 for {@code page} will return the
     * first page. Passing {@code null} values for {@code page} and {@code size}
     * will default to first page and size of
     * {@link com.fixstream.meridian.entities.enums.ElasticSearchSizeEnum#DEFAULT_PAGE_SIZE
     * ElasticSearchSizeEnum.DEFAULT_PAGE_SIZE} respectively.<br>
     * <br>
     * Note: For <b>ZERO</b> based indexing use the constructor
     * {@link #PageRequest(int, int) PageRequest(int, int)}
     * 
     * @param page
     *            - must not be less than <b>one</b>
     * @param size
     *            - must not be less than <b>zero</b> (can be zero for <b>aggregation</b> scenario)
     */
    public PageRequest(Integer page, Integer size) {
        this.page = page == null ? 0
                                 : page - 1;
        this.size = size == null ? Constants.DEFAULT_PAGE_SIZE
                                 : size;

        if (this.page < 0)
            throw new IllegalArgumentException("Page index must not be less than one!");
        if (this.size < 0)
            throw new IllegalArgumentException("Page size must not be less than zero!");
    }

    /**
     * Creates a new {@link AbstractPageRequest}. Pages are zero indexed, thus providing 0 for {@code page} will return
     * the first page.
     * 
     * @param page
     *            must not be less than zero.
     * @param size
     *            must not be less than one.
     */
    public PageRequest(int page, int size) {

        if (page < 0) {
            throw new IllegalArgumentException("Page index must not be less than zero!");
        }

        if (size < 1) {
            throw new IllegalArgumentException("Page size must not be less than one!");
        }

        this.page = page;
        this.size = size;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#getPageSize()
     */
    public int getPageSize() {
        return size;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#getPageNumber()
     */
    public int getPageNumber() {
        return page;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#getOffset()
     */
    public int getOffset() {
        return page * size;
    }

}