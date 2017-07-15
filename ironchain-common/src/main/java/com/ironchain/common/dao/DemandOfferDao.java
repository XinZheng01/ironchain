package com.ironchain.common.dao;

import com.ironchain.common.base.BaseDao;
import com.ironchain.common.domain.DemandOffer;

public interface DemandOfferDao extends BaseDao<DemandOffer, Long> {

	/**
	 * 根据发布人与需求id查找竞标数据
	 * @param offerId
	 * @param demandId
	 * @return
	 */
	Long findIdByOfferIdAndDemandId(Long offerId, Long demandId);

	/**
	 * 查找用户竞标的需求数
	 * @param id
	 * @return
	 */
	Long countByOfferId(Long id);

}
