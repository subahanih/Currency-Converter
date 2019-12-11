package com.mahaboob.currencyconverter.doaimpl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import com.mahaboob.currencyconverter.doa.UserDao;
import com.mahaboob.currencyconverter.domain.User;


@Repository
public class UserDaoImpl implements UserDao {
	
	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public List<User> findAll() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).distinct(true);
        TypedQuery<User> allQuery = entityManager.createQuery(query);

        return allQuery.getResultList();
	}
	
}
