package com.acme.jpamvcmyrna;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;

public class MovieForm
{
	
	@NotNull
	@Size(min=1, max=40)
	private String movieName;
	
	@NotNull
	private Long ticketId;
	
	@NotNull
	private Long scheduleRunId;
	
	
	public Long getScheduleRunId()
	{
		return scheduleRunId;
	}

	public void setScheduleRunId(Long scheduleRunId)
	{
		this.scheduleRunId = scheduleRunId;
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
	

}
