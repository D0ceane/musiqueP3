package com.example.musiqueP3.repository;

import com.example.musiqueP3.entities.Music;
import org.springframework.data.repository.CrudRepository;

public interface MusicRepositoryInterface extends CrudRepository<Music, String> {
}
