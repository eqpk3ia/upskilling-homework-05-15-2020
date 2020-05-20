FROM java:8
COPY . /var/www/java
WORKDIR /var/www/java
ADD ./target/todo-0.0.1-SNAPSHOT.jar todo-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD java -jar todo-0.0.1-SNAPSHOT.jar
