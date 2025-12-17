package org.yearup.data.mysql;

import org.yearup.data.ShoppingCartDao;
import org.yearup.models.ShoppingCart;

import javax.sql.DataSource;

public class MySqlShoppingCartDao extends MySqlDaoBase implements ShoppingCartDao{

    public MySqlShoppingCartDao(DataSource dataSource){
        super(dataSource);
    }

    //GET BY USER ID------------------------------------------------------------------------------------

    public ShoppingCart getByUserId(int userId){
        return null;
    }

    //ADD ITEM------------------------------------------------------------------------------------------

    public void addItem(int userId, int productId, int quantity){}

    //UPDATE PRODUCT------------------------------------------------------------------------------------

    public void updateItemQuantity(int userId, int productId, int quantity){}

    //DELETE CART ITEM----------------------------------------------------------------------------------

    public void removeItem(int userId, int productId){}

    //DELETE CART---------------------------------------------------------------------------------------

    public void clearCart(int userId){}
}