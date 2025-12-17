package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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

@RequestMapping("/shopping_cart")

@CrossOrigin

public class ShoppingCartController{

    // a shopping cart requires
    private ShoppingCartDao shoppingCartDao;
    private UserDao userDao;
    private ProductDao productDao;

    @Autowired
    // This Auto-injects dependencies, in this case with ShoppingDao, UserDao and ProductDao

    public ShoppingCartController(ShoppingCartDao shoppingCartDao, UserDao userDao, ProductDao productDao){

        this.shoppingCartDao = shoppingCartDao;
        this.userDao = userDao;
        this.productDao = productDao;
    }

    //GET CART------------------------------------------------------------------------------------------

    @GetMapping()

    // each method in this controller requires a Principal object as a parameter
    public ShoppingCart getCart(Principal principal){

        try
        {
            // get the currently logged-in username
            String userName = principal.getName();
            // find database user by userId
            User user = userDao.getByUserName(userName);
            int userId = user.getId();


            // use the shoppingCartDao to get all items in the cart and return the cart

            return shoppingCartDao.getByUserId(userId);
        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

    //ADD PRODUCT---------------------------------------------------------------------------------------

    @PostMapping("/cart/products/{productId}")
    // add a POST method to add a product to the cart - the url should be
    // https://localhost:8080/cart/products/15 (15 is the productId to be added

    public void addToCart(Principal principal, @PathVariable int productId){
                                  //@PathVariable int productId gets the product ID from the URL path.

        try
        {
            // get the currently logged-in username
            String userName = principal.getName();
            // find database user by userId
            User user = userDao.getByUserName(userName);
            int userId = user.getId();


            // use the shoppingCartDao to get all items in the cart and return the cart

            shoppingCartDao.addItem(userId, productId);
        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

    //UPDATE CART---------------------------------------------------------------------------------------

    @PutMapping("/cart/products/{productId}")
    // add a PUT method to update an existing product in the cart - the url should be
    // https://localhost:8080/cart/products/15 (15 is the productId to be updated)
    // the BODY should be a ShoppingCartItem - quantity is the only value that will be updated

    public void updateCart (Principal principal, @PathVariable int productId, @RequestBody ShoppingCartItem item){

        try
        {
            // get the currently logged-in username
            String userName = principal.getName();
            // find database user by userId
            User user = userDao.getByUserName(userName);
            int userId = user.getId();


            // use the shoppingCartDao to get all items in the cart and return the cart

            shoppingCartDao.updateItemQuantity(userId, productId, item.getQuantity());
        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

    //DELETE CART---------------------------------------------------------------------------------------

    @DeleteMapping("/cart")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    // add a DELETE method to clear all products from the current users cart
    // https://localhost:8080/cart

    public void clearCart (Principal principal){

        try
        {
            // get the currently logged-in username
            String userName = principal.getName();
            // find database user by userId
            User user = userDao.getByUserName(userName);
            int userId = user.getId();


            // use the shoppingCartDao to get all items in the cart and return the cart

            shoppingCartDao.clearCart(userId);
        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }
}