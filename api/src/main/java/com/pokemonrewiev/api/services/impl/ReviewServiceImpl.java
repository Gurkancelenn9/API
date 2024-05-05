package com.pokemonrewiev.api.services.impl;

import com.pokemonrewiev.api.dto.PokemonDto;
import com.pokemonrewiev.api.dto.ReviewDto;
import com.pokemonrewiev.api.exceptions.PokemonNotFoundException;
import com.pokemonrewiev.api.models.Pokemon;
import com.pokemonrewiev.api.models.Review;
import com.pokemonrewiev.api.repository.PokemonRepository;
import com.pokemonrewiev.api.repository.ReviewRepository;
import com.pokemonrewiev.api.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    ReviewRepository reviewRepository;
    PokemonRepository pokemonRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, PokemonRepository pokemonRepository) {
        this.reviewRepository = reviewRepository;
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public ReviewDto createReview(int pokemonId, ReviewDto reviewDto) {
        List<Review> reviews = reviewRepository.findByPokemonId(pokemonId);
        if(reviews.isEmpty()){
            Review review = mapToEntity(reviewDto);
            Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(()->new PokemonNotFoundException("Pokemon not found"));
            review.setPokemon(pokemon);



            Review newReview = reviewRepository.save(review);
            return mapToDto(newReview);
        }else {
            return null;
        }
    }

    @Override
    public List<ReviewDto> getReviewByPokemonId (int id) {
        List<Review> reviews = reviewRepository.findByPokemonId(id);
        return reviews.stream().map(review -> mapToDto(review)).collect(Collectors.toList());
    }

    private ReviewDto mapToDto(Review review){
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setTitle(review.getTitle());
        reviewDto.setContent(review.getContent());
        reviewDto.setStart(review.getStart());
        return reviewDto;
    }

    private Review mapToEntity(ReviewDto reviewDto){
        Review review = new Review();
        review.setId(reviewDto.getId());
        review.setContent(reviewDto.getContent());
        review.setTitle(reviewDto.getTitle());
        review.setStart(reviewDto.getStart());
        return review;
    }

}
