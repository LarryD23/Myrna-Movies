package com.acme.jpamvcmyrna.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
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

import com.acme.jpamvcmyrna.db.TicketPriceTypeRepository;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table(name = "TICKETS_SOLD")
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class TicketsSold implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="TICKET_SOLD_ID", unique=true, nullable=false)
	private Long ticketSoldId;
	
	@ManyToOne
	@JoinColumn(name = "SCHEDULED_RUN_ID")
	private ScheduledRun scheduledRun;
	
	@ManyToOne
	@JoinColumn(name = "MOVIE_ID")
	@JsonBackReference
	private Movie movie;
	
	@ManyToOne
	@JoinColumn(name = "PRICE_TYPE_ID") // Name of the column referencing TicketPriceType
	private TicketPriceType ticketPriceType;
	
	@Column(name="QUANTITY")
	private Integer quantity;
	
	@Column(name="TOTAL_PRICE")
	private BigDecimal totalPrice;
	

	
	

}
