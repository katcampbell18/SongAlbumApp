package com.example.demo.repositories;

import com.example.demo.beans.Album;
import org.springframework.data.repository.CrudRepository;

public interface AlbumRepository extends CrudRepository<Album, Long> {
}
