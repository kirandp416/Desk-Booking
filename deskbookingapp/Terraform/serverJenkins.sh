#!/bin/bash
cd /home/debian
echo in directory $PWD
sudo apt update
sudo apt install unzip -y
sudo apt install git -y
sudo apt install -y curl
sudo apt install -y wget
echo "installing gitlab server key..."
sudo touch ~/.ssh/known_hosts
sudo ssh-keyscan git.cardiff.ac.uk >> ~/.ssh/known_hosts
sudo chmod 644 ~/.ssh/known_hosts
echo "Installing Java 11..."
sudo apt install -y openjdk-11-jdk
java -version
echo "install Jenkins"
curl -fsSL https://pkg.jenkins.io/debian-stable/jenkins.io.key | sudo tee \
  /usr/share/keyrings/jenkins-keyring.asc > /dev/null
echo deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc] \
  https://pkg.jenkins.io/debian-stable binary/ | sudo tee \
  /etc/apt/sources.list.d/jenkins.list > /dev/null
sudo apt-get update
sudo apt-get install jenkins
# If you want jenkins on port 8081 so you can run your app on 8080 then change the default jenkins port.
#(look up linux sed - it is really cool)
# sudo sed -i 's/JENKINS_PORT="8080"/JENKINS_PORT="8081"/g' /etc/sysconfig/jenkins
sudo systemctl start jenkins
systemctl status jenkins
sudo systemctl enable jenkins
echo "installing MariaDB..."
# sudo yum install mysql -y
sudo apt install mariadb-server -y
sudo systemctl start mariadb
sudo systemctl status mariadb
sudo systemctl enable mariadb
sudo mysql -e "USE mysql; UPDATE user SET password=PASSWORD('comsc') WHERE User='root' AND Host = 'localhost'; UPDATE mysql.user SET plugin = 'mysql_native_password' WHERE user = 'root' AND plugin = 'unix_socket'; FLUSH PRIVILEGES;"
sudo systemctl restart  mariadb
sudo systemctl enable mariadb
echo "Installing gradle..."
curl -O https://downloads.gradle-dn.com/distributions/gradle-7.2-bin.zip
sudo mkdir /opt/gradle
sudo unzip -d /opt/gradle gradle-7.2-bin.zip
export PATH=$PATH:/opt/gradle/gradle-7.2/bin
gradle -v
echo "Installing terraform..."
cd /home/debian
wget https://releases.hashicorp.com/terraform/1.1.5/terraform_1.1.5_linux_amd64.zip
unzip terraform_1.1.5_linux_amd64.zip
sudo mv terraform /usr/local/bin/
