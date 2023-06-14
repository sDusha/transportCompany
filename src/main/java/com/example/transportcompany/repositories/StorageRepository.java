package com.example.transportcompany.repositories;

import com.example.transportcompany.models.Storage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StorageRepository extends JpaRepository<Storage, Long> {
    List<Storage> findByTitle(String title);
}
