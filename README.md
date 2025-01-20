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

[View the app](http://localhost:8080/)

Click the Speed button to increase the speed from 0-3. If the speed = 3 and you increase the speed, it will restart at 0.

Click the Direction button to toggle between clockwise and counter-clockwise

[You can test the endpoints](http://localhost:8080/api/fans)

This should list all the fans in the database. On boot it should be an empty array, 
after you have clicked the buttons it should match the Speed and Direction values on the app.

While the docker container is running, you can Run the Jest tests on the Vue.js app

```
cd vue-app
npm run test:unit
```

