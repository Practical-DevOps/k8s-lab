#!/usr/bin/env bash

kubectl delete ingress ui-ingress
kubectl delete svc ui
kubectl delete deployment ui
kubectl delete deployment ui-canary

kubectl delete svc message-service
kubectl delete deployment message-service

kubectl delete svc guestbook-service
kubectl delete deployment guestbook-service

kubectl delete svc redis mysql
kubectl delete deployment redis
kubectl delete statefulset mysql

kubectl delete -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v0.40.2/deploy/static/provider/cloud/deploy.yaml