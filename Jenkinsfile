pipeline {
    agent any

    stages {
        stage('克隆代码') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'github', url: 'https://github.com/xiaozhigo/springBootTest.git']]])
            }
        }
        stage('springboot-打包') {
            steps {
                sh 'cd /root/.jenkins/workspace/springboot-pipeline;mvn clean install -Dmaven.test.skip=true'
            }
        }

        stage('springboot-scp') {
            steps {
                sh '''
                cd /usr/local/jenkins
                IP=192.168.176.130
                scp /root/.jenkins/workspace/springboot-pipeline/target/springboottest-0.0.1-SNAPSHOT.jar root@${IP}:/home/springboot-pipeline/springboottest-deploy.jar
                ssh root@${IP} << EOF
                echo "start deploy";
                cd /home/springboot-pipeline/
                ##备份
                ##打包（上个版本的包备份）
                rm -rf springboottest.jar.bak
                mv springboottest.jar springboottest.jar.bak;
                echo "rm springboottest.jar";
                mv springboottest-deploy.jar springboottest.jar
                sh /home/springboot-pipeline/restart.sh restart;
                EOF
                echo "deploy succ"
                '''
            }
        }
    }
}