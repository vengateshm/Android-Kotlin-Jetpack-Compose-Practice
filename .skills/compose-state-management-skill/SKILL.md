# Compose State Management Skill (Expert)

## Purpose

Enable the agent to design, fix, and optimize state management in Jetpack Compose applications using
best practices.

## When to Use

- When building Compose UI with dynamic state
- When UI behaves incorrectly (recomposition issues)
- When state is duplicated or poorly managed
- When migrating to proper state hoisting

## Core Principles

- State should be single source of truth
- Hoist state to the highest necessary level
- Keep UI stateless where possible
- Separate UI state from business logic

## State Types

- UI State → belongs in ViewModel
- Local UI State → use remember
- Derived State → use derivedStateOf
- Flow-based State → use StateFlow / collectAsState

## Rules

### 1. State Hoisting

- Move state up to parent composable or ViewModel
- Pass state + events down

Correct:

- value + onValueChange pattern

### 2. ViewModel Usage

- Store screen state in ViewModel
- Expose immutable state (StateFlow)

### 3. Avoid

- Mutable state scattered across composables
- Business logic inside composables
- Multiple sources of truth

### 4. Recomposition Optimization

- Use remember to cache values
- Use derivedStateOf for computed values
- Avoid unnecessary recomposition triggers

### 5. Event Handling

- Use event callbacks (onClick, onChange)
- Do not mutate state directly inside UI

## Patterns

### UI State Model

- Use data class to represent full screen state

### Example:

data class LoginUiState(
val email: String = "",
val password: String = "",
val isLoading: Boolean = false,
val error: String? = null
)

## Output Rules

- Always follow MVVM
- Use StateFlow in ViewModel
- Use collectAsState() in UI
- Return clean, modular Kotlin code
- No explanations unless asked

## Refactoring Mode

When given code:

- Identify bad state usage
- Refactor to proper hoisting + ViewModel
- Optimize recomposition

## Default Behavior

- When refactoring:
    - Convert to MVVM
    - Create ViewModel if missing
    - Use StateFlow
    - Separate files (UI + ViewModel)

## Optional Enhancements

- Add sealed classes for UI events
- Add loading/error handling patterns