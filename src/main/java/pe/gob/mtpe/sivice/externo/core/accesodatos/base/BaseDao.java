package pe.gob.mtpe.sivice.externo.core.accesodatos.base;

import java.io.Serializable;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseDao<PK extends Serializable, T> {

	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public BaseDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	private EntityManager entityManager;

	protected Session getSession() {
		return entityManagerFactory.unwrap(Session.class);
	}
	
	//@SuppressWarnings("unchecked")
	public T buscarId(PK key) {
		entityManager = entityManagerFactory.createEntityManager();
		T info = entityManager.find(persistentClass, key);
		entityManager.close();
		return info;
	}

	public void persist(T entity) {
		getSession().persist(entity);
	}

	public void actualizar(T entity) {
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(entity);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public void eliminar(T entity) {
		getSession().delete(entity);
	}

	protected EntityManager createEntityManager() {
		entityManager = entityManagerFactory.createEntityManager();
		return entityManager;
	}

	public void guardar(T entity) {
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

}
