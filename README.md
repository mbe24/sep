# sep [![Java CI with Gradle](https://github.com/mbe24/sep/actions/workflows/gradle.yml/badge.svg)](https://github.com/mbe24/sep/actions/workflows/gradle.yml)
Repository hosting sep, a simple expression parser based on the Shunting-yard algorithm.

### Usage ###

You can run a demo which tests following expressions `2 + 3`, `3 * 2 + 1`, and `3 * -2 + 6`.

```bash

$ ./gradlew run

```
Alternatively, you can use Docker

```bash

$ docker build -t sep .
$ docker run sep

```
