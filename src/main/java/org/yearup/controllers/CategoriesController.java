package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.data.mysql.MySqlCategoryDao;
import org.yearup.data.mysql.MySqlProductDao;
import org.yearup.models.Category;
import org.yearup.models.Product;

import java.util.List;

@RestController
// add the annotations to make this a REST controller

@RequestMapping("/categories")
// add the annotation to make this controller the endpoint for the following url http://localhost:8080/categories

@CrossOrigin
// add annotation to allow cross site origin requests

public class CategoriesController
{
    private CategoryDao categoryDao;
    private ProductDao productDao;


    @Autowired
    // create an Autowired controller to inject the categoryDao and ProductDao
    CategoriesController(MySqlCategoryDao mySqlCategoryDao, MySqlProductDao mySqlProductDao){
        this.categoryDao = mySqlCategoryDao;
        this.productDao = mySqlProductDao;

    }

    //--------------------------------------------------------------------------------------------------

    @RequestMapping(method = RequestMethod.GET)
    // add the appropriate annotation for a get action

    public List<Category> getAll()
    {
        return categoryDao.getAllCategories();
        // find and return all categories
    }

    //--------------------------------------------------------------------------------------------------

    @RequestMapping(path= "/{id}", method = RequestMethod.GET)
    // add the appropriate annotation for a get action

    public Category getById(@PathVariable int id)
    {
        Category category = categoryDao.getById(id);

        if (category == null) {

            throw new ResponseStatusException((HttpStatus.NOT_FOUND));

        }
        return category;

        // Fixing bug so if category is null it'll throw instead of returning null to insomnia
    }

    //--------------------------------------------------------------------------------------------------

    @GetMapping("{categoryId}/products")
    // the url to return all products in category 1 would look like this https://localhost:8080/categories/1/products

    public List<Product> getProductsById(@PathVariable int categoryId)
    {
        return productDao.listByCategoryId(categoryId);
        // get a list of product by categoryId
    }

    //--------------------------------------------------------------------------------------------------

    @RequestMapping(method = RequestMethod.POST)
    // add annotation to call this method for a POST action

    @ResponseStatus(HttpStatus.CREATED)
    // Had this error:  "AssertionError: expected 200 to equal 201 | ACTUAL: 200 | EXPECTED: 201"
    // 200 OK - Indicates that the request has succeeded.
    // 201 CREATED - Indicates that the request has succeeded and a new resource has been created as a result.
    // I needed this annotation to make the 201 request

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    // add annotation to ensure that only an ADMIN can call this function

    public Category addCategory(@RequestBody Category category)
    {
        return categoryDao.create(category);
        // insert the category
    }

    //--------------------------------------------------------------------------------------------------

    @RequestMapping(path = "/{id}" , method = RequestMethod.PUT)
    // add annotation to call this method for a PUT (update) action - the url path must include the categoryId

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    // add annotation to ensure that only an ADMIN can call this function

    public void updateCategory(@PathVariable int id, @RequestBody Category category)
    {
        categoryDao.update(id,category);
        // update the category by id
    }

    //--------------------------------------------------------------------------------------------------

    @RequestMapping(path = "/{id}" , method = RequestMethod.DELETE)
    // add annotation to call this method for a DELETE action - the url path must include the categoryId

    @ResponseStatus(HttpStatus.NO_CONTENT)
    // Had this error:  "AssertionError: expected 200 to equal 204 | ACTUAL: 200 | EXPECTED: 204"
    // 200 OK - Indicates that the request has succeeded.
    // 204 NO_CONTENT - The server has fulfilled the request but does not need to return a response body.
    // The server may return the updated meta information.
    // I needed this annotation to make the 204 request

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    // add annotation to ensure that only an ADMIN can call this function

    public void deleteCategory(@PathVariable int id)
    {
        categoryDao.delete(id);
        // delete the category by id
    }
}
