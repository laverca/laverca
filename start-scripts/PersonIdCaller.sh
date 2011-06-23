#!/bin/sh
cd dist/start-scripts/..
java -classpath 'lib/*:*' fi.laverca.samples.PersonIdCaller $*
