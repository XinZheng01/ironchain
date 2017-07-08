package com.ironchain.common.kits;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.ironchain.common.kits.SearchFilter.Operator;

/**
 * Spring Data Jpa 动态查询
 * @see Specification
 * @author Administrator
 *
 */
public class SpecificationKit {

	public static <T> Specification<T> bySearchFilter(Map<String, Object> searchParams){
		return bySearchFilter(SearchFilter.parse(searchParams).values());
	}
	
	@SuppressWarnings(value={"rawtypes","unchecked"})
	public static <T> Specification<T> bySearchFilter(final Collection<SearchFilter> filters) {
		return new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				if (filters != null && filters.size() > 0) {
					List<Predicate> predicates = new ArrayList<>();
					for (SearchFilter filter : filters) {
						// nested path translate, 如Task的名为"user.name"的filedName, 转换为Task.user.name属性
						String[] names = StringUtils.split(filter.fieldName, ".");
						Path expression = root.get(names[0]);
						for (int i = 1; i < names.length; i++) {
							expression = expression.get(names[i]);
						}
						// logic operator
						switch (filter.operator) {
						case EQ:
							predicates.add(builder.equal(expression, filter.value));
							break;
						case NEQ:
							predicates.add(builder.notEqual(expression, filter.value));
							break;
						case LIKE:
							predicates.add(builder.like(expression, "%" + filter.value + "%"));
							break;
						case NOTLIKE:
							predicates.add(builder.notLike(expression, "%" + filter.value + "%"));
							break;
						case GT:
							predicates.add(builder.greaterThan(expression, (Comparable) filter.value));
							break;
						case LT:
							predicates.add(builder.lessThan(expression, (Comparable) filter.value));
							break;
						case GTE:
							predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) filter.value));
							break;
						case LTE:
							predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) filter.value));
							break;
						case NULL:
							predicates.add(expression.isNull());
							break;
						case NOTNULL:
							predicates.add(expression.isNotNull());
							break;
						case IN:
							predicates.add(expression.in((Collection)filter.value));
							break;
						case NOTIN:
							predicates.add(builder.not(expression.in((Collection)filter.value)));
							break;
						}
					}
					// 将所有条件用 and 联合起来
					if (!predicates.isEmpty()) {
						return builder.and(predicates.toArray(new Predicate[predicates.size()]));
					}
				}
				return builder.conjunction();
			}
		};
	}
	
	public static Builder createBuilder(){
		return new Builder();
	}
	
	public static class Builder{
		
		private Builder(){}
		
		private List<SearchFilter> filters = new ArrayList<>();
		
		public Builder addCondition(boolean append, String fieldName, Operator operator, Object value){
			if(append)
				this.filters.add(new SearchFilter(fieldName, operator, value));
			return this;
		}
		
		public Builder addCondition(String fieldName, Operator operator, Object value){
			return addCondition(true, fieldName, operator, value);
		}
		
		public Builder addConditions(Map<String, Object> searchParams){
			this.filters.addAll(SearchFilter.parse(searchParams).values());
			return this;
		}
		
		public Builder eq(String fieldName, Object value){
			return addCondition(true, fieldName, Operator.EQ, value);
		}
		
		public Builder eq(boolean append, String fieldName, Object value){
			return addCondition(append, fieldName, Operator.EQ, value);
		}
		
		public Builder neq(String fieldName, Object value){
			return addCondition(true, fieldName, Operator.NEQ, value);
		}
		
		public Builder neq(boolean append, String fieldName, Object value){
			return addCondition(append, fieldName, Operator.NEQ, value);
		}
		
		public Builder like(String fieldName, Object value){
			return addCondition(true, fieldName, Operator.LIKE, value);
		}
		
		public Builder like(boolean append, String fieldName, Object value){
			return addCondition(append, fieldName, Operator.LIKE, value);
		}
		
		public Builder notLike(String fieldName, Object value){
			return addCondition(true, fieldName, Operator.NOTLIKE, value);
		}
		
		public Builder notLike(boolean append, String fieldName, Object value){
			return addCondition(append, fieldName, Operator.NOTLIKE, value);
		}
		
		public Builder gt(String fieldName, Object value){
			return addCondition(true, fieldName, Operator.GT, value);
		}
		
		public Builder gt(boolean append, String fieldName, Object value){
			return addCondition(append, fieldName, Operator.GT, value);
		}
		
		public Builder lt(String fieldName, Object value){
			return addCondition(true, fieldName, Operator.LT, value);
		}
		
		public Builder lt(boolean append, String fieldName, Object value){
			return addCondition(append, fieldName, Operator.LT, value);
		}
		
		public Builder gte(String fieldName, Object value){
			return addCondition(true, fieldName, Operator.GTE, value);
		}
		
		public Builder gte(boolean append, String fieldName, Object value){
			return addCondition(append, fieldName, Operator.GTE, value);
		}
		
		public Builder lte(String fieldName, Object value){
			return addCondition(true, fieldName, Operator.LTE, value);
		}
		
		public Builder lte(boolean append, String fieldName, Object value){
			return addCondition(append, fieldName, Operator.LTE, value);
		}
		
		public Builder isNull(String fieldName, Object value){
			return addCondition(true, fieldName, Operator.NULL, value);
		}
		
		public Builder isNull(boolean append, String fieldName, Object value){
			return addCondition(append, fieldName, Operator.NULL, value);
		}
		
		public Builder notNull(String fieldName, Object value){
			return addCondition(true, fieldName, Operator.NOTNULL, value);
		}
		
		public Builder notNull(boolean append, String fieldName, Object value){
			return addCondition(append, fieldName, Operator.NOTNULL, value);
		}
		
		public Builder in(String fieldName, Object value){
			return addCondition(true, fieldName, Operator.IN, value);
		}
		
		public Builder in(boolean append, String fieldName, Object value){
			return addCondition(append, fieldName, Operator.IN, value);
		}
		
		public Builder notIn(String fieldName, Object value){
			return addCondition(true, fieldName, Operator.NOTIN, value);
		}
		
		public Builder notIn(boolean append, String fieldName, Object value){
			return addCondition(append, fieldName, Operator.NOTIN, value);
		}
		
		public Collection<SearchFilter> getFilters(){
			return this.filters;
		}
		
		public <T> Specification<T> bySearchFilter(){
			return SpecificationKit.bySearchFilter(this.filters);
		}
	}
	
}
