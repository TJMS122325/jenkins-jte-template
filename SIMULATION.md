Simulation: Simple JTE Template

Steps to run locally / in Jenkins:

1. Initialize git for both repos (central template and demo):

```bash
cd c:/Users/Joshua/Desktop/WORK/LOCAL/jenkins-jte-simple
git init
git add .
git commit -m "simple jte template"

cd ../demo-simple-app
git init
git add .
git commit -m "demo simple app"
```

2. Create a Jenkins pipeline job pointing to `demo-simple-app` as SCM, and set the Flow Definition to JTE Template Flow Definition with template repo `jenkins-jte-simple` (or rely on `library_sources` in `pipeline_config.groovy`).

3. Run the job — logs should show template and library load and run the simple `mvn package` step.
