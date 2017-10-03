package com.redhat.coolstore.inventory.service;

import com.redhat.coolstore.inventory.model.Inventory;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class InventoryService {


    @PersistenceContext(unitName = "primary")
    private EntityManager em;


    public Inventory getInventory(String itemId) {
        return em.find(Inventory.class, itemId);
    }
}

