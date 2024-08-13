FROM openjdk:22-ea-1

# set the working directory in the container
WORKDIR /app

ENV USERNAME=${USERNAME}
ENV PASSWORD=${PASSWORD}

# copy the dependencies file to the working directory
COPY target/*.jar /app/BooksApp.jar

# PORT
EXPOSE 8080

# create a directory named mytestdir and give all rights (persmission) to it

RUN mkdir /mytestdir && chmod 777 /mytestdir

# command to run on container start
CMD ["java", "-jar", "BooksApp.jar"]

# docker build -t tinumistry/booksapp:1.2 .

# docker run -d -p 8080:8080 -e USERNAME=user123 -e PASSWORD=Pass123 --name booksapp tinumistry/booksapp:1.2

# docker push tinumistry/booksapp:1.2
