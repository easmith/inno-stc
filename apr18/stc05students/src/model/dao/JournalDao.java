package model.dao;


import model.entity.Journal;

import java.util.List;

public interface JournalDao {

    List<Journal> findAll();

    Journal findById(long id);

    int insert(Journal group);

    int delete(long id);

    int update(Journal group);
}
