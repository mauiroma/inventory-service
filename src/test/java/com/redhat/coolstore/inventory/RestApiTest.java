package com.redhat.coolstore.inventory;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.arquillian.CreateSwarm;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RunWith(Arquillian.class)
public class RestApiTest {

    @CreateSwarm
    public static Swarm newContainer() throws Exception {
        return new Swarm().withProfile("local");
    }

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addPackages(true, RestApplication.class.getPackage())
                .addAsResource("project-local.yml", "project-local.yml")
                .addAsResource("META-INF/test-persistence.xml",  "META-INF/persistence.xml")
                .addAsResource("META-INF/test-load.sql",  "META-INF/test-load.sql");
    }


    @Test
    @RunAsClient
    public void inventoryItem() throws Exception {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080").path("/inventory").path("/1");
        Response response = target.request(MediaType.APPLICATION_JSON).get();
        JsonObject value = Json.parse(response.readEntity(String.class)).asObject();

        String location = value.getString("location", "null");
        System.out.println(location);
//        Assert.assertNotNull("Should have the injected the CDI bean manager", inventoryService);
        Assert.assertEquals("Rome", location);
    }


    @Test
    @RunAsClient
    public void wrongInventoryItem(){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080").path("/inventory").path("/666");
        Response response = target.request(MediaType.APPLICATION_JSON).get();
        System.out.println("-------->"+response.getStatus());
        Assert.assertTrue(response.getStatus()==404);
    }
}
