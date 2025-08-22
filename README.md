# Weather Info API 🌦️

A simple **Spring Boot REST API** that fetches and caches weather information for a given **pincode** and **date**, using the [OpenWeather APIs](https://openweathermap.org/current).  

- Resolves **pincode → latitude/longitude** using OpenWeather Geocoding API  
- Fetches **weather by lat/lon** using OpenWeather Current Weather API  
- Saves both **location** and **weather info** in a MySQL database for faster subsequent lookups  
- Testable via Postman, cURL, or Swagger  
---
## 📌 Features
- REST API endpoint (`/api/v1/weather`)
- Caches responses in MySQL to minimise external API calls
- Stores pincode, latitude, longitude, and weather details
- Optimised for repeated calls (cache hit → no external API call)
---
## 🛠️ Tech Stack
- **Java 17+**
- **Spring Boot 3**
- **Spring Data JPA (Hibernate)**
- **MySQL**
- **OpenWeather API**
---
## ⚙️ Setup & Run
1. Clone repo
git clone https://github.com/your-username/weather-api.git
cd weather-api
2. Run Command
mvn spring-boot:run
3. Endpoint
POST /api/v1/weather
Content-Type: application/json
4. Request
{
  "pincode": "411014",
  "for_date": "2025-08-22",
  "country": "IN"
}
5. Response
{
  "id": 1,
  "pincode": "411014",
  "forDate": "2025-08-22",
  "temperature": 29.3,
  "condition": "Clouds - scattered clouds",
  "windSpeed": 3.2,
  "humidity": 70,
  "timestamp": 1724320000
}
