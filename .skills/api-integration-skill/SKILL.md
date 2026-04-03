# API Integration Skill

## Purpose

Enable the agent to integrate REST APIs into applications with clean, scalable architecture.

## When to Use

- Fetching data from backend
- Sending user data to server
- Authentication flows
- Connecting mobile apps to APIs

## Core Rules

- Use Retrofit for networking
- Use Kotlin coroutines (suspend functions)
- Handle errors properly
- Keep network logic separate from UI

## Architecture

- API Service → Retrofit interface
- Repository → Handles API calls
- ViewModel → Exposes data to UI

## Networking Guidelines

- Use base URL configuration
- Use proper HTTP methods (GET, POST, PUT, DELETE)
- Use JSON parsing (Gson / Kotlinx Serialization)

## Error Handling

- Handle:
    - Network errors
    - Timeout
    - API errors (4xx, 5xx)
- Return Result type (Success / Error)

## Security

- Never hardcode API keys
- Use secure storage if needed
- Use HTTPS only

## Output Format

- Return complete Kotlin code
- Include:
    - API interface
    - Repository
    - ViewModel usage (if relevant)

## Example Structure

API:

```kotlin
interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}