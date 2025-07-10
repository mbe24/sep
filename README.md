# sep [![Java CI with Gradle](https://github.com/mbe24/sep/actions/workflows/gradle.yml/badge.svg)](https://github.com/mbe24/sep/actions/workflows/gradle.yml)
Repository hosting sep, a simple expression parser based on abstract syntax trees (AST). It supports two construction strategies: the Shunting-yard algorithm and recursive descent parsing.

### Usage ###

You can run a demo which tests following expressions `2 + 3`, `3 * 2 + 1`, and `3 * -2 + 6` relying on recursive descent parsing.

```bash

$ ./gradlew run

```
Alternatively, you can use Docker

```bash

$ docker build -t sep .
$ docker run sep

```
