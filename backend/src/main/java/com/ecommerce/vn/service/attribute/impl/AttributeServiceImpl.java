package com.ecommerce.vn.service.attribute.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.vn.entity.attribute.Attribute;
import com.ecommerce.vn.repository.AttributeRepository;
import com.ecommerce.vn.service.attribute.AttributeService;


@Service
@Transactional
public class AttributeServiceImpl implements AttributeService {

    @Autowired
    private AttributeRepository attributeRepository; 

    @Override
    public Attribute createAttribute(Attribute attribute) {
        return attributeRepository.save(attribute);
    }

    @Override
    public Attribute updateAttribute(UUID id, Attribute attributeDetails) {

        Optional<Attribute> optionalAttribute = attributeRepository.findById(id);
        if (optionalAttribute.isPresent()) {
            Attribute attribute = optionalAttribute.get();
            
            attribute.setAttributeName(attributeDetails.getAttributeName());
            attribute.setActive(attributeDetails.getActive());
            
            return attributeRepository.save(attribute);
        } else {
            throw new RuntimeException("Attribute not found with id: " + id);
        }
    }

    @Override
    public void deleteAttribute(UUID id) {
        
        Optional<Attribute> optionalAttribute = attributeRepository.findById(id);
        if (optionalAttribute.isPresent()) {
            attributeRepository.delete(optionalAttribute.get());
        } else {
            throw new RuntimeException("Attribute not found with id: " + id);
        }
    }

    @Override
    public Attribute getAttributeById(UUID id) {
        // Tìm attribute theo id
        Optional<Attribute> optionalAttribute = attributeRepository.findById(id);
        if (optionalAttribute.isPresent()) {
            return optionalAttribute.get();
        } else {
            throw new RuntimeException("Attribute not found with id: " + id);
        }
    }

    @Override
    public List<Attribute> getAllAttributes() {
        return attributeRepository.findAll();
    }

}

