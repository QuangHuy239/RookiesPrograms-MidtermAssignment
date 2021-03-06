package com.nashtech.MyBikeShop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nashtech.MyBikeShop.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
	List<ProductEntity> findByStatusNotAndCategoriesStatusNot(Sort sort, boolean status, boolean cateStatus);

	List<ProductEntity> findByCategoriesIdAndStatusNot(Sort sort, int id, boolean status);

	List<ProductEntity> findByCategoriesIdAndStatusNot(Pageable pageable, int id, boolean status);

	List<ProductEntity> findByNameIgnoreCaseAndStatusNot(String name, boolean status);

	Optional<ProductEntity> findByIdIgnoreCase(String id);
	
	Optional<ProductEntity> findByIdIgnoreCaseAndStatusNotAndCategoriesStatusNot(String name, boolean status, boolean statusCate);


	int countByCategoriesIdAndStatusNot(int id, boolean status);

	boolean existsByNameAndStatusNot(String name, boolean status);

	@Query("SELECT p FROM ProductEntity p "
			+ "WHERE UPPER(p.name) LIKE %?1% and (p.categories.id = ?2) "
			+ "		and p.status != false and p.categories.status != false "
			+ "ORDER BY p.updateDate DESC")
	List<ProductEntity> searchProduct(String keyword, int type);

	@Query("SELECT p FROM ProductEntity p "
			+ "WHERE UPPER(p.name) LIKE %?1% "
			+ "		and p.status != false and p.categories.status != false "
			+ "ORDER BY p.updateDate DESC")
	List<ProductEntity> searchProduct(String keyword);
	
}
