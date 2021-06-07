# 90POE Data SDET assignment

## Task execution
#### How to run service:
- launchTestEnv.sh - to launch test environment
- launchTestEnv.sh build - to build docker image for message-server service and launch test environment
#### Message-server service description:
 After launchTestEnv.sh script was successfully executed user will be able to access endpoint:
 - POST http://localhost:80/v1/kafka/publish
 ```
    REQUEST:
    application/json
    BODY:
    {
	    "id": "61",
	    "message": "hello3"
    }

    RESPONSES:
    OK: 200
    BODY: application/json
    {
        "message": "pushed to kafka"
    }
    or 
    Internal Server Error: 500
        - exception happend
        - message id already exist
        - incorrect body
    
```
 - GET http://localhost:80/v1/kafka/consume
```
    RESPONSES:
    OK: 200
    BODY: application/json
    [
        {
            "key": 2,
            "id": 60,
            "message": "hello3"
        },
        {
            "key": 3,
            "id": 61,
            "message": "hello3"
        }
    ]
    or 
    [] in case of empty data
```
##### Note: all messages after call of kafka/consume will be deleted and to get new messages there is a need t call kafka/publish again

#### Test Plan:
TBD

-----------------------------------------------

## Task:
1) Create a Producing and Consuming service that writes verified data into created db.
2) Provide a Test Plan that describes an approach on how to test this system: types, levels of testing, tech stack, tooling.

Allowed languages to use: Java OR Python OR Golang 
Any test framework can be used

## Time limits

The evaluation result of the test is not linked to how much time you spend on it.

## Steps to do:
-	Launch the Kafka cluster in docker (requires the latest docker installed into your host machine) - see Launch test environment below.
-	Create Producer and consumer to store and get data from topic
-	Message format: {long id, String message}
-	Create business logic part to verify data correctness/complentess etc
-	Database should be created
-	Repository layer to store verified data from topic to db

## Would be a plus:
-	dockerize it!
-	add reporting

## Tech Task Result:
-	git repo with README explaining on how to run service and all related info
-	Test Plan in any convenient format also pushed to same git repo


## Launch test environment:
-	execute launchTestEnv.sh script located in test-env folder


## Shut down and cleanup test environment
-	execute stopAndCleanUpTestEnv.sh script located in test-env folder

