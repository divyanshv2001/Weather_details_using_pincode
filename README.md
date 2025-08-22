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

---## ⚙️ Setup & Run

### 1. Clone repo
```bash
git clone https://github.com/your-username/weather-api.git
cd weather-api

## ⚙️ Setup & Run

### 1. Clone repo
```bash
