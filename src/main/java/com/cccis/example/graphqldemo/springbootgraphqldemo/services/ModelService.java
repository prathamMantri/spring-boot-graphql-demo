package com.cccis.example.graphqldemo.springbootgraphqldemo.services;

import com.cccis.example.graphqldemo.springbootgraphqldemo.models.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelService  extends JpaRepository<Model, String> {
}
