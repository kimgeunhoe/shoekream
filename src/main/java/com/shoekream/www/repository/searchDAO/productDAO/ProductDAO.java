package com.shoekream.www.repository.searchDAO.productDAO;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.shoekream.www.domain.PagingVO.PagingVO;
import com.shoekream.www.domain.filterVO.FilterBrandVO;
import com.shoekream.www.domain.filterVO.FilterCategoryVO;
import com.shoekream.www.domain.productVO.ProductVO;

@Repository
public interface ProductDAO {
	
	List<ProductVO> selectList(PagingVO pgvo);
	List<ProductVO> selectAdminList(PagingVO pgvo);
	List<ProductVO> selectMinPrice(PagingVO pgvo);
	int totalCount(PagingVO pgvo);
	int adminListTotalCount(PagingVO pgvo);
	int insert(ProductVO pvo);
	List<FilterBrandVO> selectBrandList();	
	List<FilterCategoryVO> selectCategoryList();
	int checkModelDuple(String model);
	int insertImage(ProductVO productVO);
	int selectLastPno();
	int selectProductCount(int pno);
	String selectModelNumber(int pno);
	List<ProductVO> selectImageIdAndUrl(String model);
	int deleteImageWithModelNumber(List<ProductVO> imageList);
	int deleteProduct(int pno);
}