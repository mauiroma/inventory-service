# README

This project demostrate wildfly-swam into OCP

## Run Locally
mvn wildfly-swarm:run -Dmaven.test.skip=true

### Testing Locally
```
curl http://localhost:8080/inventory/1
```
### Testing Locally Monitoring fraction
```
curl http://localhost:8080/node
```
```
curl http://localhost:8080/heap
```
```
curl http://localhost:8080/threads
```
```
curl http://localhost:8080/health
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
export ROUTE=`oc get route inventory-service -o template --template='{{.spec.host}}'`
```
```
curl http://${ROUTE}/inventory/1
```

### Testing on OCP Monitoring fraction
```
curl http://${ROUTE}/node
```
```
curl http://${ROUTE}/heap
```
```
curl http://${ROUTE}/threads
```
```
curl http://${ROUTE}/health
```