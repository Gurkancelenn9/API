package com.pokemonrewiev.api.repository;

import com.pokemonrewiev.api.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<Pokemon, Integer> { //Integer Pokemon Clasının id'sinin veri tipini belirler

}
