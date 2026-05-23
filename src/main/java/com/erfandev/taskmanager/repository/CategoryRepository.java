package com.erfandev.taskmanager.repository;

import com.erfandev.taskmanager.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
