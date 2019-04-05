# Parking System
This is a sample parking lot system which handles vehicle entry and exit flows

### Class Diagram of ParkingLot System
![alt text](https://raw.githubusercontent.com/Joysond/ParkingSystem/master/parkingsystem/ClassDiagram.jpg)

### Follow below steps to create database (In my project I have used postgres database)
1. Create empty databse with the name **parkingsystem**
2. Update [application.properties](https://github.com/Joysond/ParkingSystem/blob/master/parkingsystem/src/main/resources/application.properties) for databse username, password, url
3. Set **spring.jpa.hibernate.ddl-auto** property to **create**, This creates the required tables for you in database.<br/> **Note** : Make sure that you run the springboot jar only once by setting this property to **create**. Once it creates the required tables set this property to **validate**

### Parking System API calls
1. **Create Parking Lot** : This API creates parking lot with the given number of sections and lots.<br/>**Method Type** : POST<br/>**Url** : http://localhost:8080/parking-system/parking-lot<br/>**Header** : Content-Type : application/json<br/>**Body** : 
```json
{
  "sections" : [
    {
      "number": 1,
      "slots" : [
        {
          "isFree" : true
        },
        {
          "isFree" : true
        },
        {
          "isFree" : true
        },
        {
          "isFree" : true
        }
      ]
    },
    {
      "number" : 2,
      "slots" : [
        {
          "isFree" : true
        },
        {
          "isFree" : true
        },
        {
          "isFree" : true
        },
        {
          "isFree" : true
        }
      ]
    }
  ],
  "gates" : [
    {
      "number" : 1
    },
    {
      "number" : 2
    }
  ]
}
```
**Result** : 
```json
{
"msg": "Parking Lot Created",
"dateTime": "2019-04-01T12:04:49.334+0000"
}
```
2. **Get Parking Lot** : Returns the json representation of the parking lot.<br/>**Method Type** : GET<br/>**Url** : http://localhost:8080/parking-system/parking-lot<br/>**Result** : 
```json
{"id":1,"sections":[{"id":1,"number":1,"slots":[{"id":2,"isFree":true,"vehicle":null},{"id":3,"isFree":true,"vehicle":null},{"id":4,"isFree":true,"vehicle":null},{"id":1,"isFree":true,"vehicle":null}]},{"id":2,"number":2,"slots":[{"id":5,"isFree":true,"vehicle":null},{"id":6,"isFree":true,"vehicle":null},{"id":8,"isFree":true,"vehicle":null},{"id":7,"isFree":true,"vehicle":null}]}],"gates":[{"id":1,"number":1},{"id":2,"number":2}]}
```
3. **Create Vehicle Entry Flow** : Enters the vehicle information in the system.<br/>**Method Type** : POST<br/>**Url** : http://localhost:8080/parking-system/vehicle-entry-flow/{gateId}/{slotId}<br/>**Header** : Content-Type : application/json<br/>**Body** : 
```json
{
  "number" : "KA 01 JK 8800",
  "type" : 0
}
```
**Result** : 
```json
{
"msg": "Vehicle entry created",
"dateTime": "2019-04-02T04:54:22.258+0000"
}
```
4. **Create Vehicle Exit Flow** : Enters the vehicle exit time and free's the occupied slot and makes entry in VehicleEntry table<br/>**Method Type** : POST<br/>**Url** : http://localhost:8080/parking-system/vehicle-exit-flow/{gateId}/{slotId}/{amount}<br/>**Header** : Content-Type : application/json<br/>**Body** : 
```json
{
  "id" : 52
}
```
**Result** : 
```json
{
"msg": "Vehicle exit flow created",
"dateTime": "2019-04-02T04:57:20.467+0000"
}
```
5. **Get Vechile Entries** : Returns all the vehicle entries in the system.<br/>**Method Type** : GET<br/>**Url** : http://localhost:8080/parking-system/vehicle-entries<br/>**Result** : 

```json
[{"id":1,"inTime":"2019-04-01T12:05:03.062+0000","outTime":"2019-04-01T12:05:34.021+0000","vehicle":{"id":1,"number":"KA 01 JK 8800","type":"CAR","info":{"id":1,"inTime":"2019-04-01T12:05:03.062+0000","outTime":"2019-04-01T12:05:34.021+0000","amount":0,"entryGate":{"id":1,"number":1},"exitGate":{"id":2,"number":2}}},"entryGate":{"id":1,"number":1},"exitGate":{"id":2,"number":2},"slot":{"id":1,"isFree":true,"vehicle":null}},{"id":52,"inTime":"2019-04-02T04:54:22.102+0000","outTime":"2019-04-02T04:57:20.354+0000","vehicle":{"id":52,"number":"KA 01 JK 8800","type":"CAR","info":{"id":52,"inTime":"2019-04-02T04:54:22.102+0000","outTime":"2019-04-02T04:57:20.354+0000","amount":100,"entryGate":{"id":2,"number":2},"exitGate":{"id":2,"number":2}}},"entryGate":{"id":2,"number":2},"exitGate":{"id":2,"number":2},"slot":{"id":7,"isFree":true,"vehicle":null}}]
```
