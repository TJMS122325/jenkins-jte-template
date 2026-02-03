# jenkins-jte-template

This is a minimal JTE template repo intended to teach and simulate JTE usage.

Structure:
- `Jenkinsfile` — template that invokes `simple.build()`
- `libraries/simple/build.groovy` — library step that orchestrates multiple small steps

The `simple` library now contains modular steps so you can see a more-realistic pipeline flow:
- `Checkout` — performs `checkout scm`
- `Compile` — `mvn compile`
- `Unit Tests` — `mvn test` and publishes `junit` results
- `Package` — `mvn package`
- `Sonar` — runs `mvn sonar:sonar` if `SONAR_SERVER` is configured
- `Publish` — placeholder for publishing artifacts (Nexus/Docker)
- `Notify` — placeholder for notifications

Simulation with a demo service:

1. Create demo repo (local folder `demo-simple-app`) with the following `pipeline_config.groovy`:

```groovy
library_sources {
  github {
    org = "TJMS122325"
    repo = "jenkins-jte-simple"
    branch = "main"
  }
}

libraries {
  simple
}

project {
  name = 'demo-simple-app'
  artifact = 'demo-simple-app'
}
```

2. Point your Jenkins job SCM to the demo repo and use JTE Template Provider to load the template from `jenkins-jte-simple` (or rely on `library_sources` in the demo repo).

3. Run the job — the template will call `simple.build()` from the central repo which executes the full step sequence.