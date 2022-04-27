#!/usr/bin/env groovy

def call(){
     echo "building image"
     withCredentials([usernamePassword(credentialsId: 'github-credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]){
         sh 'docker build -t nelobaba/demo-app:2.0 .'
         sh "echo $PASS | docker login -u $USER --password-stdin"
         sh "docker push nelobaba/demo-app:2.0"
     }
}