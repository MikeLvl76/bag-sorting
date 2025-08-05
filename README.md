# Shapes in bag

Former school project (named <b>Faire son sac</b>) written in Java. The goal here is to rewrite it and fix its issues. Names were in french but will be back in english.

## Description

The goal is to stack shapes of different dimension in a bag. There are 2D shapes and 3D shapes. 2D shapes come with attributes (e.g. length for a square) and an area computed with these attributes. The same method goes for 3D shapes but with volume instead of area. There are 2 rules when inserting a shape in the bag:
- A 3D shape cannot be placed on top of a 2D shape. But the inverse is true only if the area is twice bigger than the volume.
- A shape can be place on top of another of the same dimension only if its area or volume is bigger.

Rules were a bit different formerly but the main logic remains the same.

## Former rules

As mentionned, rules were not the same as now:
- A sphere (3D shape) could not have another shape stacked on it.
- 4D shapes like <b>glome</b> were handled but it didn't change alot the logic.

# Run

## Old (On Windows)
```
# On Windows
javac old/main/*.java -d "old/out" ; java -cp old/out TestSacADos
```

## Current (On Windows)
```
# Classic mode
javac current/main/*.java -d "current/out" ; java -cp current/out Main

# Random mode (with -r or --random argument)
javac current/main/*.java -d "current/out" ; java -cp current/out Main -r
```
