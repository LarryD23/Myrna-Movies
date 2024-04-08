package com.acme.jpamvcmyrna;
import java.util.List;

import com.acme.jpamvcmyrna.domain.ScheduledRun;
import com.acme.jpamvcmyrna.domain.TicketPriceType;


import jakarta.validation.constraints.NotNull;


public class TicketsSoldForm
{
	
	private Long movieId;
	
	@NotNull
	private TicketPriceType ticketPriceType;
	
	@NotNull
	private Long ticketPriceTypeId;
	
	@NotNull
	private ScheduledRun scheduledRun;
	
	@NotNull
	private Long scheduleId;
	
	@NotNull
	Integer quantity;
	
	public Long getScheduleId()
	{
		return scheduleId;
	}

	public void setScheduleId(Long scheduleId)
	{
		this.scheduleId = scheduleId;
	}


	
	public ScheduledRun getScheduledRun()
	{
		return scheduledRun;
	}

	public void setScheduledRun(ScheduledRun scheduledRun)
	{
		this.scheduledRun = scheduledRun;
	}




	public Long getMovieId()
	{
		return movieId;
	}

	public void setMovieId(Long movieId)
	{
		this.movieId = movieId;
	}



	public Long getTicketPriceTypeId()
	{
		return ticketPriceTypeId;
	}

	public void setTicketPriceTypeId(Long ticketPriceTypeId)
	{
		this.ticketPriceTypeId = ticketPriceTypeId;
	}

	public TicketPriceType getTicketPriceType()
	{
		return ticketPriceType;
	}

	public void setTicketPriceType(TicketPriceType ticketPriceType)
	{
		this.ticketPriceType = ticketPriceType;
	}

	public Integer getQuantity()
	{
		return quantity;
	}

	public void setQuantity(Integer quantity)
	{
		this.quantity = quantity;
	}


	
	
}
