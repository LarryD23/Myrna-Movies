package com.acme.jpamvcmyrna;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.acme.jpamvcmyrna.domain.TicketsSold;
//import com.acme.jpamvcmyrna.domain.TicketsSold;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class MovieEditForm
{
	@NotNull
	@Size(min=1, max=40)
	private String movieName;
	
	@NotNull
	private Long ticketId;
	
	@NotNull
	private Long movieId;
	
	@NotNull
	private TicketsSold totalPrice;

	public TicketsSold getTotalPrice()
	{
		return totalPrice;
	}

	public void setTotalPrice(TicketsSold totalPrice)
	{
		this.totalPrice = totalPrice;
	}

	public String getMovieName()
	{
		return movieName;
	}

	public void setMovieName(String movieName)
	{
		this.movieName = movieName;
	}

	public Long getTicketId()
	{
		return ticketId;
	}

	public void setTicketId(Long ticketId)
	{
		this.ticketId = ticketId;
	}

	public Long getMovieId()
	{
		return movieId;
	}

	public void setMovieId(Long movieId)
	{
		this.movieId = movieId;
	}
	
	
}
