package org.yearup.data.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.ShoppingCart;

import javax.sql.DataSource;

@Component
public class MySqlShoppingCartDao extends MySqlDaoBase implements ShoppingCartDao{


    public MySqlShoppingCartDao(DataSource dataSource){
        super(dataSource);
    }

    //GET BY USER ID------------------------------------------------------------------------------------

    @Override
    public ShoppingCart getByUserId(int userId){
        return null;
    }

    //ADD ITEM------------------------------------------------------------------------------------------

    @Override
    public void addItem(int userId, int productId){}

    //UPDATE PRODUCT------------------------------------------------------------------------------------

    @Override
    public void updateItemQuantity(int userId, int productId, int quantity){}

    //DELETE CART---------------------------------------------------------------------------------------

    @Override
    public void clearCart(int userId){}
}