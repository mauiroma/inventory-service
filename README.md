# README

## Run Locally
mvn wildfly-swarm:run -Dmaven.test.skip=true

### Testing Locally
```
http://localhost:8080/inventory/1
```
### Testing Locally Monitoring fraction
```
http://localhost:8080/node
```
```
http://localhost:8080/heap
```
```
http://localhost:8080/threads
```
```
http://localhost:8080/health
```

## Run on OCP
```
export INVENTORY_PRJ=mr-inventory-swarm 
```

### Create project on OCP
```
oc new-project $INVENTORY_PRJ
```

### Create postgresql database on OCP
```
oc process -f ocp/inventory-service-postgresql-persistent.yaml INVENTORY_DB_USERNAME=jboss INVENTORY_DB_PASSWORD=jboss INVENTORY_DB_NAME=inventorydb | oc create -f - -n $INVENTORY_PRJ
```

### Deploy on OCP

```
oc create configmap app-config --from-file=src/main/resources/project-defaults.yml -n $INVENTORY_PRJ
```
```
mvn clean fabric8:deploy -Popenshift -Dfabric8.namespace=$INVENTORY_PRJ -Dmaven.test.skip=true
```

### Testing on OCP
Get exposed route and then use to call the service
```
oc get route -n $INVENTORY_PRJ
```
```
http://<ROUTE>/inventory/1
```

### Testing on OCP Monitoring fraction
```
http://<ROUTE>/node
```
```
http://<ROUTE>/heap
```
```
http://<ROUTE>/threads
```
```
http://<ROUTE>/health
```