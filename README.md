#backend

## Requirements
Make sure to have the followings installed:

* Kubernetes
    - Kafka on kubernetes

# 1st Step 
   - Build docker image
   - pushed to dockerHub
# 2nd Step
   - Changed docker image path in k8s/deployment.yml file
# 3rd Step
   - deploy configs.yml and deployment.yml
    - kubectl apply -f configs.yml
   -  kubectl apply -f deployment.yml    
   
