# Mancala-Game
[Mancala](https://www.thesprucecrafts.com/how-to-play-mancala-409424), is a 2-player turn-based strategy board game played with small stones and rows of pits on a board. The objective is usually to capture all or some set of the opponent's stones.

![mancala_board](https://user-images.githubusercontent.com/18353476/27533704-66246efa-5a19-11e7-8a00-0ea8d42156a9.png)
![mancala](https://user-images.githubusercontent.com/18353476/39416679-b9c761b6-4c03-11e8-86f6-a1f7ae14791c.jpg)

# Use Cases

Setup Game
1. Enter number of stones
2. Select board style
3. Game is started

Play Turn
1. Click a pit
2. Move Stones according to Mancala rules

Variation #1
1. Start at Step 1
2. Inform player it’s not his turn yet

Variation #2
1. Start at Step 2
2. Click Undo

Variation #3
1. Start at Step 2
2. Player wins

New Game
1. Click new game
2. Board is reset

End Game
1. Click end game
2. Program exits

# Setting up Android Studio
You can run this application using Android Studio's Android device emulator which is heavy on system resources especially the CPU. For best performance build and run the application on your own Android phone.


[Android Studio Setup User Guide(Windows, Mac, and Linux)](https://developer.android.com/studio/intro/index.html) 

[Debugging App on Android](https://developer.android.com/studio/debug/index.html)

[Unit Tests on Android](https://developer.android.com/studio/test/index.html)

[Profiling App Performance on Android](https://developer.android.com/studio/profile/index.html)

[Publishing App on Android](https://developer.android.com/studio/publish/index.html)

![as](https://user-images.githubusercontent.com/18353476/28494127-6da78c40-6eda-11e7-8fa0-d77a5294b193.png)
![creat project](https://user-images.githubusercontent.com/18353476/28494097-63a0df68-6ed9-11e7-929e-3eba9a3f6700.png)
![instant-apps_2x](https://user-images.githubusercontent.com/18353476/28494126-680f3a4e-6eda-11e7-9235-0cd1b4bdf408.png)
![update-channel_2x](https://user-images.githubusercontent.com/18353476/28494098-68114d94-6ed9-11e7-87d2-3c0c30e866ac.png)

# Getting Started with Android NDK
The [Native Development Kit (NDK)](https://developer.android.com/ndk/guides/index.html) is a set of tools that allows you to use C and C++ code with Android, and provides platform libraries you can use to manage native activities and access physical device components, such as sensors and touch input. The NDK may not be appropriate for most novice Android programmers who need to use only Java code and framework APIs to develop their apps. However, the NDK can be useful for cases in which you need to do one or more of the following:

  Squeeze extra performance out of a device to achieve low latency or run computationally intensive applications, such as games or  physics simulations.

  Reuse your own or other developers' C or C++ libraries.

Using Android Studio 2.2 and higher, you can use the NDK to compile C and C++ code into a native library and package it into your APK using Gradle, the IDE's integrated build system. Your Java code can then call functions in your native library through the Java Native Interface (JNI) framework. To learn more about Gradle and the Android build system, read Configure Your Build.
