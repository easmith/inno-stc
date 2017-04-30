package Models.dao;

import Models.pojo.Result;

import java.util.List;

/**
 * Created by eku on 30.04.17.
 */
public interface ResultDaoInterface {
    Result createResult();
    List<Result> getResultsByUserId(int userId);
}
