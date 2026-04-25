package com.mundial.app.Repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mundial.app.Entidades.Entrenador;

public interface EntrenadorRepositorio extends MongoRepository<Entrenador, String> {}
