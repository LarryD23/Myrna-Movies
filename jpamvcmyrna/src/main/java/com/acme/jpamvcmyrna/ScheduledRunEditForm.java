package com.acme.jpamvcmyrna;

import java.time.LocalDate;
import java.time.LocalTime;

import com.acme.jpamvcmyrna.domain.Movie;

import jakarta.validation.constraints.NotNull;

public class ScheduledRunEditForm
{
	
	private Long movieId;
	
	@NotNull
	private LocalDate date;
	
	@NotNull
	private LocalTime time;
//	
	private Movie movie;
	
	public LocalTime getTime()
	{
		return time;
	}

	public void setTime(LocalTime time)
	{
		this.time = time;
	}

	private Long scheduleId;
	
	
	public Long getScheduleId()
	{
		return scheduleId;
	}

	public void setScheduleId(Long scheduleId)
	{
		this.scheduleId = scheduleId;
	}

	public Movie getMovie()
	{
		return movie;
	}

	public void setMovie(Movie movie)
	{
		this.movie = movie;
	}

	public Long getMovieId()
	{
		return movieId;
	}

	public void setMovieId(Long movieId)
	{
		this.movieId = movieId;
	}

	public LocalDate getDate()
	{
		return date;
	}

	public void setDate(LocalDate date)
	{
		this.date = date;
	}

	
}
