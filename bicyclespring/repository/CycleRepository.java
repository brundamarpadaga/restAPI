package com.prodapt.bicyclespring.repository;

import org.springframework.data.repository.CrudRepository;

import com.prodapt.bicyclespring.entity.Bicycle;

public interface CycleRepository  extends CrudRepository<Bicycle, Long> {

}
