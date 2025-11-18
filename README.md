# AI Logs Handler

Eric, Yu Li, Sasha, Kabinan, Jason

## Project Overview
This project is an Ai Powered Spring Boot service that uses Gemini API to help DevOps and Software Engineering teams analyze large volumes of logs. The system identifies root causes, summarizes incidents, detects anomalies, recommends fixes and provides chat-based troubleshooting.

## Quick Start

1. Clone the Repository
git clone https://github.com/PixelWolf75/C430SpringBootOOP.git

2. Download all Maven Dependencies

3. Navigate to /resources/application.properties.

4. Add your Gemini Api Key

## Project Structure
```text
/src/main
  /java/com/mthree
    /api: Contains Interface and Implementation of Gemini Api
    /controller: REST controllers (HTTP entry points)
    /exception: Contains Custom Exceptions
    /oopspringboot/entity: Contains Entities
    Main.java: Spring Boot App entry point
  /resources: Stores Application Properties
  /scala/com/mthree
    /service: Contains Business logic implemented in Scala
pom.xml: Contains Project Dependencies
```
