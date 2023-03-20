# Numbered Strings [![](https://jitpack.io/v/Thibstars/numbered-strings.svg)](https://jitpack.io/#Thibstars/numbered-strings) [![](https://jitci.com/gh/Thibstars/numbered-strings/svg)](https://jitci.com/gh/Thibstars/numbered-strings)


Utilities for grammatically numbered Strings.

## Usage
This library introduces the `NumberedString` class which comes with utility methods for grammatically numbered Strings (singular/plural words and sentences).

Example:
````java
String itemString = NumberedString.format(
        "Are you sure you'd like to delete %s %s?", 
        selectedItems.size(), 
        "item", "items"
        );
````

## Installation
Import this library into your project via maven using the following snippets in your `pom.xml`:

````xml
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
````

````xml
	<dependency>
	    <groupId>com.github.Thibstars</groupId>
	    <artifactId>numbered-strings</artifactId>
	    <version>currentVersion</version>
	</dependency>
````