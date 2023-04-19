apiVersion: apps/v1
kind: Deployment
metadata:
  name: message-service
  labels:
    app: message-service
    version: "1.0"
spec:
  replicas: 2
  selector:
    matchLabels:
      app: message-service
      version: "1.0"
  template:
    metadata:
      labels:
        app: message-service
        version: "1.0"
    spec:
      serviceAccountName: message-sa
      containers:
      - name: message-service
        image: AWS_ACCOUNT_ID.dkr.ecr.us-east-1.amazonaws.com/AWS_REPO_PREFIX-message:v1
        imagePullPolicy: Always
        resources:
          requests:
            cpu: 200m
            memory: 128Mi
        readinessProbe:
          initialDelaySeconds: 40
          httpGet:
            path: /actuator/health
            port: 9000
        livenessProbe:
          initialDelaySeconds: 40
          httpGet:
            port: 9000
            path: /actuator/health
        ports:
        - name: http
          containerPort: 8080
        - name: metrics
          containerPort: 9000
---
apiVersion: v1
kind: ServiceAccount
metadata:
  name: message-sa
  labels:
    account: message