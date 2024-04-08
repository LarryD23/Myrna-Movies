package com.acme.jpamvcmyrna;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.math.BigDecimal;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;


import com.acme.jpamvcmyrna.db.MovieRepository;
import com.acme.jpamvcmyrna.db.ScheduledRunRepository;
import com.acme.jpamvcmyrna.db.TicketsSoldRepository;
import com.acme.jpamvcmyrna.domain.Movie;
import com.acme.jpamvcmyrna.domain.ScheduledRun;
//import com.acme.jpamvcmyrna.domain.TicketPricing;
import com.acme.jpamvcmyrna.domain.TicketsSold;

import org.springframework.http.HttpStatus;

@Controller
@RequestMapping("/movie")
public class MovieController
{
	private MovieRepository movieRepository;
	//private TicketsSoldRepository ticketsSoldRepository;
	private ScheduledRunRepository scheduledRunRepository;
	
	
	@Autowired
	public MovieController(MovieRepository movieRepository, ScheduledRunRepository scheduledRunRepository, TicketsSoldRepository ticketsSoldRepository)
	{
		this.movieRepository = movieRepository;
		//this.ticketsSoldRepository = ticketsSoldRepository;
		this.scheduledRunRepository = scheduledRunRepository;
		
	}
	
	@RequestMapping(value="/api", method=GET)
	public @ResponseBody List<Movie>  getMovieList(Model model) 
	{
      List<Movie> movieList = movieRepository.findAll();
	  
	  return movieList;
	}
	
	
	@RequestMapping(value="/api/{id}", method=GET)
	public @ResponseBody Movie getMovieById(@PathVariable Long id) 
	{
      Movie movie= movieRepository.findById(id).orElse(null);
	  
	  return movie;
	}
	
	@RequestMapping(value="/api/create", method=POST)
	public @ResponseBody Movie submitMovieApi( @RequestBody @Valid MovieForm movieForm, Errors errors) {
		System.err.println(movieForm.getMovieName());
		System.err.println("Has errors: " + errors.hasErrors()); 
		Movie movie= new Movie();
		movie.setMovieName(movieForm.getMovieName());
		
		
				
//		if (errors.hasErrors() | region == null) {
//		    return "country/create";
//		}
		
		movie = movieRepository.save(movie);
	 
		return movie;
	}
	
	@RequestMapping(value="/list", method=GET)
	public String getMovies(Model model) {
      List<Movie> movies = movieRepository.findAll();
	  model.addAttribute("movies", movies);
	  System.err.println("SIZE: " + movies.size());
	  for(Movie m: movies)
		  System.err.println("Name: " + m.getMovieName());
	  return "movie/movielist";
	}
	
	@RequestMapping(value = "/{id}", method = GET)
	public String getMovieById(@PathVariable Long id, Model model) {
	    Movie movie = movieRepository.findById(id).orElse(null);
	    //TicketsSold ticket = ticketsSoldRepository.findById(id).orElse(null);
	    

	    if (movie != null) {
	    	List<TicketsSold> ticketsSoldList = movie.getTicketsSoldList();
	    	
	    	double totalSales = 0.0;
	    	
	    	//iterate through and calculate total sales for each movie
	    	for (TicketsSold tic : ticketsSoldList)
	    	{
	    		totalSales += tic.getTotalPrice().doubleValue();
	    	}
	    	
	        model.addAttribute("mov", movie);
	        model.addAttribute("totalSales", totalSales);

	       
	    }

	    return "movie/details";
	}



	
	@RequestMapping(value="/add", method=GET)
	public String showCreateForm(Model model) {
      List<ScheduledRun> schedules = scheduledRunRepository.findAll();
      model.addAttribute("schedules", schedules);
	  model.addAttribute("movieForm", new MovieForm());	
	  return "movie/add";
	}
	
	@RequestMapping(value="/create", method=POST)
	public String submitMovie( @Valid MovieForm movieForm, Errors errors) {
	
		Movie movie = new Movie();
		movie.setMovieName(movieForm.getMovieName());
		
		movie = movieRepository.save(movie);
				
		
	  return "redirect:/movie/" + movie.getMovieId();
	  
	}
	
	@RequestMapping(value="/edit/{id}", method=GET)
	public String showEditForm(@PathVariable Long id, Model model) {
	  Movie movie = movieRepository.findById(id).orElse(null);  
      MovieEditForm form =  new MovieEditForm();
      form.setMovieId(movie.getMovieId());
      form.setMovieName(movie.getMovieName());
      
      
	  model.addAttribute("movieEditForm", form);	
	  return "movie/edit";
	}
	
	
	@RequestMapping(value="/submitedit", method=POST)
	public String submitEditMovie( @Valid MovieEditForm movieEditForm, Errors errors) {

		Movie movie = movieRepository.findById(movieEditForm.getMovieId()).orElse(null);
		movie.setMovieName(movieEditForm.getMovieName());
		
		
		
	
		movie = movieRepository.save(movie);
	 
	  
	  return "redirect:/movie/" + movie.getMovieId();
	  
	}
	
	@RequestMapping(value="/api", method=POST)
	@ResponseStatus(HttpStatus.CREATED) 
	public @ResponseBody Movie postMovie( @RequestBody Movie movie, HttpServletResponse response) {
		System.err.println(movie.getMovieName());
		//System.err.println("Has errors: " + errors.hasErrors()); 
		
		
//		if (errors.hasErrors() | region == null) {
//		    return "create";
//		}
		movie = movieRepository.save(movie);

	  return movie;
	  
	}

	
	
	
}
