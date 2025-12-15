package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.CategoryDao;
import org.yearup.models.Category;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlCategoryDao extends MySqlDaoBase implements CategoryDao
{
    public MySqlCategoryDao(DataSource dataSource)
    {
        super(dataSource);
    }

    @Override
    public List<Category> getAllCategories()
            // Built logic in this method
    {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM categories";

        try(Connection connection = super.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet results = statement.executeQuery()){

            while(results.next()){
                int categoryId = results.getInt(1);
                String name = results.getString(2);
                int description = results.getInt(3);
                categories.add(new Category());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
        // Returns all categories
    }

    @Override
    public Category getById(int categoryId)
            // Built logic in this method
    {
        String sql = "SELECT * FROM categories WHERE category_id = ?";
        try(Connection connection = super.getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, categoryId);

            ResultSet row = statement.executeQuery();

            if (row.next())
            {
                return mapRow(row);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
        // Get category by ID
    }

    @Override
    public Category create(Category category)
    {
        String sql = "INSERT INTO categories(category_id, name, description) " +
                " VALUES (?, ?, ?);";

        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, category.getCategoryId());
            statement.setString(2, category.getName());
            statement.setString(3, category.getDescription());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                // Retrieve the generated keys
                ResultSet generatedKeys = statement.getGeneratedKeys();

                if (generatedKeys.next()) {
                    // Retrieve the auto-incremented ID
                    int orderId = generatedKeys.getInt(1);

                    // get the newly inserted category
                    return getById(orderId);
                }
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void update(int categoryId, Category category)
    {
        // update category
    }

    @Override
    public void delete(int categoryId)
    {
        // delete category
    }

    private Category mapRow(ResultSet row) throws SQLException
    {
        int categoryId = row.getInt("category_id");
        String name = row.getString("name");
        String description = row.getString("description");

        Category category = new Category()
        {{
            setCategoryId(categoryId);
            setName(name);
            setDescription(description);
        }};

        return category;
    }

}
