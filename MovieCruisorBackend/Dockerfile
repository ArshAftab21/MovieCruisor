FROM java:8-jre
WORKDIR usr/src
ENV MYSQL_DATABASE=moviedb
ENV MYSQL_USER=movieroot
ENV MYSQL_PASSWORD=root1
ENV MYSQL_CI_URL=jdbc:mysql://localhost:3306/moviedb
ADD ./target/MovieCruiserServer-0.0.1-SNAPSHOT.jar /usr/src/MovieCruiserServer-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","MovieCruiserServer-0.0.1-SNAPSHOT.jar"]