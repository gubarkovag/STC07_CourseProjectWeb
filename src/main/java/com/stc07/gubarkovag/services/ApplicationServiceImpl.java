package com.stc07.gubarkovag.services;

import com.stc07.gubarkovag.db.dao.ApplicationDAO;
import com.stc07.gubarkovag.db.dao.ApplicationDAOImpl;
import com.stc07.gubarkovag.pojo.Application;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

public class ApplicationServiceImpl implements  ApplicationService{
    private static final Logger logger = Logger.getLogger(ApplicationServiceImpl.class);

    public static class ApplicationServiceException extends Exception {}

    private static ApplicationDAO applicationDAO = new ApplicationDAOImpl();

    @Override
    public List<Application> getAll() throws ApplicationServiceException {
        try {
            return applicationDAO.getAll();
        } catch (ApplicationDAOImpl.ApplicationDAOException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
            throw new ApplicationServiceException();
        }
    }

    @Override
    public Application getById(Integer id) throws ApplicationServiceException {
        try {
            return applicationDAO.getById(id);
        } catch (ApplicationDAOImpl.ApplicationDAOException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
            throw new ApplicationServiceException();
        }
    }

    @Override
    public boolean deleteById(Integer id) throws ApplicationServiceException {
        try {
            return applicationDAO.deleteById(id);
        } catch (ApplicationDAOImpl.ApplicationDAOException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
            throw new ApplicationServiceException();
        }
    }

    @Override
    public boolean deleteAll() throws ApplicationServiceException {
        try {
            return applicationDAO.deleteAll();
        } catch (ApplicationDAOImpl.ApplicationDAOException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
            throw new ApplicationServiceException();
        }
    }

    @Override
    public int insertOne(Application application) throws ApplicationServiceException {
        try {
            return applicationDAO.insertOne(application);
        } catch (ApplicationDAOImpl.ApplicationDAOException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
            throw new ApplicationServiceException();
        }
    }

    @Override
    public int[] insertAll(List<Application> applications) throws ApplicationServiceException {
        try {
            return applicationDAO.insertAll(applications);
        } catch (ApplicationDAOImpl.ApplicationDAOException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
            throw new ApplicationServiceException();
        }
    }

    @Override
    public int update(String status, Integer targetId) throws ApplicationServiceException {
        try {
            return applicationDAO.update(status, targetId);
        } catch (ApplicationDAOImpl.ApplicationDAOException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
            throw new ApplicationServiceException();
        }
    }

    @Override
    public int[] updateAll(Map<String, Integer> updateData) throws ApplicationServiceException {
        try {
            return applicationDAO.updateAll(updateData);
        } catch (ApplicationDAOImpl.ApplicationDAOException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
            throw new ApplicationServiceException();
        }
    }

    @Override
    public boolean putRejectedToWaitingStatus(Integer userId) throws ApplicationServiceException {
        try {
            return applicationDAO.putRejectedToWaitingStatus(userId);
        } catch (ApplicationDAOImpl.ApplicationDAOException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
            throw new ApplicationServiceException();
        }
    }
}
