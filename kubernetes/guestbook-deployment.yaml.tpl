apiVersion: apps/v1
kind: Deployment
metadata:
  name: guestbook-service
  labels:
    app: guestbook-service
spec:
  replicas: 2
  selector:
    matchLabels:
      app: guestbook-service
  template:
    metadata:
      labels:
        app: guestbook-service
        version: "latest"
    spec:
      serviceAccountName: guestbook-sa
      containers:
      - name: guestbook-service
        image: AWS_ACCOUNT_ID.dkr.ecr.us-east-1.amazonaws.com/AWS_REPO_PREFIX-guestbook:v1
        imagePullPolicy: Always
        env:
          - name: DB_HOST
            value: mysql
          - name: DB_USER
            value: root
          - name: DB_PASS
            value: yourpassword
        ports:
        - name: http
          containerPort: 8080
        - name: metrics
          containerPort: 9000
        resources:
          requests:
            cpu: "100m"
          limits:
            cpu: "500m"
---
apiVersion: v1
kind: ServiceAccount
metadata:
  name: guestbook-sa
  labels:
    account: guestbook