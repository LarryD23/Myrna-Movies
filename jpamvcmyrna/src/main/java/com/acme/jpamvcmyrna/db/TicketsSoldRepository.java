package com.acme.jpamvcmyrna.db;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acme.jpamvcmyrna.domain.Movie;
import com.acme.jpamvcmyrna.domain.TicketPriceType;
import com.acme.jpamvcmyrna.domain.TicketsSold;

public interface TicketsSoldRepository extends JpaRepository<TicketsSold, Long>
{
	TicketsSold findByTicketPriceType(TicketPriceType ticketPriceType);
	TicketsSold findByTicketSoldId(Long ticketSoldId);
	TicketsSold findByMovie (Movie movie);
}