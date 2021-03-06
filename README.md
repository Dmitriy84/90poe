# 90POE Data SDET assignment

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

