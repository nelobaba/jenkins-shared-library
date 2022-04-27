#!/usr/bin/env groovy
//import com.example.Docker

def call(String imageName){
    // return new Docker(this).buildDockerImage(imageName)
     echo "building image"
     withCredentials([usernamePassword(credentialsId: 'github-credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]){
         sh "docker build -t $imageName ."
         sh "echo $PASS | docker login -u $USER --password-stdin"
         sh "docker push $imageName"
     }
}