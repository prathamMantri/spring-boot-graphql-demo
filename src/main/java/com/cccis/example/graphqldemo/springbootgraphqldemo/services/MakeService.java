package com.cccis.example.graphqldemo.springbootgraphqldemo.services;

import com.cccis.example.graphqldemo.springbootgraphqldemo.models.Make;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MakeService  extends JpaRepository<Make, String> {
}
