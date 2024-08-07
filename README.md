# How to build service
1. You should download intellij idea ultimate
2. Open the editor and set java 17 where is written Gradle JVM in File | Settings | Build, Execution, Deployment | Build Tools | Gradle
3. After that set java 17 where is SDK in File | Project Structure
4. After you should build the project 

# How to up the database
1. I hope you have installed docker and docker-compose before. You can check it by commands: docker --version | docker-compose --version
2. Open docker-compose.yml file in the project and click the green triangle button or run in terminal this command : docker-compose up -d
3. If docker container is running then everything is OK

# How to create test user
1. Send the GET request to address localhost:8080/test in order to create test user with email efe30091994@mail.ru and password Quantori2024!

# How to check our service for auth
1. Run the service in intellij idea ultimate, clicking the green triangle button RUN
2. After that you can send requests via Postman
3. To check /login api, send the POST request to address localhost:8080/login with request body {"email" : "efe30091994@mail.ru","password" : "Quantori2024!"}. You will get accessToken
4. To check /profile api, send the Get request to address localhost:8080/profile with header 'Authorization' : 'Bearer value-of-access-token'. You will get response body with information about this user
