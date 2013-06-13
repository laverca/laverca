#!/bin/sh
cd `dirname $0`/..
java -classpath 'lib/*:*' fi.laverca.samples.PersonIdCaller $*
