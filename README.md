# Android TV Remote Cursor Library

A lightweight, easy-to-integrate Compose UI library that provides TV remote cursor functionality for Android TV applications. This library enables apps with non-TV-friendly UIs to be easily accessible with a TV remote by overlaying a moveable cursor controlled by D-pad navigation.

## Features

- **Compose UI Integration**: Built entirely with Jetpack Compose for modern Android development
- **TV Remote Navigation**: Full support for D-pad movement (up, down, left, right) and center button clicks
- **Easy Integration**: Simple drop-in container that wraps any composable content
- **Customizable Cursor**: Configurable cursor appearance (color, alpha, size)
- **Bounds Management**: Automatic cursor boundary detection and movement constraints
- **Minimal Dependencies**: Lightweight library with only essential Compose dependencies

## Installation

Add the library to your project:

```kotlin
dependencies {
    implementation project(':library')
}
```

## Quick Start

### Basic Usage

Wrap your existing Compose content with `TVRemoteCursorContainer`:

```kotlin
import com.example.tvremotecursor.TVRemoteCursorContainer

@Composable
fun MyTVApp() {
    TVRemoteCursorContainer {
        // Your existing Compose UI
        Column {
            Button(onClick = { /* handle click */ }) {
                Text("Click me with TV remote!")
            }
            // More UI components...
        }
    }
}
```

### Customized Cursor

```kotlin
TVRemoteCursorContainer(
    cursorColor = Color.Yellow,
    cursorAlpha = 0.8f,
    stepSize = 50f,
    onCursorClick = { position ->
        // Handle cursor click at position
        println("Cursor clicked at: $position")
    }
) {
    // Your content here
}
```

## API Reference

### TVRemoteCursorContainer

The main container composable that provides cursor functionality.

**Parameters:**
- `cursorColor: Color` - Color of the cursor overlay (default: Color.White)
- `cursorAlpha: Float` - Alpha transparency of cursor (default: 0.9f)
- `stepSize: Float` - Movement step size for D-pad navigation (default: 40f)
- `onCursorClick: ((Offset) -> Unit)?` - Callback when cursor performs click action

### TVRemoteCursorController

The core controller class that manages cursor state and input handling.

**Key Methods:**
- `handleKeyEvent(KeyEvent): Boolean` - Process TV remote key events
- `setCursorPosition(Offset)` - Programmatically set cursor position
- `initializeCursor(IntSize)` - Initialize cursor to screen center

### Supported Key Events

- `KEYCODE_DPAD_LEFT` - Move cursor left
- `KEYCODE_DPAD_RIGHT` - Move cursor right  
- `KEYCODE_DPAD_UP` - Move cursor up
- `KEYCODE_DPAD_DOWN` - Move cursor down
- `KEYCODE_DPAD_CENTER` / `KEYCODE_ENTER` - Perform click action

## Sample Application

A complete sample app is included in the `sample` module demonstrating:
- Basic cursor integration
- Custom cursor styling
- Button interaction
- TV-optimized UI layout

Run the sample:
```bash
./gradlew :sample:installDebug
```

## Requirements

- Android API 24+
- Jetpack Compose BOM 2024.02.00+
- Kotlin 1.9.24+

## Integration Guide

### For Existing Apps

1. Wrap your main content with `TVRemoteCursorContainer`
2. Ensure your Activity requests focus for key events
3. Test with TV remote or emulator D-pad controls

### TV-Optimized Layouts

- Use larger touch targets (minimum 48dp)
- Provide visual feedback for interactive elements
- Consider cursor movement paths in your layout design
- Test cursor navigation flow with actual TV remote

## Contributing

This library is designed to be minimal and focused. For feature requests or bug reports, please create an issue describing the use case.

## License

This project is open source and available under standard licensing terms.
