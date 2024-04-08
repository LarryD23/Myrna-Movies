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

import com.acme.jpamvcmyrna.domain.ScheduledRun;
import com.acme.jpamvcmyrna.domain.TicketPriceType;
import com.acme.jpamvcmyrna.domain.TicketsSold;
//import com.acme.jpamvcmyrna.domain.TicketsSold;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class TicketsSoldEditForm {
    
    private Long movieId;

 
    private TicketPriceType ticketPriceType;

    @NotNull
    private Long ticketSoldId;
    
   
    private ScheduledRun scheduledRun;

    
    private Integer quantity;
    
    @NotNull
    private Long ticketPriceTypeId;

    public Long getScheduledRunId()
	{
		return scheduledRunId;
	}

	public void setScheduledRunId(Long scheduledRunId)
	{
		this.scheduledRunId = scheduledRunId;
	}

	private Long scheduledRunId;

    public Long getTicketPriceTypeId()
	{
		return ticketPriceTypeId;
	}

	public void setTicketPriceTypeId(Long ticketPriceTypeId)
	{
		this.ticketPriceTypeId = ticketPriceTypeId;
	}

	public Long getTicketSoldId() {
        return ticketSoldId;
    }

    public void setTicketSoldId(Long ticketSoldId) {
        this.ticketSoldId = ticketSoldId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public TicketPriceType getTicketPriceType() {
        return ticketPriceType;
    }

    public void setTicketPriceType(TicketPriceType ticketPriceType) {
        this.ticketPriceType = ticketPriceType;
        this.ticketPriceTypeId = ticketPriceType.getPriceTypeId();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    // Other getters and setters...
}

