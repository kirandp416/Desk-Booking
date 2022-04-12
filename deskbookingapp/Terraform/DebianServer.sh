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
cat << `EOF` >> gitlab_deskbook_keypair.key
-----BEGIN OPENSSH PRIVATE KEY-----
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
-----END OPENSSH PRIVATE KEY-----
`EOF`
chmod 400 gitlab_deskbook_keypair.key
ssh-agent bash -c 'ssh-add gitlab_deskbook_keypair.key'


echo "installing MariaDB..."
# sudo yum install mysql -y
sudo apt install mariadb-server -y
sudo systemctl start mariadb
sudo systemctl status mariadb
sudo systemctl enable mariadb

ssh-agent bash -c 'ssh-add gitlab_deskbook_keypair.key;git clone git@git.cardiff.ac.uk:c21091401/team-2-office-desk-booking.git'

cd team-2-office-desk-booking/
mysql -u root < deskbookingapp/database.sql
sudo mysql -e "USE mysql; UPDATE user SET password=PASSWORD('comsc') WHERE User='root' AND Host = 'localhost'; UPDATE mysql.user SET plugin = 'mysql_native_password' WHERE user = 'root' AND plugin = 'unix_socket'; FLUSH PRIVILEGES;"
sudo systemctl restart  mariadb
sudo systemctl enable mariadb
echo "Installing gradle..."
curl -O https://downloads.gradle-dn.com/distributions/gradle-7.2-bin.zip
sudo mkdir /opt/gradle
sudo unzip -d /opt/gradle gradle-7.2-bin.zip
export PATH=$PATH:/opt/gradle/gradle-7.2/bin
gradle -v

gradle build
gradle bootrun
