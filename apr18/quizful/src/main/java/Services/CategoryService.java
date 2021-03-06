package Services;

import Models.dao.CategoryDaoInterface;
import Models.pojo.Category;
import Exceptions.QuizInternalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by eku on 30.04.17.
 */
@Service
public class CategoryService implements CategoryServiceInterface {
    private static CategoryDaoInterface categoryDao;

    @Autowired
    public void setCategoryDao(CategoryDaoInterface categoryDao) {
        CategoryService.categoryDao = categoryDao;
    }

    @Override
    public List<Category> getCategories() throws QuizInternalException {
        return categoryDao.getCategories();
    }

    public Category getCategoryById(int categoryId) throws QuizInternalException {
        return categoryDao.getCategoryById(categoryId);
    }
}
