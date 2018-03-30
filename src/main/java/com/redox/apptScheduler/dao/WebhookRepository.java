package com.redox.apptScheduler.dao;

import com.redox.apptScheduler.entity.Webhook;
import org.springframework.data.repository.CrudRepository;

/**
 * Webhook Repository
 */
public interface WebhookRepository  extends CrudRepository<Webhook, Long> {
}
