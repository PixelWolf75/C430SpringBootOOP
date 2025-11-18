AI Logs Handler
Eric, Yu Li, Sasha, Kabinan, Jason

Project Overview
This project is an Ai Powered Spring Boot service that uses Gemini API to help DevOps and Software Engineering teams analyze large volumes of logs. The system identifies root causes, summarizes incidents, detects anomalies, recommends fixes and provides chat-based  troubleshooting.
Quick Start
Clone the Repository
git clone https://github.com/PixelWolf75/C430SpringBootOOP.git
Download all Maven Dependencies
Navigate to /resources/application.properties.
Add your Gemini Api Key in application.properties

Project Structure
/src/main
	/java/com/mthree/oopspringboot
		/api: Contains Interface and Implementation of Gemini Api,
		/controller: REST controllers (HTTP entry points)
		/exception: Contains Custom Exceptions
		/entity: Contains Entities
		Main.java: Spring Boot App entry point 
	/resources: Stores Application Properties
	/scala/com/mthree/oopspringboot
		/service: Contains Business logic implemented in Scala
pom.xml: Contains Project Dependencies

Project Usage Example
Please note that you can get 500 responses from any of the following route because of the Gemini 2.5-flash overloaded issue.
Analyze the log
To get an analysis of a log, please make a POST request to http://localhost:8080/api/ai/analyze
The format of the request body should follow the below example:
{
   "filename": "",
   "content": "2025-03-10T14:22:18Z INFO Starting container...\n2025-03-10T14:22:20Z INFO Application initialized successfully.\n2025-03-10T14:22:33Z WARN Slow response detected on /api/data",
   "sourceType": "K8S",
   "createdAt": "2025-03-10T14:23:00"
}

The response you will get if the Gemini model is up and functioning will be:
{
   "id": null,
   "log": {
       "id": null,
       "filename": "",
       "content": "2025-03-10T14:22:18Z INFO Starting container...\n2025-03-10T14:22:20Z INFO Application initialized successfully.\n2025-03-10T14:22:33Z WARN Slow response detected on /api/data",
       "sourceType": "K8S",
       "createdAt": "2025-03-10T14:23:00"
   },
   "analysis": "{\n  \"root_cause\": \"Application performance bottleneck in the /api/data endpoint, possibly due to inefficient processing or database queries.\",\n  \"fix\": \"Investigate and optimize the /api/data endpoint's logic, database queries, and consider implementing caching mechanisms to improve response times.\",\n  \"impact\": \"Degraded user experience due to slow API responses, potentially leading to client-side timeouts and reduced application responsiveness.\"\n}",
   "createdAt": "2025-11-18T12:13:40.132681",
   "summary": null,
   "anomalies": null,
   "recommendation": null
}



Get the summary from a log
To get an analysis of a log, please make a POST request to http://localhost:8080/api/ai/summarize-incident
The format of the request body should follow the below example:
{
   "filename": "",
   "content": "2025-03-10T14:22:18Z INFO Starting container...\n2025-03-10T14:22:20Z INFO Application initialized successfully.\n2025-03-10T14:22:33Z WARN Slow response detected on /api/data",
   "sourceType": "K8S",
   "createdAt": "2025-03-10T14:23:00"
}

The response you will get if the Gemini model is up and functioning will be:
{
   "id": null,
   "log": {
       "id": null,
       "filename": "",
       "content": "2025-03-10T14:22:18Z INFO Starting container...\n2025-03-10T14:22:20Z INFO Application initialized successfully.\n2025-03-10T14:22:33Z WARN Slow response detected on /api/data",
       "sourceType": "K8S",
       "createdAt": "2025-03-10T14:23:00"
   },
   "analysis": null,
   "createdAt": "2025-11-18T14:48:40.360726",
   "summary": "The system initiated its container successfully.\nShortly after, the application reported a successful initialization.\nHowever, a warning was subsequently logged.\nThis warning indicated a slow response time\nwhen accessing the `/api/data` endpoint.",
   "anomalies": null,
   "recommendation": null
}




Detect the anomaly from a log
To get an analysis of a log, please make a POST request to http://localhost:8080/api/ai/detect-anomaly
The format of the request body should follow the below example:
{
   "filename": "",
   "content": "2025-03-10T14:22:18Z INFO Starting container...\n2025-03-10T14:22:20Z INFO Application initialized successfully.\n2025-03-10T14:22:33Z WARN Slow response detected on /api/data",
   "sourceType": "K8S",
   "createdAt": "2025-03-10T14:23:00"
}

The response you will get if the Gemini model is up and functioning will be:
{
   "id": null,
   "log": {
       "id": null,
       "filename": "",
       "content": "2025-03-10T14:22:18Z INFO Starting container...\n2025-03-10T14:22:20Z INFO Application initialized successfully.\n2025-03-10T14:22:33Z WARN Slow response detected on /api/data",
       "sourceType": "K8S",
       "createdAt": "2025-03-10T14:23:00"
   },
   "analysis": null,
   "createdAt": "2025-11-18T14:49:50.499637",
   "summary": null,
   "anomalies": "Based on the *very short* log snippet provided, we can analyze the requested patterns:\n\n```\n2025-03-10T14:22:18Z INFO Starting container...\n2025-03-10T14:22:20Z INFO Application initialized successfully.\n2025-03-10T14:22:33Z WARN Slow response detected on /api/data\n```\n\nHere's what can and cannot be identified:\n\n*   **Sudden spike in errors:**\n    *   **Cannot identify from this log.** The snippet contains no `ERROR` or `SEVERE` level logs at all. To identify a spike, we'd need to see multiple error logs occurring rapidly.\n\n*   **Memory leaks:**\n    *   **Cannot identify from this log.** Memory leaks are very difficult to spot from general application logs unless they lead to explicit `OutOfMemoryError` messages or frequent, prolonged garbage collection logs. This snippet shows none of that.\n\n*   **Slow DB queries:**\n    *   **Potentially indicated, but not confirmed.** The log entry `WARN Slow response detected on /api/data` suggests that an API endpoint is taking too long to respond. A common cause for slow API responses is indeed slow database queries.\n    *   However, it could also be due to slow external API calls, complex computations, or network issues. We cannot definitively say it's a slow DB query *from this log alone*. We'd need more granular logs (e.g., specific ORM/DB driver logs showing query times).\n\n*   **Repeating warnings:**\n    *   **Cannot identify from this log.** The warning `Slow response detected on /api/data` appears only *once* in this snippet. To identify repeating warnings, we would need to see this exact or similar warning appearing multiple times over a period.\n\n**In summary:**\n\nThe only pattern even remotely hinted at is a **potential for slow DB queries** due to the `Slow response detected` warning. All other patterns cannot be identified from such a limited log sample. To find these patterns effectively, you would typically need a much larger dataset of logs over a longer period.",
   "recommendation": null
}


Get a recommended fix
To get an analysis of a log, please make a POST request to http://localhost:8080/api/ai/recommend-fix
The format of the request body should follow the below example:
{
   "filename": "",
   "content": "2025-03-10T14:22:18Z INFO Starting container...\n2025-03-10T14:22:20Z INFO Application initialized successfully.\n2025-03-10T14:22:33Z WARN Slow response detected on /api/data",
   "sourceType": "K8S",
   "createdAt": "2025-03-10T14:23:00"
}

The response you will get if the Gemini model is up and functioning will be:
{
   "id": null,
   "log": {
       "id": null,
       "filename": "",
       "content": "2025-03-10T14:22:18Z INFO Starting container...\n2025-03-10T14:22:20Z INFO Application initialized successfully.\n2025-03-10T14:22:33Z WARN Slow response detected on /api/data",
       "sourceType": "K8S",
       "createdAt": "2025-03-10T14:23:00"
   },
   "analysis": null,
   "createdAt": "2025-11-18T14:51:25.921275",
   "summary": null,
   "anomalies": null,
   "recommendation": "The log entry `2025-03-10T14:22:33Z WARN Slow response detected on /api/data` indicates that your application's `/api/data` endpoint is taking too long to process requests. This is a common performance issue that can stem from various causes.\n\nHere's a breakdown of recommended fixes, categorized for easier investigation and implementation:\n\n## I. Immediate Investigation & Monitoring (Crucial First Steps)\n\n1.  **Enable Detailed Logging/Tracing for `/api/data`:**\n    *   **Action:** Modify your application's logging configuration to log *what happens inside* the `/api/data` endpoint. Log the start and end of major operations within it, and their duration.\n  ..."
}



Chat with the AI model
To chat with the AI model, please make a POST request to http://localhost:8080/api/ai/devops-chat
You can type in a text in the postman just as follows:
hello, gemini.How are you


The response you can get:
{
   "id": null,
   "log": null,
   "analysis": "Hello there!\n\nI am an AI, so I don't experience \"being\" or \"feeling\" in the human sense, but I am fully operational, running smoothly, and ready to assist you.\n\nHow are you doing today? And how can I help you with your DevOps needs?",
   "createdAt": "2025-11-18T13:01:07.418224",
   "summary": null,
   "anomalies": null,
   "recommendation": null
}


