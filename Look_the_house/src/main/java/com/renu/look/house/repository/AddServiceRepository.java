package com.renu.look.house.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.renu.look.house.models.AddService;

public interface AddServiceRepository extends JpaRepository<AddService, Long>,JpaSpecificationExecutor<AddService>{
	//get by name
	public AddService findByWord(String word);
	//getById
	public AddService getById(Long id);
	//get all services
	@Query("FROM AddService")
	public List<AddService>getServicesTable();
	//find by category
	public static final String category="FROM AddService where rentType=:category";
        	@Query(category)
        List<AddService> findByCategory(@Param("category")String category);
        	//find by category
        	public static final String country="FROM AddService where country=:country";
                	@Query(country)
			public List<AddService> findByCountry(@Param("country")String country);
                	public static final String countryCategory="FROM AddService where rentType=:category AND country=:country";
                	@Query(countryCategory)
					public List<AddService> findByCountryCategory(@Param("category")String category,@Param("country")String country);
                	
            
        
}
