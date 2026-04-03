# Android Development Skill

## Purpose

Enable the agent to generate high-quality Android applications using modern best practices.

## When to Use

- Android UI development
- Feature implementation
- App architecture design
- Bug fixing and refactoring

## Core Rules

- Always use Kotlin
- Prefer Jetpack Compose over XML
- Follow MVVM architecture
- Use clean architecture principles where applicable

## Architecture Guidelines

- UI layer → Composables
- State → ViewModel
- Data → Repository pattern
- Use separation of concerns strictly

## UI (Jetpack Compose)

- Use Material3 components
- Maintain proper spacing (8dp / 16dp standard)
- Use Column, Row, Box correctly
- Avoid deeply nested layouts
- Use remember and state hoisting properly

## Code Style

- camelCase → variables/functions
- PascalCase → composables/classes
- Small, reusable composables
- Avoid hardcoded strings (use resources)

## State Management

- Use ViewModel
- Use StateFlow or LiveData
- Handle loading, success, error states

## Error Handling

- Always handle null cases
- Show user-friendly messages
- Log errors where necessary

## Output Format

- Return ONLY Kotlin code
- No explanations unless explicitly requested

## Optional Enhancements

- Add navigation if multiple screens
- Add dependency injection (Hilt) if needed
- Add previews for composables

## Example Tasks

- Login screen
- Dashboard UI
- API data list
- Form validation