# Demo: Spring Boot + Kafka + Avro

## Log into PCF

- `cf login -a <url> -u <student>`
- at the prompt enter your student password

## Modify the cups.json file with values provided by the workshop

## Run "Create User Provided Service"

- ` cf create-user-provided-service cp -p cups.json`
(this will inject the Kakfa credentials at runtime)

## Build the project

- `./mvnw clean package -DskipTests`
(the tests will hang unless you are running kafka locally)

## Deploy to PCF

- `cf push`
(just remember to be in the project root folder with the manifest.yml)

## Test the deployment

- `cf logs stream`
(in another terminal)

- `curl -X POST -d 'name=vik&age=33' https://<route>/user/publish`
(the <route> will be the generated PCF route for pubsub `cf app pubsub | grep routes`)

Change the name and age in the payload and see if the stream filters it or not...
