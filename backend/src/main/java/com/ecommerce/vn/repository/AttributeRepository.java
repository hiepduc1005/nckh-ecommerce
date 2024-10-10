package com.ecommerce.vn.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.vn.entity.attribute.Attribute;


public interface AttributeRepository extends JpaRepository<Attribute, UUID> {
}
