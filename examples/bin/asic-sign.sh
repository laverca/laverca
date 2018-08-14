#!/bin/sh
cd `dirname $0`/..
java -classpath '../libs/*:./libs/*:*:../*' fi.laverca.examples.etsi.ASiC $*
