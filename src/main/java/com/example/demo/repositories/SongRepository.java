package com.example.demo.repositories;

import com.example.demo.beans.Song;
import org.springframework.data.repository.CrudRepository;

public interface SongRepository extends CrudRepository<Song, Long> {
    }
