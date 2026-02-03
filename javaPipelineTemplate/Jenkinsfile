// Simple JTE template: delegates to `javaProject` library
pipeline {
  agent any
  stages {
    stage('Run Java Project Pipeline') {
      steps {
        script {
          javaProject.build()
        }
      }
    }
  }
}
