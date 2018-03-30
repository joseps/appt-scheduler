package com.redox.apptScheduler.dao;

import com.redox.apptScheduler.entity.Provider;
import org.springframework.data.repository.CrudRepository;

/**
 * Provider Repository
 */
public interface ProviderRepository extends CrudRepository<Provider, Long> {
}
