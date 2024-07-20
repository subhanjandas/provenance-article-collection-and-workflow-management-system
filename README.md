# Provenance: Article Collection and Workflow Management System

Provenance is an application designed to collect and store articles from [infoq.com](https://www.infoq.com/) using a Netflix [Conductor-esque](https://netflix.github.io/conductor/) architecture. The application showcases a common architecture used for data collection and workflow management, demonstrating the integration of background workers, data gateways, and RESTful endpoints.

## Table of Contents

- [Introduction](#introduction)
- [Project Structure](#project-structure)
- [Setup Instructions](#setup-instructions)
- [Running the Application](#running-the-application)
- [Testing](#testing)
- [Running with Docker](#running-with-docker)
- [Software Used](#software-used)
- [Thought Process and Code Explanation](#thought-process-and-code-explanation)
- [Contributing](#contributing)
- [License](#license)

## Introduction

In 2016, a project was initiated to address the growing concerns around fake news. Instead of merely analyzing articles, the focus was on providing context by showcasing the sources of the content. The objective was to bring the origins of news articles and the journalist's context to the forefront, allowing consumers to understand where their news is coming from.

**Provenance** collects and stores articles from [InfoQ](https://www.infoq.com/), utilizing a workflow management system to ensure that the articles are up-to-date and easily accessible. This approach highlights the importance of source transparency in news consumption.

## Project Structure

```bash
provenance/
├── applications/
│   └── provenance-server/
│       ├── src/
│       │   ├── main/
│       │   │   └── java/io/collective/start/
│       │   │       ├── App.java
│       │   │       ├── ArticleServlet.java
│       │   │       ├── ...
│       │   ├── test/
│       │       └── java/test/collective/start/
│       │           ├── AppTest.java
│       │           ├── ...
│       ├── build.gradle
├── components/
│   └── articles/
│       ├── src/
│       │   └── main/
│       │       └── java/io/collective/articles/
│       │           ├── ArticleDataGateway.java
│       │           ├── ArticlesController.java
│       │           ├── ...
│       ├── build.gradle
├── build.gradle
├── settings.gradle
└── README.md

```

## Setup Instructions

### Prerequisites

- **Java Development Kit (JDK)**: Version 11 or higher
- **Gradle**: Version 7.x or higher
- **Docker** (optional, for running with Docker)

### Building the Application

1. Clone the repository and navigate to the project directory:

```bash
git clone https://github.com/subhanjandas/provenance-article-collection-and-workflow-management-system.git
cd provenance-article-collection-and-workflow-management-system
```

2. Build the application:

```bash
./gradlew build
```

## Running the Application

### Running Locally

1. Run the server:

```bash
java -jar applications/provenance-server/build/libs/provenance-server-1.0-SNAPSHOT.jar
```

2. In another terminal, make a request to fetch all articles:

```bash
curl -H "Accept: application/json" http://localhost:8888/articles
```

### Running with Docker

1. Build the Docker image:

```bash
docker build -t provenance-server . --platform linux/amd64
```

2. Run the Docker container:

```bash
docker run -p 8888:8888 provenance-server
```

3. Make a request to fetch all articles:

```bash
curl -H "Accept: application/json" http://localhost:8888/articles
```


## Testing

To run the tests, use the following command:

```bash
./gradlew :components:articles:test
```

```bash
./gradlew :components:endpoints:test 
```
Ensure that all tests pass and review any failures to understand what needs to be fixed.

## Software Used

### Backend
- **Java**: A high-level, class-based, object-oriented programming language designed for flexibility and ease of use.
- **Gradle**: An open-source build automation tool focused on flexibility and performance.
- **Jetty**: A Java web server and servlet container used for serving dynamic web content.
- **JUnit**:  A unit testing framework for Java programming language.
- **Mockito**:  A mocking framework that allows you to create and configure mock objects for unit testing.

### Tools and Libraries
- **Jackson**: A suite of data-processing tools for Java (JSON, XML, CSV, etc).
- **Slf4j**: A simple logging facade for Java.
- **Docker**: A set of platform-as-a-service products that use OS-level virtualization to deliver software in packages called containers.

## Thought Process and Code Explanation

### Architecture Overview

The application is designed to follow a Netflix Conductor-esque architecture. It includes components for collecting articles, managing workflows, and exposing RESTful endpoints. The core components are:
- **Article Collection**: Fetches and stores articles from InfoQ.
- **Workflow Management**: Uses a scheduler to manage background tasks.
- **RESTful Endpoints**: Provides APIs to interact with the stored articles.

### Code Explanation 

- **App.java**: The main entry point of the application. It initializes the workflow scheduler and starts the server to handle requests.


### Thought Process

The goal was to create a modular and extensible architecture. The use of background workers and a scheduler allows for asynchronous processing, which is essential for handling large volumes of data. By following a Conductor-esque architecture, we ensure that the system is scalable and maintainable.

The RESTful endpoints make it easy to interact with the data, and the Docker integration allows for easy deployment across different environments.

## Contributing
Contributions are welcome! Please open an issue or submit a pull request with your changes. Ensure that all new code includes relevant tests and follows the existing coding style.


## License

This project is part of the University of Colorado Boulder's Master of Science in Computer Science, Fundamentals of Software Architecture for Big Data course, available on Coursera.

#### This project is licensed under the University of Colorado Boulder and Coursera.


