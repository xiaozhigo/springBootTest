pipeline {
    agent any

environment {
	def projects =  "${params.project}"
 }
    stages {
        stage('param') {
             steps {
              echo "${params.branch}"
              echo "${branch}"
              echo "${params.project}"
           }
        }

        stage('克隆代码') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'github', url: 'https://github.com/xiaozhigo/springBootTest.git']]])
            }
        }
        stage('${params.project}-打包') {
            steps {
                sh 'cd /root/.jenkins/workspace/springboot-pipeline;mvn clean install -Dmaven.test.skip=true'
            }
        }

        stage('${params.project}-scp') {
            steps {
                sh '''
                cd /usr/local/jenkins
                IP=192.168.176.130
                scp /root/.jenkins/workspace/springboot-pipeline/target/${params.project}-0.0.1-SNAPSHOT.jar root@${IP}:/home/springboot-pipeline/${params.project}-deploy.jar
                ssh root@${IP} << EOF
                echo "start deploy";
                cd /home/springboot-pipeline/
                ##备份
                ##打包（上个版本的包备份）
                rm -rf ${params.project}.jar.bak
                mv ${params.project}.jar ${params.project}.jar.bak;
                echo "rm ${params.project}.jar";
                mv ${params.project}-deploy.jar ${params.project}.jar
                sh /home/springboot-pipeline/restart.sh restart;
                EOF
                echo "deploy succ"
                '''
            }
        }
    }
}