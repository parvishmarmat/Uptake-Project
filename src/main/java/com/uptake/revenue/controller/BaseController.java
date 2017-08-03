package com.uptake.revenue.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.uptake.revenue.exception.BadArgumentException;
import com.uptake.revenue.exception.BaseRuntimeException;
import com.uptake.revenue.exception.ResourceNotFoundException;
import com.uptake.revenue.mongo.pagination.PageRequest;
import com.uptake.revenue.util.Constants;

/**
 * This is the base marker controller, to provide any base functionality common
 * to all controllers.
 *
 *@author Parvish Marmat
 */
public abstract class BaseController {

    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";
    public static final String SELF_LINK = "self";
    public static final int PAGE_SIZE = 20;
    public static final int EVERYTHING = 10000;

    /**
     * Helper method to construct page request object
     *
     * @param page
     * @param sort
     * @param size
     * @param sortDir
     * @return
     */
    protected PageRequest getPageRequest(Integer page, Integer size) {
        PageRequest pageRequest = new PageRequest(0, EVERYTHING);
        if ((page != null)) {
            int pageSize = PAGE_SIZE;
            if (size != null) {
                pageSize = size;
            }
            pageRequest = new PageRequest(page.intValue(), pageSize);
        }
        return pageRequest;
    }

    protected PageRequest getPageRequest(String page, String size) {
        PageRequest pageRequest = new PageRequest(0, EVERYTHING);
        if ((page != null) && (StringUtils.isNotEmpty(page.trim()))) {
            int pageNo = Integer.parseInt(page.trim());
            int pageSize = PAGE_SIZE;
            if ((size != null) && (StringUtils.isNotEmpty(size.trim()))) {
                pageSize = Integer.parseInt(size.trim());
            }
            pageRequest = new PageRequest(pageNo, pageSize);
        }
        return pageRequest;
    }

    /**
     * Method to check to bad argument of type {@code String}
     *
     * @param args
     */
    protected void checkArguments(String... args) {
        String[] inArgs = args;

        for (String arg : inArgs) {
            if (arg == null || arg.isEmpty()) {
                BaseRuntimeException e = new BadArgumentException(Constants.BAD_ARGUMENT_MESSAGE);
                e.setCode(Constants.BAD_ARGUMENT_CODE);
                e.setTitle(Constants.BAD_ARGUMENT_TITLE);
                throw e;
            }
        }
    }

    /**
     * Method to check to bad arguments
     *
     * @param args
     */
    protected void checkArguments(Object... args) {
        Object[] inArgs = args;

        for (Object arg : inArgs) {
            if (arg == null) {
                throwBadArgumentException();
            }

            if (arg instanceof String && ((String) arg).isEmpty()) {
                throwBadArgumentException();
            }
        }
    }

    /**
     * Helper method to throw generic {@code BadArgumentException}
     * 
     */
    protected void throwBadArgumentException() {
        BaseRuntimeException e = new BadArgumentException(Constants.BAD_ARGUMENT_MESSAGE);
        e.setCode(Constants.BAD_ARGUMENT_CODE);
        e.setTitle(Constants.BAD_ARGUMENT_TITLE);
        throw e;
    }

    /**
     * Method to throw {@code BaseRuntimeException} for error handling in
     * JsonApiWrapper aspect
     *
     */
    protected void handleResourceNotFound() {
        BaseRuntimeException e = new ResourceNotFoundException(Constants.RESOURCE_NOT_FOUND_MESSAGE);
        e.setCode(Constants.RESOURCE_NOT_FOUND_CODE);
        e.setTitle(Constants.RESOURCE_NOT_FOUND_TITLE);
    }

    /**
     * Access method to get self link from {@code request} object to be embedded
     * in JSON API output
     *
     * @param request
     * @return
     */
    protected String getSelfLink(HttpServletRequest request) {
        return request.getRequestURL().toString();
    }

}
