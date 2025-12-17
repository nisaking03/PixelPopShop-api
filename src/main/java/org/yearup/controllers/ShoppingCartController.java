package org.yearup.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.ProductDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.data.UserDao;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import org.yearup.models.User;

import java.security.Principal;
import java.security.PublicKey;

@RestController
// convert this class to a REST controller

@PreAuthorize("hasRole('ROLE_USER', 'ROLE_ADMIN')")
// only logged-in users should have access to these actions

@RequestMapping("cart")
public class ShoppingCartController{

    // a shopping cart requires
    private ShoppingCartDao shoppingCartDao;
    private UserDao userDao;
    private ProductDao productDao;

    //GET CART------------------------------------------------------------------------------------------

    // each method in this controller requires a Principal object as a parameter
    public ShoppingCart getCart(Principal principal){

        try
        {
            // get the currently logged-in username
            String userName = principal.getName();
            // find database user by userId
            User user = userDao.getByUserName(userName);
            int userId = user.getId();

            shoppingCartDao.getByUserId(userId);
            // use the shoppingCartDao to get all items in the cart and return the cart

            return null;
        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

    //ADD PRODUCT---------------------------------------------------------------------------------------

    @RequestMapping(method = RequestMethod.POST)
    // add a POST method to add a product to the cart - the url should be
    // https://localhost:8080/cart/products/15 (15 is the productId to be added

    public ShoppingCart addProduct(Principal principal, @PathVariable ShoppingCartItem shoppingCartItem){

        shoppingCartDao.addItem(shoppingCartItem);
        return null;
    }

    //UPDATE CART---------------------------------------------------------------------------------------

    @RequestMapping(method = RequestMethod.PUT)
    // add a PUT method to update an existing product in the cart - the url should be
    // https://localhost:8080/cart/products/15 (15 is the productId to be updated)
    // the BODY should be a ShoppingCartItem - quantity is the only value that will be updated

    public ShoppingCart updateCart (Principal principal, @PathVariable ShoppingCartItem shoppingCartItem){

        shoppingCartDao.updateProduct(shoppingCartItem);
        return null;
    }

    //DELETE CART---------------------------------------------------------------------------------------

    @RequestMapping(method = RequestMethod.DELETE)
    // add a DELETE method to clear all products from the current users cart
    // https://localhost:8080/cart

    public ShoppingCart deleteCart (Principal principal, @PathVariable ShoppingCartItem shoppingCartItem){

        shoppingCartDao.deleteCartItems(shoppingCartItem);
        return null;
    }
}