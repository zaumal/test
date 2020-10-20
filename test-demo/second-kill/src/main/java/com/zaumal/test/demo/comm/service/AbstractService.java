package com.zaumal.test.demo.comm.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.hibernate.engine.spi.SessionImplementor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractService{
	protected Logger logger = LoggerFactory.getLogger(AbstractService.class);
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public EntityManager getEm(){
		return this.entityManagerFactory.createEntityManager();
	}
	
	public void close(EntityManager em){
		if(null != em){
			em.close();
		}
	}

	public void close(Statement statement){
		if(null != statement){
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void close(Connection connection) {
		if(null != connection){
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void close(ResultSet rs) {
		if(null != rs){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Connection getConnection(EntityManager entityManager){
		SessionImplementor session = entityManager.unwrap(SessionImplementor.class);
		return session.connection();
	}
	
	public String obj2Str(Object obj){
		return null != obj ? obj.toString() : "";
	}
	
	
	public int nativeUpdate(String sql){
		EntityManager em = null;
		try{
			em = getEm();
			EntityTransaction et = em.getTransaction();
			et.begin();
			int count = em.createNativeQuery(sql).executeUpdate();
			et.commit();
			return count;
		}catch (Exception e) {
			logger.error("[aqu-common-web] --- 执行原生SQL(executeUpdate)时发生异常",e.getMessage(),e);
			logger.error("[aqu-common-web] --- " + sql);
		}finally{
			close(em);
		}
		return -1;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> nativeQuery(String sql){
		EntityManager em = null;
		try{
			em = getEm();
			return em.createNativeQuery(sql)
					.getResultList();
		}catch (Exception e) {
			logger.error("[aqu-common-web] --- 执行原生SQL(getResultList)时发生异常",e.getMessage(),e);
		}finally{
			close(em);
		}
		return new ArrayList<>(0);
	}
}
