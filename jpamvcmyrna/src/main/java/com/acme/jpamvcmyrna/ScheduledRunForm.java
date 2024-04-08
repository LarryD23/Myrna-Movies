package com.acme.jpamvcmyrna;

import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.Date;

import com.acme.jpamvcmyrna.domain.Movie;

import jakarta.validation.constraints.NotNull;

public class ScheduledRunForm
{
	@NotNull
	private LocalDate date;
	
	@NotNull 
	private LocalTime time;
	
	public LocalTime getTime()
	{
		return time;
	}

	public void setTime(LocalTime time)
	{
		this.time = time;
	}

	@NotNull
	private Long movieId;
	
	private Movie movie;

	public Movie getMovie()
	{
		return movie;
	}

	public void setMovie(Movie movie)
	{
		this.movie = movie;
	}

	public LocalDate getDate()
	{
		return date;
	}

	public void setDate(LocalDate date)
	{
		this.date = date;
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
