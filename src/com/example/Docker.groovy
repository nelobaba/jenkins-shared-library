#!/usr/bin/env groovy
package com.example

class Docker implements Serializable {
    def script
    Docker(script){
        this.script = script
    }

    buildDockerImage(String imageName){
        script.echo "building image"
        script.withCredentials([script.usernamePassword(credentialsId: 'github-credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]){
            script.sh "docker build -t $imageName ."
            script.sh "echo $script.PASS | docker login -u $script.USER --password-stdin"
            script.sh "docker push $imageName"
        }
    }
}