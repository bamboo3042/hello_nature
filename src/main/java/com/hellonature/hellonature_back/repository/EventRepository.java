package com.hellonature.hellonature_back.repository;

import com.hellonature.hellonature_back.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
