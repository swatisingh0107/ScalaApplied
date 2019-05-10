# Setup Scala Environment
The video demonstration follows the steps to install Scala, SBT and JDK in the development environment. It also covers the steps to install and setup a suitable IntelliJ IDE. I have tweaked the steps to containarize the development environement. Steps are mentioned as below:

## Setup Dockerfile with build configuration

1. Run Git bash    
```   
$ pwd  
/c/Users/swati/Box Sync/Independent Study/SpecialTopics/summer2019_swati_scalasparkpythonkafka
$ touch Dockerfile
#Dockerfile contains the image installation for Scala-SBT-JDK
```
2. Add the dockerfile without an extension into the working directory
[Dockerfile](https://gitlab.com/rohit104/summer2019_swati_scalasparkpythonkafka/blob/master/Dockerfile)

3. Build dockerfile to create image. A Dockerfile is a script that contains collections of commands and instructions that will be automatically executed in sequence in the docker environment for building a new docker image.
```
docker build -t scalabuild:1.0 .
```   
![alt text](/master/Images/DockerBuild.png "Build Image")

4. ```docker images``` lists the images running in the docker environment.  
<<<<<<< HEAD
![alt text](/Images/DockerImages.png "Image List")

## Setup IntelliJ with Development Environment
We will integrate IntelliJ with Docker to fetch the configuration from the Dockerfile.

1. Goto Files->Setting->Plugins0->Docker Integration->install
2. Docker should now be available under File->Settings->Build,Execution,Deployment
3. Add the docker machine and map the path to the working directory. Test the connection.    
![alt text](/Images/MapDockerMachine.png "Map Docker Machine")  

### Test setup
We will now run a test module to check that the correct version of scala and sbt are configured into our project. For this download and unzip the [test project](https://gitlab.com/rohit104/summer2019_swati_scalasparkpythonkafka/tree/master/TestSetup) provided in the tutorial. Open this project in IntelliJ.
1. Deploy build configuration from Dockerfile   
![alt text][/Images/AddDockerBuildConfig.gif]
2. Run build config
![alt text][/Images/RunBuildConfig.gif]
