// Simple JTE template: delegates to `simple` library
pipeline {
  agent any
  stages {
    stage('Run Simple Build') {
      steps {
        script {
          simple.build()
        }
      }
    }
  }
}
