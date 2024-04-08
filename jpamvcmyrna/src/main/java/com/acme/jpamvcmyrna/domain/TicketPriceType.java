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

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="TICKET_PRICE_TYPE")
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Data

public class TicketPriceType implements Serializable
{
	private static final long serialVersionUID = 1L;
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name="PRICE_TYPE_ID", unique=true, nullable=false)
	    private Long priceTypeId;

	    @Column(name = "PRICE_TYPE_NAME")
	    private String priceTypeName; // Example: "Adult", "Child", etc.

	    @Column(name = "PRICE")
	    private BigDecimal priceValue; // Price value for this type

	    @Column(name="IS_ACTIVE")
		private boolean isActive;


}
