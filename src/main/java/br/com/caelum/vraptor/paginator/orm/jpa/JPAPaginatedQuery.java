package br.com.caelum.vraptor.paginator.orm.jpa;

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

import br.com.caelum.vraptor.paginator.Paginator;
import br.com.caelum.vraptor.paginator.view.Page;

@Dependent
public class JPAPaginatedQuery {

	private EntityManager manager;
	private Query delegate;
	private String raw;
	private JPAPager pager;

	@Inject
	public JPAPaginatedQuery(EntityManager manager,JPAPager jpaPager) {
		super();
		this.manager = manager;
		this.pager = jpaPager;
	}
	
	public JPAPaginatedQuery jpql(String jpql){
		this.delegate = manager.createQuery(jpql);
		this.raw = jpql;
		return this;
	}
	
	public String raw() {
		return raw;
	}
	
	public <T> Paginator<T> paginate(Page page){
		return pager.paginate(this,page);
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

	public JPAPaginatedQuery setMaxResults(int maxResult) {
		delegate.setMaxResults(maxResult);
		return this;
	}

	public int getMaxResults() {
		return delegate.getMaxResults();
	}

	public JPAPaginatedQuery setFirstResult(int startPosition) {
		delegate.setFirstResult(startPosition);
		return this;
	}

	public int getFirstResult() {
		return delegate.getFirstResult();
	}

	public JPAPaginatedQuery setHint(String hintName, Object value) {
		delegate.setHint(hintName, value);
		return this;
	}

	public Map<String, Object> getHints() {
		return delegate.getHints();
	}

	public <T> JPAPaginatedQuery setParameter(Parameter<T> param, T value) {
		delegate.setParameter(param, value);
		return this;
	}

	public JPAPaginatedQuery setParameter(Parameter<Calendar> param, Calendar value, TemporalType temporalType) {
		delegate.setParameter(param, value, temporalType);
		return this;
	}

	public JPAPaginatedQuery setParameter(Parameter<Date> param, Date value, TemporalType temporalType) {
		delegate.setParameter(param, value, temporalType);
		return this;
	}

	public JPAPaginatedQuery setParameter(String name, Object value) {
		delegate.setParameter(name, value);
		return this;
	}

	public JPAPaginatedQuery setParameter(String name, Calendar value, TemporalType temporalType) {
		delegate.setParameter(name, value, temporalType);
		return this;
	}

	public JPAPaginatedQuery setParameter(String name, Date value, TemporalType temporalType) {
		delegate.setParameter(name, value, temporalType);
		return this;
	}

	public JPAPaginatedQuery setParameter(int position, Object value) {
		delegate.setParameter(position, value);
		return this;
	}

	public JPAPaginatedQuery setParameter(int position, Calendar value, TemporalType temporalType) {
		delegate.setParameter(position, value, temporalType);
		return this;
	}

	public JPAPaginatedQuery setParameter(int position, Date value, TemporalType temporalType) {
		delegate.setParameter(position, value, temporalType);
		return this;
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

	public JPAPaginatedQuery setFlushMode(FlushModeType flushMode) {
		delegate.setFlushMode(flushMode);
		return this;
	}

	public FlushModeType getFlushMode() {
		return delegate.getFlushMode();
	}

	public JPAPaginatedQuery setLockMode(LockModeType lockMode) {
		delegate.setLockMode(lockMode);
		return this;
	}

	public LockModeType getLockMode() {
		return delegate.getLockMode();
	}

	public <T> T unwrap(Class<T> cls) {
		return delegate.unwrap(cls);
	}

}
