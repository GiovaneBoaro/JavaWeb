package com.manga.appmanga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.manga.appmanga.model.Manga;

public interface MangaRepository extends JpaRepository<Manga, Long>{

}
