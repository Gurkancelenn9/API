package com.pokemonrewiev.api.services;

import com.pokemonrewiev.api.dto.PokemonDto;
import com.pokemonrewiev.api.models.Pokemon;

import java.util.List;


public interface PokemonService {
    PokemonDto createPokemon(PokemonDto pokemonDto);
    public List<PokemonDto> getAllPokemon(int pageNo, int pageSize);
}
