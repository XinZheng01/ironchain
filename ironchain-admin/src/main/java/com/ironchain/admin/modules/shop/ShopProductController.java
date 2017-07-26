package com.ironchain.admin.modules.shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.HtmlUtils;

import com.ironchain.common.base.ModelController;
import com.ironchain.common.dao.ShopClassDao;
import com.ironchain.common.dao.ShopProductDao;
import com.ironchain.common.dao.ShopProductSkuDao;
import com.ironchain.common.dao.ShopProductSpecDao;
import com.ironchain.common.dao.ShopProductSpecValueDao;
import com.ironchain.common.domain.R;
import com.ironchain.common.domain.ShopProduct;
import com.ironchain.common.domain.ShopProductParam;
import com.ironchain.common.domain.ShopProductSku;
import com.ironchain.common.domain.ShopProductSpec;
import com.ironchain.common.domain.ShopProductSpecValue.SpecValueVO;
import com.ironchain.common.kits.JsonKit;


/**
 * 商品
 * 
 * @author zheng xin
 * @email 
 */
@Controller
@RequestMapping("/shop/product")
public class ShopProductController extends ModelController<ShopProductDao, ShopProduct> {
	
	@Autowired
	private ShopProductService shopProductService;
	
	@Autowired
	private ShopClassDao shopClassDao;
	
	@Autowired
	private ShopProductSpecDao shopProductSpecDao;
	
	@Autowired
	private ShopProductSpecValueDao shopProductSpecValueDao;
	
	@Autowired
	private ShopProductSkuDao shopProductSkuDao;
	
	/**
	 * 列表
	 */
	@GetMapping("/list")
	public String list(Pageable pageable, HttpServletRequest request, Model model){
		model.addAttribute("shopClassList", shopClassDao.findAll());
		model.addAttribute("page", modelDao.findAll(bySearchFilter(request), pageable));
		return "shop/shop_product_list";
	}
	
	
	/**
	 * 编辑页面
	 * @return
	 */
	@GetMapping("/add")
	public String add(@ModelAttribute ShopProduct shopProduct, Model model){
		model.addAttribute("shopClassList", shopClassDao.findAll());
		model.addAttribute("specList", shopProductSpecDao.findAll());
		return "shop/shop_product_form";
	}
	
	/**
	 * 编辑页面
	 * @return
	 */
	@GetMapping("/edit")
	public String edit(@ModelAttribute ShopProduct shopProduct, Model model){
		model.addAttribute("shopClassList", shopClassDao.findAll());
		model.addAttribute("specList", shopProductSpecDao.findAll());
		shopProduct.setContent(HtmlUtils.htmlUnescape(shopProduct.getContent()));
		return "shop/shop_product_form";
	}
	
	/**
	 * 
	 * @return
	 */
	@ResponseBody
	@GetMapping("/attrbute")
	public Map<String, Object> attrbute(Long id){
		Map<String, Object> result = new HashMap<>();
		//产品sku
		List<ShopProductSku> productSkus = shopProductSkuDao.findByProductId(id);
		result.put("skus", productSkus);
		
		if(productSkus.size() > 0){
			String specItemsStr = productSkus.get(0).getSpecItems();
			if(StringUtils.hasText(specItemsStr)){
				String[] specItems = StringUtils.delimitedListToStringArray(specItemsStr, ",");
				int itemsLen = specItems.length;
				//获取所有规格属性id
				if(specItems != null && itemsLen > 0){
					List<Long> specKeys = new ArrayList<>();
					for (int i = 0; i < itemsLen; i++) {
						specKeys.add(Long.valueOf(StringUtils.delimitedListToStringArray(specItems[i], ":")[0]));
					}
					List<ShopProductSpec> productSpecs = shopProductSpecDao.findByIdIn(specKeys);
					//商品规格和对应的规格值
					result.put("specs", productSpecs);
				}
				//商品已选择的属性值列表
				Set<Long> specValues = new HashSet<>();
				for (ShopProductSku sku : productSkus) {
					specItems = StringUtils.delimitedListToStringArray(sku.getSpecItems(), ",");
					itemsLen = specItems.length;
					if(specItems != null && itemsLen > 0){
						for (int i = 0; i < itemsLen; i++) {
							specValues.add(Long.valueOf(StringUtils.delimitedListToStringArray(specItems[i], ":")[1]));
						}
					}
				}
				result.put("specVals", specValues);
				
			}
		}
		return result;
	}
	
	/**
	 * 保存
	 * @return
	 */
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute ShopProduct shopProduct,
			@RequestParam String paramsJson, @RequestParam String skusJson, 
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
		//校验
		if(bindingResult.hasErrors()){
			return "shop/shop_product_form";
		}
		List<ShopProductParam> params = JsonKit.nonDefault().fromJson(HtmlUtils.htmlUnescape(paramsJson), 
				JsonKit.nonDefault().contructCollectionType(ArrayList.class, ShopProductParam.class));
		List<ShopProductSku> skus = JsonKit.nonDefault().fromJson(HtmlUtils.htmlUnescape(skusJson), 
				JsonKit.nonDefault().contructCollectionType(ArrayList.class, ShopProductSku.class));
		shopProductService.save(shopProduct, params, skus);
		redirectAttributes.addFlashAttribute(R.ok().setMsg("操作成功"));
		return "redirect:list";
	}
	
	/**
	 * 删除
	 * @return
	 */
	@PostMapping("/delete")
	@ResponseBody
	public R delete(@RequestParam Long[] ids){
		if(ids == null || ids.length == 0)
			return R.error("请选择删除数据");
		List<ShopProduct> entitys = new ArrayList<>();
		ShopProduct entity = null;
		for (Long id : ids) {
			entity = new ShopProduct();
			entity.setId(id);
			entitys.add(entity);
		}
		modelDao.deleteInBatch(entitys);
		return R.ok().setMsg("操作成功");
	}
	
	@GetMapping("/spec_value_list")
	@ResponseBody
	public List<SpecValueVO> specValueList(@RequestParam Long id){
		return shopProductSpecValueDao.findBySpecId(id);
	}
}
