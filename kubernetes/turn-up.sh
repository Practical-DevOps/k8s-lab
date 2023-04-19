#!/usr/bin/env bash

kubectl apply -f mysql-stateful.yaml -f mysql-service.yaml --record
kubectl apply -f redis-deployment.yaml -f redis-service.yaml --record
kubectl apply -f guestbook-deployment.yaml -f guestbook-service.yaml --record
kubectl apply -f message-deployment.yaml -f message-service.yaml --record
kubectl apply -f ui-deployment.yaml -f ui-service.yaml -f ui-ingress.yaml --record
