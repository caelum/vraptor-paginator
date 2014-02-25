package br.com.caelum.vraptor.paginator.orm.jpa;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Parameter;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import net.vidageek.mirror.dsl.Mirror;
import br.com.caelum.vraptor.proxy.JavassistProxifier;
import br.com.caelum.vraptor.proxy.MethodInvocation;
import br.com.caelum.vraptor.proxy.SuperMethod;

@Dependent
public class PaginatedManagerProducer {

	private EntityManager manager;
	private final JavassistProxifier proxifier = new JavassistProxifier();

	@Inject
	public PaginatedManagerProducer(EntityManager manager) {
		this.manager = manager;
	}

	public PaginatedQuery get(final String jpql) {
		return proxifier.proxify(PaginatedQuery.class, new MethodInvocation<PaginatedQuery>() {
			private DefaultPaginatedQuery paginatedQuery = new DefaultPaginatedQuery(manager.createQuery(jpql), jpql);

			@Override
			public Object intercept(PaginatedQuery proxy, Method method, Object[] args, SuperMethod superMethod) {
				if (method.getName().equals("raw")) {
					return this.paginatedQuery.raw;
				}
				Object result = new Mirror().on(paginatedQuery.delegate).invoke().method(method).withArgs(args);
				if(result instanceof Query) {
					return paginatedQuery;
				}
				
				return result;
			}
		});
	}

	private static class DefaultPaginatedQuery implements PaginatedQuery {

		private Query delegate;
		private String raw;

		public DefaultPaginatedQuery(Query delegate, String raw) {
			super();
			this.delegate = delegate;
			this.raw = raw;
		}
		
		@Override
		public String raw() {
			return raw;
		}

		public List getResultList() {
			return delegate.getResultList();
		}

		public Object getSingleResult() {
			return delegate.getSingleResult();
		}

		public int executeUpdate() {
			return delegate.executeUpdate();
		}

		public Query setMaxResults(int maxResult) {
			return delegate.setMaxResults(maxResult);
		}

		public int getMaxResults() {
			return delegate.getMaxResults();
		}

		public Query setFirstResult(int startPosition) {
			return delegate.setFirstResult(startPosition);
		}

		public int getFirstResult() {
			return delegate.getFirstResult();
		}

		public Query setHint(String hintName, Object value) {
			return delegate.setHint(hintName, value);
		}

		public Map<String, Object> getHints() {
			return delegate.getHints();
		}

		public <T> Query setParameter(Parameter<T> param, T value) {
			return delegate.setParameter(param, value);
		}

		public Query setParameter(Parameter<Calendar> param, Calendar value, TemporalType temporalType) {
			return delegate.setParameter(param, value, temporalType);
		}

		public Query setParameter(Parameter<Date> param, Date value, TemporalType temporalType) {
			return delegate.setParameter(param, value, temporalType);
		}

		public Query setParameter(String name, Object value) {
			return delegate.setParameter(name, value);
		}

		public Query setParameter(String name, Calendar value, TemporalType temporalType) {
			return delegate.setParameter(name, value, temporalType);
		}

		public Query setParameter(String name, Date value, TemporalType temporalType) {
			return delegate.setParameter(name, value, temporalType);
		}

		public Query setParameter(int position, Object value) {
			return delegate.setParameter(position, value);
		}

		public Query setParameter(int position, Calendar value, TemporalType temporalType) {
			return delegate.setParameter(position, value, temporalType);
		}

		public Query setParameter(int position, Date value, TemporalType temporalType) {
			return delegate.setParameter(position, value, temporalType);
		}

		public Set<Parameter<?>> getParameters() {
			return delegate.getParameters();
		}

		public Parameter<?> getParameter(String name) {
			return delegate.getParameter(name);
		}

		public <T> Parameter<T> getParameter(String name, Class<T> type) {
			return delegate.getParameter(name, type);
		}

		public Parameter<?> getParameter(int position) {
			return delegate.getParameter(position);
		}

		public <T> Parameter<T> getParameter(int position, Class<T> type) {
			return delegate.getParameter(position, type);
		}

		public boolean isBound(Parameter<?> param) {
			return delegate.isBound(param);
		}

		public <T> T getParameterValue(Parameter<T> param) {
			return delegate.getParameterValue(param);
		}

		public Object getParameterValue(String name) {
			return delegate.getParameterValue(name);
		}

		public Object getParameterValue(int position) {
			return delegate.getParameterValue(position);
		}

		public Query setFlushMode(FlushModeType flushMode) {
			return delegate.setFlushMode(flushMode);
		}

		public FlushModeType getFlushMode() {
			return delegate.getFlushMode();
		}

		public Query setLockMode(LockModeType lockMode) {
			return delegate.setLockMode(lockMode);
		}

		public LockModeType getLockMode() {
			return delegate.getLockMode();
		}

		public <T> T unwrap(Class<T> cls) {
			return delegate.unwrap(cls);
		}
		
		

	}

}
