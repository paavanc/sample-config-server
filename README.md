# SampleConfig
 
#### Purpose:
An external server that lets us inject environment variables at run time.

#### Important files:
/src/main/resources/application.properties - environment variables
Dockerfile - docker configuration
pom.xml - java libraries


#### Running Locally:

Pre-req: Java8 and Maven3

#### Installation for mac:

1. Install HomeBrew:
```
/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
```

2. Install Java:

  	1. ```brew tap caskroom/versions```
  
  	2. ```brew cask install java8```
  
3. Install Maven: 

	```brew install maven```
	
4. Run the project:

	In the terminal, run the following command in the demo directory.

	```mvn spring-boot:run```

	The service should be running on port:9080.

5. Run tests: ```mvn package```

   * This command will run the tests and package the project into a jar.

   * If one cds into demo/target, one can also run the jar to start the service.

     ```-java -jar demo-0.0.1-SNAPSHOT.jar```
     
     
#### GET Info Check:

```
curl -X GET \
  http://localhost:9080/actuator/info 
```
Response:
```
{
    "app": {
        "name": "SampleConfig",
        "description": "Loads External properties",
        "version": "1.0.0"
    }
}
```
#### GET Health Check:

```
curl -X GET \
  http://localhost:9080/actuator/health 
```
Response:
```
{
    "status": "UP",
    "details": {
        "diskSpace": {
            "status": "UP",
            "details": {
                "total": 123141343,
                "free": 1234234,
                "threshold": 12343
            }
        },
        "refreshScope": {
            "status": "UP"
        },
        "configServer": {
            "status": "UP",
            "details": {
                "repositories": [
                    {
                        "name": "app",
                        "profiles": [
                            "default"
                        ],
                        "label": null
                    }
                ]
            }
        }
    }
}
```

#### Sample GET:

```
curl -X GET \
  http://localhost:9080/test-dev.properties \
  -H 'Authorization: Basic weewq' \
  ```
Response:
```
server.port=9080
spring.cloud.config.server.git.uri=https://github.com/paavanc/sample-properties.git
spring.cloud.config.server.git.clone-on-start=true
security.user.name=sample
security.user.password=password
management.security.enabled=false
management.port=9080
management.endpoint.health.show-details=always
info.app.name=SampleConfig
info.app.description=Loads External properties
info.app.version=1.0.0
spring.cloud.config.server.git.username=nope
spring.cloud.config.server.git.password=ok
```

Note:
We can also change the response type, following these conventions.
```
/{application}/{profile}[/{label}]
/{application}-{profile}.yml
/{label}/{application}-{profile}.yml
/{application}-{profile}.properties
/{label}/{application}-{profile}.properties
```

#### Docker:

1. Build docker image:
```
mvn package dockerfile:build
```
2. Test docker image:
```
docker-compose -f ./docker-compose-local.yml up
```

#### AWS
1. Build + image to AWS
```
python3 build.py latest
```
2. Deploy to K8s cluster
```
python3 deploy.py latest dev-context
```

#### MiniKube
/k8

1.  
```
minikube start --memory 6000
```
2.
```
kubectl delete secret docker-registry my-docker-credentials
```
3. 
```
kubectl create secret docker-registry my-docker-credentials --docker-server=aws-uri --docker-username=AWS --docker-password=<the long key you got back> --docker-email=<your user>@test.org
```
4.
```
kubectl create -f {fill-in}.yaml
```

config.yaml - environment variables
deployment.yaml - specify pod and replica information
service.yaml - internal router

5.
```
kubectl expose deployment config-deployment --type=LoadBalancer
```

6. 
```
minikube {service name} 
```
	


