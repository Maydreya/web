package com.may.dft.repos;

import org.springframework.data.repository.CrudRepository;
import com.may.dft.domain.Bicycles;

public interface BicycleRepos extends CrudRepository<Bicycles, Long> {
}
