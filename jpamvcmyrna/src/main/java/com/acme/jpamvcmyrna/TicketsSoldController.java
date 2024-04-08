package com.acme.jpamvcmyrna;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
import com.acme.jpamvcmyrna.db.TicketPriceTypeRepository;
//import com.acme.jpamvcmyrna.db.TicketPricingRepository;
import com.acme.jpamvcmyrna.domain.Movie;
import com.acme.jpamvcmyrna.domain.ScheduledRun;
import com.acme.jpamvcmyrna.domain.TicketPriceType;
//import com.acme.jpamvcmyrna.domain.TicketPricing;
import com.acme.jpamvcmyrna.domain.TicketsSold;
import com.acme.mvcjpademo.domain.Country;

import org.springframework.http.HttpStatus;

@Controller
@RequestMapping("/ticket")
public class TicketsSoldController
{
	private TicketsSoldRepository ticketRepository;
	private TicketPriceTypeRepository ticketPriceTypeRepository;
	private MovieRepository movieRepository;
	private ScheduledRunRepository scheduleRepository;
	
	@Autowired
	public TicketsSoldController(TicketsSoldRepository ticketRepository,MovieRepository movieRepository, TicketPriceTypeRepository ticketPriceTypeRepository, ScheduledRunRepository scheduleRepository)
	{
		this.ticketRepository = ticketRepository;
		this.ticketPriceTypeRepository = ticketPriceTypeRepository;
		this.movieRepository = movieRepository;
		this.scheduleRepository = scheduleRepository;
	}
	
	
	@RequestMapping(value="/api", method=GET)
	public @ResponseBody List<TicketsSold>  getTicketList(Model model) 
	{
      List<TicketsSold> ticketList = ticketRepository.findAll();
	  
	  return ticketList;
	}
	
	@RequestMapping(value="/api/{id}", method=GET)
	public @ResponseBody TicketsSold getTicketById(@PathVariable Long id) 
	{
      TicketsSold ticket= ticketRepository.findById(id).orElse(null);
	  
	  return ticket;
	}
	
	
	@RequestMapping(value="/api/create", method=GET)
	public @ResponseBody TicketsSold submitTicketApi(@RequestBody @Valid TicketsSoldForm ticketForm, Errors errors) {
		
	    
	     TicketsSold ticket = new TicketsSold();
	     ticket.setTicketPriceType(ticketForm.getTicketPriceType());
	            
	     Movie movie = movieRepository.findById(ticketForm.getMovieId()).orElse(null);
	            
	     ticket.setQuantity(ticketForm.getQuantity());
	     

	          
	   ticket = ticketRepository.save(ticket);
	   return ticket;
	} 
	        
	    
	
	@RequestMapping(value="api/edit/{id}", method=GET)
	public TicketsSold editTicketApi(@PathVariable Long id, @RequestBody @Valid TicketsSoldEditForm ticketForm)
	{
			TicketsSold ticket = ticketRepository.findById(id).orElse(null);
		
           ticket.setTicketPriceType(ticketForm.getTicketPriceType());
           ticket.setQuantity(ticketForm.getQuantity());
       
             
             return ticket;
     } 
	
	
	@RequestMapping(value="/list", method=GET)
	public String getTickets(Model model) 
	{
	    try 
	    {
	        List<TicketsSold> tickets = ticketRepository.findAll();
	        if (tickets != null && !tickets.isEmpty()) 
	        {
	            model.addAttribute("tickets", tickets);
	            System.err.println("SIZE: " + tickets.size());
	            for(TicketsSold t: tickets)
	                System.err.println("Name: " + t.getTicketPriceType());
	        } 
	        else 
	        {
	            // Handling scenario where tickets list is empty or null
	            System.err.println("No tickets found or tickets list is empty");
	            
	        }
	    } catch (Exception e) {
	        // Handling other potential exceptions that might occur
	        e.printStackTrace(); 
	        
	    }
	    return "ticket/ticketlist";
	}
	

	
	@RequestMapping(value="/{id}", method=GET)
	public String getTicketById(@PathVariable Long id, Model model) 
	{
	    try 
	    {
	        TicketsSold ticket = ticketRepository.findById(id).orElse(null);
	        if (ticket != null) 
	        {
	            model.addAttribute("tic", ticket);
	        } 
	        else 
	        {
	            // Handling scenario where ticket is null
	            System.err.println("Ticket not found for ID: " + id);
	           
	        }
	    } 
	    catch (Exception e) 
	    {
	        // Handling other potential exceptions that might occur
	        e.printStackTrace(); // 
	        
	    }
	    return "ticket/details";
	}
	@RequestMapping(value="/add", method=GET)
	public String showCreateForm(Model model)
	{
		List<Movie> movies = movieRepository.findAll();
		model.addAttribute("movies", movies);
		
		List<ScheduledRun> schedules = scheduleRepository.findAll();
		
		//Filter the schedules
		List<ScheduledRun> filteredSchedules = filterSchedules(schedules);
		
		model.addAttribute("schedules", filteredSchedules);
		
		// Retrieve all TicketPriceTypes and add them to the model
        List<TicketPriceType> ticketPriceTypes = ticketPriceTypeRepository.findAll();
        model.addAttribute("ticketPriceTypes", ticketPriceTypes);
        
		
		model.addAttribute("ticketForm", new TicketsSoldForm());
		return "ticket/add";
	}
	
	private List<ScheduledRun> filterSchedules(List<ScheduledRun> schedules) {
	    LocalDate currentDate = LocalDate.now();
	    LocalDate threeDaysAgo = currentDate.minusDays(3);

	    List<ScheduledRun> filteredSchedules = new ArrayList<>();
	    for (ScheduledRun schedule : schedules) {
	        LocalDate scheduleDate = schedule.getDate();
	        // Filter schedules that fall within the date range
	        if ((scheduleDate.isEqual(currentDate) || scheduleDate.isAfter(threeDaysAgo)) && scheduleDate.isBefore(currentDate.plusDays(1))) {
	            filteredSchedules.add(schedule);
	        }
	    }
	    return filteredSchedules;
	}
	
	@RequestMapping(value = "/create", method = POST)
	public String submitTicket(@Valid TicketsSoldForm ticketForm, Errors errors) {
		System.err.println(ticketForm.getMovieId());
	    // Fetch the TicketPriceType object using the ticketPriceTypeId directly
	    TicketPriceType ticketPriceType = ticketPriceTypeRepository.findById(ticketForm.getTicketPriceTypeId()).orElseGet(null);
	    //System.err.println(ticketForm.getScheduledRun().getDate());

	    // Fetch the Movie based on the provided ID from the form
	    Movie movie = movieRepository.findById(ticketForm.getMovieId()).orElse(null);
	    
	    
	    
	    //Fetch the date based on the provided ID from the form
	    //ScheduledRun date = scheduleRepository.findById(ticketForm.getScheduleId()).orElse(null);
	    ScheduledRun time = scheduleRepository.findById(ticketForm.getScheduleId()).orElse(null);
	   
	    // Fetch all scheduled runs from the repository
	    //List<ScheduledRun> allScheduledRuns = scheduleRepository.findAll();
	    
	    // Get today's date
	    //LocalDate today = LocalDate.now();

	    // Calculate the date two days before today
	    //LocalDate twoDaysBefore = today.minusDays(2);
	    


	    TicketsSold ticket = new TicketsSold();
	    ticket.setTicketPriceType(ticketPriceType);
	    
	    ticket.setQuantity(ticketForm.getQuantity());
	    ticket.setMovie(movie);
	   
	    //ticket.setScheduledRun(date);
	    ticket.setScheduledRun(time);
	   

	    // Calculate total price by multiplying quantity with the ticket price
	    BigDecimal totalPrice = ticketPriceType.getPriceValue().multiply(BigDecimal.valueOf(ticketForm.getQuantity()));
	    ticket.setTotalPrice(totalPrice); // Set the total price in the TicketsSold object

	    ticket = ticketRepository.save(ticket);

	    return "redirect:/ticket/" + ticket.getTicketSoldId();
	}

	
	@RequestMapping(value="/edit/{id}", method=GET)
	public String showEditForm(@PathVariable Long id, Model model) {
	    
		TicketsSold ticket = ticketRepository.findById(id).orElse(null);
	    List<Movie> movies = movieRepository.findAll();
	    List<TicketPriceType> ticketPriceTypes = ticketPriceTypeRepository.findAll();
	    List<ScheduledRun> schedules = scheduleRepository.findAll();
	    model.addAttribute("movies", movies);
	    model.addAttribute("ticketPriceTypes", ticketPriceTypes);
	    model.addAttribute("schedules", schedules);
	    TicketsSoldEditForm form = new TicketsSoldEditForm();
	    
	    form.setTicketSoldId(ticket.getTicketSoldId());
	    form.setTicketPriceTypeId(ticket.getTicketPriceType().getPriceTypeId());// Set the ticketPriceTypeId
	   
	    form.setQuantity(ticket.getQuantity());

	    model.addAttribute("ticketEditForm", form);
	    return "ticket/edit";
	}


	
	@RequestMapping(value="/submitedit", method=POST)
	public String submitEditTicket( @Valid TicketsSoldEditForm ticketEditForm) {

	    
		TicketsSold ticket = ticketRepository.findById(ticketEditForm.getTicketSoldId()).orElse(null);
		TicketPriceType priceType = ticketPriceTypeRepository.findByPriceTypeId(ticketEditForm.getTicketPriceTypeId());
		
		
		ticket.setTicketPriceType(priceType);
	
	    //Movie movie = movieRepository.findById(ticketEditForm.getMovieId()).orElse(null);
	    
	    //ScheduledRun schedule = scheduleRepository.findById(ticketEditForm.getScheduledRunId()).orElse(null);

	 
	    


	    ticket.setQuantity(ticketEditForm.getQuantity());
	    
	    BigDecimal totalPrice = ticket.getTicketPriceType().getPriceValue().multiply(BigDecimal.valueOf(ticket.getQuantity()));
	    ticket.setTotalPrice(totalPrice); // Set the total price in the TicketsSold object
	    
	    
	    //ticket.setMovie(movie);
	    //ticket.setScheduledRun(schedule);

	    ticket = ticketRepository.save(ticket);

	    return "redirect:/ticket/" + ticket.getTicketSoldId(); 
	
	
	
	}
}

	
	
	

