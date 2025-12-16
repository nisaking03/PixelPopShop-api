package org.yearup.data.mysql;

import org.yearup.data.ShoppingCartDao;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlShoppingCartDao extends MySqlDaoBase implements ShoppingCartDao {
    public MySqlShoppingCartDao(DataSource dataSource)
    {
        super(dataSource);
    }

    @Override
    public ShoppingCart getByUserId(int userId)

    {
        String sql = "SELECT * FROM shopping_cart WHERE user_id = ?";
        try(Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);

            ResultSet row = statement.executeQuery();

//            if (row.next())
//            {
//                return mapRow(row);
//            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void addItem(ShoppingCartItem shoppingCartItem)

    {
//        String sql = "INSERT INTO shopping_cart(product_id, quantity)" +
//                " VALUES (?, ?);";
//
//        try (Connection connection = getConnection())
//        {
//            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
//            statement.setInt(1, "product_Id");
//            statement.setInt(2, quantity);
//
//            int rowsAffected = statement.executeUpdate();
//
//            if (rowsAffected > 0) {
//                // Retrieve the generated keys
//                ResultSet generatedKeys = statement.getGeneratedKeys();
//
//                if (generatedKeys.next()) {
//                    // Retrieve the auto-incremented ID
//                    int orderId = generatedKeys.getInt(1);
//
//                    // get the newly inserted category
//                    return getById(orderId);
//                }
//            }
//        }
//        catch (SQLException e)
//        {
//            throw new RuntimeException(e);
//        }
//        return null;

    }

    @Override
    public void updateProduct(ShoppingCartItem shoppingCartItem)

    {
//        String sql = "UPDATE shopping_cart " +
//                " SET quantity = ? " +
//                " WHERE product_id = ?;";
//
//        try (Connection connection = getConnection())
//        {
//            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.setString(1, category.getName());
//            statement.setString(2, category.getDescription());
//            statement.setInt(3, categoryId);
//
//            statement.executeUpdate();
//        }
//        catch (SQLException e)
//        {
//            throw new RuntimeException(e);
//        }

    }

    @Override
    public void deleteCartItems(ShoppingCartItem shoppingCartItem)

    {
//        String sql = "DELETE FROM shopping_cart " +
//                " WHERE product_id = ?;";
//
//        try (Connection connection = getConnection())
//        {
//            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.setInt(1, productId);
//
//            statement.executeUpdate();
//        }
//        catch (SQLException e)
//        {
//            throw new RuntimeException(e);
//        }

    }

}
