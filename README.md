# Installation Instructions

Clone the repo

`git clone https://github.com/alfredamorrissey/spring-vue-ceiling-fan.git`

Enter into the root directory

`cd spring-vue-ceiling-fan`

Run the API tests (these run automatically when you build the artifact)

`./mvnw test `

Build the artifact and the docker container

`docker build -t spring-vue-app .`

Serve the docker container

`docker run -p 8080:8080 spring-vue-app`

You can load the app using the following link

[http://localhost:8080/](http://localhost:8080/)

Click the Speed button to increase the speed from 0-3. If the speed = 3 and you increase the speed, it will restart at 0.

Click the Direction button to toggle between clockwise and counter-clockwise

This endpoint should list all the fans in the database. On initial load of the docker container it should be an empty array.
After you have clicked the buttons it should match the Speed and Direction values on the app. 
The database does not persist if you take the docker container down and back up again because the docker file uses the H2 database.
For a real app you would need to update the dockerfile to add a database and update the application properties to connect to it.

[http://localhost:8080/api/fans](http://localhost:8080/api/fans)

While the docker container is running, you can Run the Jest tests on the Vue.js app

```
cd vue-app
npm run test:unit
```

