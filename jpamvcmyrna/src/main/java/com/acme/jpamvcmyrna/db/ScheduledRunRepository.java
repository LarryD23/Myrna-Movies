package com.acme.jpamvcmyrna.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acme.jpamvcmyrna.domain.ScheduledRun;

public interface ScheduledRunRepository extends JpaRepository<ScheduledRun, Long>
{
	
}
