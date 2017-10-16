package com.stc07.gubarkovag.services;

import com.stc07.gubarkovag.pojo.Application;

import java.util.List;
import java.util.Map;

public interface ApplicationService {
    List<Application> getAll() throws ApplicationServiceImpl.ApplicationServiceException;
    Application getById(Integer id) throws ApplicationServiceImpl.ApplicationServiceException;
    boolean deleteById(Integer id) throws ApplicationServiceImpl.ApplicationServiceException;
    boolean deleteAll() throws ApplicationServiceImpl.ApplicationServiceException;
    int insertOne(Application application) throws ApplicationServiceImpl.ApplicationServiceException;
    int[] insertAll(List<Application> applications) throws ApplicationServiceImpl.ApplicationServiceException;
    int update(Application changeData, Application target) throws ApplicationServiceImpl.ApplicationServiceException;
    int[] updateAll(Map<Application, Application> updateData) throws ApplicationServiceImpl.ApplicationServiceException;
}
