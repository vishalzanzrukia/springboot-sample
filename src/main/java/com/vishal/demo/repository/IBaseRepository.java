package com.vishal.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/***
 * Base repository
 * @param <T>
 */
@NoRepositoryBean
public interface IBaseRepository<T> extends JpaRepository<T, Integer> {
}
