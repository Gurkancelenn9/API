package com.pokemonrewiev.api.controllers;

import com.pokemonrewiev.api.dto.PokemonDto;
import com.pokemonrewiev.api.models.Pokemon;
import com.pokemonrewiev.api.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {

    private PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<PokemonDto>> getPokemons(
            @RequestParam (value = "pageNo",defaultValue = "1", required = false) int pageNo,
            @RequestParam (value = "pageSize", defaultValue = "1",required = false) int pageSize){
        return new ResponseEntity<>(pokemonService.getAllPokemon(pageNo,pageSize),HttpStatus.OK);
    }

    /*@GetMapping("/{id}")
    public Pokemon pokemonDetail(@PathVariable int id){
       return new Pokemon(id,"Pikachu","Electric");
    }*/

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PokemonDto> createPokemon(@RequestBody PokemonDto pokemonDto){
        System.out.println(pokemonDto.getName());
        System.out.println(pokemonDto.getType());
        return new ResponseEntity<>(pokemonService.createPokemon(pokemonDto), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.UPGRADE_REQUIRED)
    public ResponseEntity<Pokemon> createPokemon(@RequestBody Pokemon pokemon,  @PathVariable int id){
        System.out.println(pokemon.getName());
        System.out.println(pokemon.getType());
        return new ResponseEntity<>(pokemon, HttpStatus.UPGRADE_REQUIRED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePokemon(@PathVariable int id){
        System.out.println(id);
        return ResponseEntity.ok("Delete");
    }

    /*@PostMapping("/createDto")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PokemonDto> createPokemon(@RequestBody PokemonDto pokemonDto){
        return new ResponseEntity<>(pokemonService.createPokemon(pokemonDto), HttpStatus.CREATED);
    }*/

    @GetMapping("/proje")
    public ResponseEntity<String> getProje(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/todos"; // Harici sitenin API URL'si
        String jsonResponse = restTemplate.getForObject(url, String.class);
        return ResponseEntity.ok(jsonResponse);
    }


}
