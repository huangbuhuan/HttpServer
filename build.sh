#!/bin/bash

mvn clean package

docker build -t hbh/http:latest .