package com.acme.jpamvcmyrna.db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acme.jpamvcmyrna.domain.TicketPriceType;

public interface TicketPriceTypeRepository extends JpaRepository<TicketPriceType, Long>
{
	//Find by price type using TicketPriceType
	TicketPriceType findByPriceTypeName(TicketPriceType ticketPriceType);
	TicketPriceType findByPriceTypeId(Long Id);
}
