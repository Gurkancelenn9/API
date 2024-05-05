package com.pokemonrewiev.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   private String title;
   private String content;
   private int start;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "pokemon_id") //mySQL'de oluşturaln *sütunun* başlığı: name
   private Pokemon pokemon;
}
