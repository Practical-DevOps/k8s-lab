apiVersion: apps/v1
kind: Deployment
metadata:
  name: ui
  labels:
    app: ui
    version: "1.0"
spec:
  replicas: 2
  selector:
    matchLabels:
      app: ui
      version: "1.0"
  template:
    metadata:
      labels:
        app: ui
        version: "1.0"
    spec:
      serviceAccountName: ui-sa
      containers:
      - name: ui
        image: AWS_ACCOUNT_ID.dkr.ecr.us-east-1.amazonaws.com/AWS_REPO_PREFIX-ui:v1
        imagePullPolicy: Always
        env:
          - name: MESSAGE_HOST
            value: http://message-service
          - name: GUESTBOOK_HOST
            value: http://guestbook-service
          - name: REDIS_HOST
            value: redis
        readinessProbe:
          initialDelaySeconds: 40
          httpGet:
            path: /actuator/health
            port: 9000
        ports:
        - name: http
          containerPort: 8080
        - name: metrics
          containerPort: 9000
---
apiVersion: v1
kind: ServiceAccount
metadata:
  name: ui-sa
  labels:
    account: ui