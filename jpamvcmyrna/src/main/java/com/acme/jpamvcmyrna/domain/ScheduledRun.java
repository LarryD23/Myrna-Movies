package com.acme.jpamvcmyrna.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;
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
@Table(name="SCHEDULE")
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Data

public class ScheduledRun implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name="SCHEDULE_ID", unique=true, nullable=false)
	 private Long scheduleId;
	 
	 private LocalDate date;
//	 private LocalDate endDate;
	 
	 private LocalTime time;
	 
	 @ManyToOne
	 @JoinColumn(name = "MOVIE_ID")
	 private Movie movie;

	 
	 

	 
	  

	
}
