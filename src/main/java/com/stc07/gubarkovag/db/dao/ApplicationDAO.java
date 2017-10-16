package com.stc07.gubarkovag.db.dao;

import com.stc07.gubarkovag.pojo.Application;

import java.util.List;
import java.util.Map;

public interface ApplicationDAO {
    List<Application> getAll() throws ApplicationDAOImpl.ApplicationDAOException;
    Application getById(Integer id) throws ApplicationDAOImpl.ApplicationDAOException;
    boolean deleteById(Integer id) throws ApplicationDAOImpl.ApplicationDAOException;
    boolean deleteAll() throws ApplicationDAOImpl.ApplicationDAOException;
    int insertOne(Application application) throws ApplicationDAOImpl.ApplicationDAOException;
    int[] insertAll(List<Application> applications) throws ApplicationDAOImpl.ApplicationDAOException;
    int update(Application changeData, Application target) throws ApplicationDAOImpl.ApplicationDAOException;
    int[] updateAll(Map<Application, Application> updateData) throws ApplicationDAOImpl.ApplicationDAOException;
}
