package Models.dao;

import Models.pojo.*;
import Models.pojo.Category;
import exceptions.QuizInternalException;

import java.util.List;

/**
 * Created by eku on 30.04.17.
 */
public interface CategoryDaoInterface {
    List<Category> getCategories() throws QuizInternalException;
    Category getCategoryById(int categoryId) throws QuizInternalException;
}
