package com.redhat.coolstore.inventory.service;

import com.redhat.coolstore.inventory.model.Inventory;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.arquillian.CreateSwarm;

import javax.inject.Inject;

@RunWith(Arquillian.class)
public class InventoryServiceTest {

    @Inject
    InventoryService inventoryService;

    @CreateSwarm
    public static Swarm newContainer() throws Exception {
        return new Swarm().withProfile("local");
    }

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addPackages(true, InventoryService.class.getPackage())
                .addPackages(true, Inventory.class.getPackage())
                .addAsResource("project-local.yml", "project-local.yml")
                .addAsResource("META-INF/test-persistence.xml",  "META-INF/persistence.xml")
                .addAsResource("META-INF/test-load.sql",  "META-INF/test-load.sql");
    }


    @Test
    public void inventoryItem() throws Exception {

        String itemId = "1";

        Assert.assertNotNull("Should have the injected the CDI bean manager", inventoryService);

        Assert.assertEquals("Rome", inventoryService.getInventory(itemId).getLocation());

    }

}
