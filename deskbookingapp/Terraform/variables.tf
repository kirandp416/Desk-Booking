variable "flavor" { default = "m1.large" }
variable "image" { default = "Debian Buster 10.11.1 20211029" }
#variable "instance" { default = "tf_instance" }

variable "name" { default = "JenkinsServer" }

variable "network" { default = "default" }   # you need to change this

variable "keypair" { default = "21091401_keypair" } # you need to change this
variable "pool" { default = "cscloud_private_floating" }
variable "server_script" { default = "./serverJenkins.sh" }
variable "security_description" { default = "Terraform security group" }
variable "security_name" { default = "tf_security" }
