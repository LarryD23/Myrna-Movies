package com.acme.jpamvcmyrna.domain;

import java.io.Serializable;
import java.math.BigDecimal;
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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="MOVIES")

@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Data


public class Movie implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="MOVIE_ID", unique=true, nullable=false)
	private Long movieId;
	
	@Column(name="MOVIE_NAME", length=40)
	private String movieName;


	@OneToMany(mappedBy = "movie", fetch = FetchType.EAGER)
	@JsonManagedReference
	@JsonIgnore
    private List<TicketsSold> ticketsSoldList;
	
	private BigDecimal distributorSharePercentage;
	


}
