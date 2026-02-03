def call(Map params = [:]) {
    // Orchestrator: run small steps in sequence so template is modular
    checkoutStep()
    compileStep()
    unitTestStep()
    packageStep()
    sonarStep()
    publishStep()
    notifyStep()
}

def checkoutStep() {
    stage('Checkout') {
        echo 'Checking out SCM'
        checkout scm
    }
}

def compileStep() {
    stage('Compile') {
        echo 'Compiling with Maven'
        sh 'mvn -B -DskipTests compile'
    }
}

def unitTestStep() {
    stage('Unit Tests') {
        echo 'Running unit tests'
        sh 'mvn -B test'
        junit '**/target/surefire-reports/*.xml'
    }
}

def packageStep() {
    stage('Package') {
        echo 'Packaging artifact'
        sh 'mvn -B -DskipTests package'
    }
}

def sonarStep() {
    stage('Sonar') {
        if (env.SONAR_SERVER) {
            withSonarQubeEnv(env.SONAR_SERVER) {
                sh "mvn sonar:sonar -Dsonar.projectKey=${project?.artifact ?: 'app'} -Dsonar.projectName=${project?.artifact ?: 'app'}"
            }
        } else {
            echo 'Sonar not configured; skipping'
        }
    }
}

def publishStep() {
    stage('Publish') {
        echo 'Publishing artifact (simulated)'
        // implement actual publish (Nexus, Docker push, etc.) in real usage
    }
}

def notifyStep() {
    stage('Notify') {
        echo 'Notifying stakeholders (simulated)'
    }
}

return this
