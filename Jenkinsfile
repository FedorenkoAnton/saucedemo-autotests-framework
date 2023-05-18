pipeline {
    agent any

    tools {
        gradle "8.0.2"
    }

    parameters {
        booleanParam(defaultValue: true, description: 'run rest tests', name: 'rest')
        booleanParam(defaultValue: false, description: 'run web ui tests', name: 'web_ui')
    }

    stages {
        stage('Rest tests') {
            when {
                expression { return params.rest}
            }

        steps {
                sh "gradle clean RunRestApiTests"
            }
        }

        stage('Web UI tests') {
            when {
                expression { return params.web_ui}
            }

            steps {
                sh "gradle clean RubWebUITests"
            }
        }
    }

    post {
        always {
            allure([
                reportBuildPolicy: 'ALWAYS',
                results: [[path: 'build/allure-results']]
            ])
        }
    }
}