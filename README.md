# 🎬 Trace-Flix

**Trace-Flix** is a simple microservices-based application built to **learn and practice observability with OpenTelemetry**.

It consists of **three services**:

- `movie-service`
- `actor-service`
- `review-service`

The goal of this application is to demonstrate how distributed tracing, metrics, and logs can help us debug and understand interactions across services. This app is intentionally designed to be simple, predictable, and observable. Feel free to update this project as you wish!

## Services

| Service            | Description                                                                                                                                            | API Endpoint                         |
| ------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------ |
| **actor-service**  | Provides actor details for a given **actor ID**.                                                                                                       | `GET /api/actors/{actorId}`          |
| **review-service** | Provides reviews for a given **movie ID**.                                                                                                             | `GET /api/reviews?movieId={movieId}` |
| **movie-service**  | Maintains movie IDs, basic info, and associated actor IDs. Acts as the main entry point to the system. Acts as the **main entry point** to the system. | `GET /api/movies/{movieId}`          |

## Architecture

![](.images/trace-flix-architecture.png)

The **movie-service** is the central entry point.
For example, when a request is sent for movie ID `2`, it fetches data from both **actor-service** and **review-service** to return complete movie details.

Each service is deployed as a MVC Spring-Boot application, using an in-memory H2 database.

![](.images/trace-flix-service-structure.png)

## Movies API

Example response for `GET /api/movies/2`:

```json
{
  "id": 2,
  "title": "The Godfather",
  "releaseYear": 1972,
  "actors": [
    {
      "id": 3,
      "name": "Marlon Brando"
    },
    {
      "id": 4,
      "name": "Al Pacino"
    }
  ],
  "reviews": [
    {
      "id": 3,
      "rating": 5,
      "comment": "A flawless classic. The tension and performances are unmatched.",
      "reviewer": "Sophie"
    },
    {
      "id": 4,
      "rating": 4,
      "comment": "Great film, but a bit long for my taste.",
      "reviewer": "Leo"
    }
  ]
}
```

### Supported Movie IDs

- **1–7** → Normal & fast responses.
- **8, 9** → Simulated slow responses.
- **10** → Always throws an error (to demonstrate failure scenarios).

This behavior is intentional so we can **observe traces, metrics, and logs under different conditions**.

## Database Config

The H2 database in each service can be configured via the `application.properties` file.

### Database Settings

To give a fixed name to the service database, use the following settings:

![](/.images/trace-flix-h2-settings.png)

The `spring.datasource.url` contains the database name. The last segment of the url is the name of the database. In the above example the database name is `testdb`.

In the default setup, there were no database settings. A random database name will be generated each time the service starts up. This is intentional to keep the setup simple so as to focus on observability aspects of the application.

![](/.images/trace-flix-h2-dbname.png)

### Database Console

To expose the database console for data exploration, use the following settings:

![](/.images/trace-flix-h2-console-settings.png)

In the default `docker-compose.yaml` setup, only `movie-service` has a ports mapping (`8080:8080`), making it the only service accessible directly from the host machine (localhost).

`actor-service` and `review-service` operate entirely within Docker's internal container network. They communicate via container hostnames (`http://actor-service:8080` and `http://review-service:8080`), so their H2 web consoles cannot be reached from your host browser at localhost.

If you need to access the H2 web console for `actor-service` or `review-service`, you will need to expose the container ports to host in `docker-compose.yaml`.

```yaml
actor-service:
  image: ensanguine/actor-service
  build: ./actor-service
  ports:
    - "8081:8080" # Exposes actor-service H2 console at http://localhost:8081/h2-console

review-service:
  image: ensanguine/review-service
  build: ./review-service
  ports:
    - "8082:8080" # Exposes review-service H2 console at http://localhost:8082/h2-console
```

With the approporiate setup done, `http://localhost:<configured-port>/h2-console` in the browser will bring up the console login.

![](/.images/trace-flix-h2-console-login.png)

## Building & Running with Docker

The project includes:

- A **Dockerfile** for each individual service.
- A **docker-compose.yaml** file at the project root to orchestrate all services together.

### Steps

From the **project root directory**, run:

```bash
# 1. build application JAR files. Ensure that you have Java 24+ is installed.
mvn clean package

# 2. build docker images and start all services
docker compose up --build
```

This will build the JARs, create Docker images for each service, and bring up the full Trace-Flix application.

## Testing the Services

Once the application is up and running, we can test it using **Postman** or browser/curl.

### Sample Requests

```bash
# normal & fast responses
http://localhost:8080/api/movies/1
http://localhost:8080/api/movies/2

# simulated slow response
http://localhost:8080/api/movies/9

# simulated error
http://localhost:8080/api/movies/10
```
