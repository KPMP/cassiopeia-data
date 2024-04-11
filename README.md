# cassiopeia-data

[![Build Status](https://travis-ci.org/KPMP/cassiopeia-data.svg?branch=develop)](https://travis-ci.org/KPMP/cassiopeia-data)

Repo for the WSI viewer backend. 

# Build

`./gradlew build docker`
The default tag is `latest` if no verison is provided
To pass a version when building the docker image execute
`./gradlew build docker -Ptag=<tagNumber>`