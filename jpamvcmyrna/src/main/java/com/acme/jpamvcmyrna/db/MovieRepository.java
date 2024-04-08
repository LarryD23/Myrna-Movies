package com.acme.jpamvcmyrna.db;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acme.jpamvcmyrna.domain.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>
{
	Movie findByMovieName(String movieName);
	Movie findByMovieId (Long movieId);
}
