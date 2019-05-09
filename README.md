# Setup Scala Environment
The video demonstration follows the steps to install Scala, SBT and JDK in the development environment. It also covers the steps to install and setup a suitable IntelliJ IDE. I have tweaked the steps to containarize the development environement. Steps are mentioned as below:

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
![alt text](https://gitlab.com/rohit104/summer2019_swati_scalasparkpythonkafka/blob/master/Images/DockerBuild.png "Build Image")

4. ```docker images``` lists the images running in the docker environment.  
![alt text](/Images/DockerImages.png "List Image")
