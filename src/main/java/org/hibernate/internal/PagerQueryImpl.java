package org.hibernate.internal;

import java.util.Map;

import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.TypedValue;

/**
 * Just a temporal solution to get named parameter values from {@link QueryImpl}. 
 * @author Alberto Souza
 *
 */
public final class PagerQueryImpl extends QueryImpl {
	
	/*
	 * I know this is not a good solution but was the better that I find.
	 */

	private QueryImpl queryImpl;	

	public PagerQueryImpl(SessionImplementor sessionImplementor,QueryImpl queryImpl) {				
		super(queryImpl.getQueryString(),queryImpl.getFlushMode(),sessionImplementor,queryImpl.getParameterMetadata());
		this.queryImpl = queryImpl;
	}
	
	@Override
	public Map<String, TypedValue> getNamedParams() {		
		return queryImpl.getNamedParams();
	}
}
