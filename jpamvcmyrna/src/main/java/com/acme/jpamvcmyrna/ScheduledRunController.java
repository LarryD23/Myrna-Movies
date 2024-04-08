package com.acme.jpamvcmyrna;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
@RequestMapping("/schedule")
public class ScheduledRunController
{

	private MovieRepository movieRepository;
	private ScheduledRunRepository scheduleRepository;
	
	@Autowired
	public ScheduledRunController(MovieRepository movieRepository, ScheduledRunRepository scheduleRepository)
	{
		
		this.movieRepository = movieRepository;
		this.scheduleRepository = scheduleRepository;
	}
	
	@RequestMapping(value="/api", method=GET)
	public @ResponseBody List<ScheduledRun>  getScheduleList(Model model) 
	{
      List<ScheduledRun> scheduleList = scheduleRepository.findAll();
	  
	  return scheduleList;
	}
	
	@RequestMapping(value="/api/{id}", method=GET)
	public @ResponseBody ScheduledRun getScheduleById(@PathVariable Long id) 
	{
      ScheduledRun schedule= scheduleRepository.findById(id).orElse(null);
	  
	  return schedule;
	}
	
	@RequestMapping(value="/api/create", method=GET)
	public @ResponseBody ScheduledRun submitScheduleApi(@RequestBody @Valid ScheduledRunForm scheduleForm, Errors errors) {
		
	    
	     ScheduledRun schedule = new ScheduledRun();
	     schedule.setDate(scheduleForm.getDate());
	     schedule.setTime(scheduleForm.getTime());
	            
	    Movie movie = movieRepository.findById(scheduleForm.getMovieId()).orElse(null);
	            	    	     
	   schedule = scheduleRepository.save(schedule);
	   return schedule;
	} 
	
	@RequestMapping(value="api/edit/{id}", method=GET)
	public ScheduledRun editScheduleApi(@PathVariable Long id, @RequestBody @Valid ScheduledRunEditForm scheduleForm)
	{
			ScheduledRun schedule = scheduleRepository.findById(id).orElse(null);
		
           schedule.setDate(scheduleForm.getDate());
           schedule.setTime(scheduleForm.getTime());
           
           //schedule.setMovie(scheduleForm.getMovie());
     
       
             
             return schedule;
      } 
	
	@RequestMapping(value="/list", method=GET)
	public String getSchedules(Model model) 
	{
	    try 
	    {
	        List<ScheduledRun> schedules = scheduleRepository.findAll();
	        if (schedules != null && !schedules.isEmpty()) 
	        {
	            model.addAttribute("schedules", schedules);
	            System.err.println("SIZE: " + schedules.size());
	            for(ScheduledRun s: schedules)
	                System.err.println("Name: " + s.getDate());
	        } 
	        else 
	        {
	            // Handling scenario where tickets list is empty or null
	            System.err.println("No dates found or dates list is empty");
	            
	        }
	    } catch (Exception e) {
	        // Handling other potential exceptions that might occur
	        e.printStackTrace(); 
	        
	    }
	    return "schedule/schedulelist";
	}
	
	@RequestMapping(value="/{id}", method=GET)
	public String getScheduleById(@PathVariable Long id, Model model) 
	{
	    try 
	    {
	        ScheduledRun schedule = scheduleRepository.findById(id).orElse(null);
	        if (schedule != null) 
	        {
	            model.addAttribute("sch", schedule);
	        } 
	        else 
	        {
	            // Handling scenario where schedule is null
	            System.err.println("Schedule not found for ID: " + id);
	           
	        }
	    } 
	    catch (Exception e) 
	    {
	        // Handling other potential exceptions that might occur
	        e.printStackTrace(); // 
	        
	    }
	    return "schedule/details";
	}
	
	@RequestMapping(value="/add", method=GET)
	public String showCreateForm(Model model)
	{
		List<Movie> movies = movieRepository.findAll();
		model.addAttribute("movies", movies);
		
		
		
		
		model.addAttribute("scheduleForm", new ScheduledRunForm());
		
//		model.addAttribute("movieId", "");
		return "schedule/add";
	}
	
	@RequestMapping(value = "/create", method = POST)
	public String submitSchedule(@Valid ScheduledRunForm scheduleForm, Errors errors) {
		System.err.println(scheduleForm.getDate());
		Movie movie = movieRepository.findById(scheduleForm.getMovieId()).orElse(null);
		
		ScheduledRun schedule = new ScheduledRun();
	    	 
		schedule.setMovie(movie);
		System.err.println(scheduleForm.getDate());
		schedule.setDate(scheduleForm.getDate());
		schedule.setTime(scheduleForm.getTime());
		
		schedule = scheduleRepository.save(schedule);
		
		
		
		return "redirect:/schedule/" + schedule.getScheduleId();
	}
	
	@RequestMapping(value="/edit/{id}", method=GET)
	public String showEditForm(@PathVariable Long id, Model model) 
	{
	    ScheduledRun schedule = scheduleRepository.findById(id).orElse(null);
	    
	   
	    
	    if (schedule != null) 
	    {
	        ScheduledRunEditForm form = new ScheduledRunEditForm();
	        form.setScheduleId(schedule.getScheduleId());
	        form.setDate(schedule.getDate());
	        form.setTime(schedule.getTime());
	     
	        
	        model.addAttribute("scheduleEditForm", form);
	        return "schedule/edit";
	    } 
	    else 
	    {
	        // Handle case when schedule is not found
	        return "No Schedule Found"; // You can redirect to an error page or handle it as needed
	    }
	}
	
	@RequestMapping(value="/submitedit", method=POST)
	public String submitEditSchedule( @Valid ScheduledRunEditForm scheduleEditForm) {

		System.err.println(scheduleEditForm.getScheduleId());
		//System.err.println(scheduleEditForm.getMovieId());

		ScheduledRun schedule = scheduleRepository.findById(scheduleEditForm.getScheduleId()).orElse(null);
	    //Movie movie = movieRepository.findById(scheduleEditForm.getMovieId()).orElse(null);
	    		    	    	    		    	    
	    
	    //schedule.setMovie(movie);
//	    LocalDate updateDate = schedule.getDate();
//	    LocalTime updateTime = schedule.getTime();
	    schedule.setDate(scheduleEditForm.getDate());
	    schedule.setTime(scheduleEditForm.getTime());
	   
	    schedule = scheduleRepository.save(schedule);

	    return "redirect:/schedule/" + schedule.getScheduleId(); 
	
	
	
	}
	
	

	
	
	   

	
}
