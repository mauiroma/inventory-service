package com.redhat.coolstore.inventory.rest;


import com.redhat.coolstore.inventory.model.Inventory;
import com.redhat.coolstore.inventory.service.InventoryService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/inventory")
public class InventoryResource{
    @Inject
    InventoryService inventoryService;


    @GET
    @Path("/{itemId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Inventory getInventory(@PathParam("itemId")String itemId) {
        System.out.println("Get Inventory Item ["+itemId+"]");
        Inventory inventory = inventoryService.getInventory(itemId);
        if (inventory == null) {
            System.out.println("Inventory is NULL");
            throw new NotFoundException();
        }
        return inventory;
    }
}
