package com.ironchain.common.dao;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import com.ironchain.common.base.BaseDao;
import com.ironchain.common.domain.Member;

public interface MemberDao extends BaseDao<Member, Long>{

	/**
	 * 根据手机号码和密码查找会员
	 * @param mobilephone
	 * @param password
	 * @return
	 */
	Member findByMobilephoneAndPassword(String mobilephone, String password);

	/**
	 * 根据手机号码查询id
	 * @param mobilephone
	 * @return
	 */
	@Query("select m.id from Member m where m.mobilephone = ?1")
	Long findIdByMobilephone(String mobilephone);
	
	/**
	 * 根据手机号码数组查询id
	 * @param mobilephone
	 * @return
	 */
	@Query("select m.id from Member m where m.mobilephone in (?1)")
	Long[] findIdByMobilephones(String[] mobilephones);
	
	/**
	 * 根据用户id查询类型
	 * @param id
	 * @return
	 */
	@Query(value="select type from member where id=?1", nativeQuery=true)
	int findTypeById(Long id);

	/**
	 * 根据父id查找子账户列表
	 * @param id
	 * @param typeChild
	 * @return
	 */
	List<Member> findByParentIdAndType(Long id, int typeChild);

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select m from Member m where m.id=?1")
	Member lockById(Long userId);
	
	/**
	 * 查找子账户的父账户id
	 * @param userId
	 * @return
	 */
	Long findParentIdById(Long userId);

}
