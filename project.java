provider "aws" {
  region     = "ap-southeast-1"
}
resource "aws_security_group" "web_access" {
        name = "web_access"
        description = "allow ssh and http"

        ingress {
                from_port = 80
                to_port = 80
                protocol = "tcp"
                cidr_blocks = ["0.0.0.0/0"]
        }

        ingress {
                from_port = 22
                to_port = 22
                protocol = "tcp"
                cidr_blocks = ["0.0.0.0/0"]
        }

        egress {
                from_port = 0
                to_port = 0
                protocol = "-1"
                cidr_blocks = ["0.0.0.0/0"]
        }


}
resource "aws_instance" "example" {
  ami           = "ami-0e97ea97a2f374e3d"
  instance_type = "t2.micro"
  key_name      = "terra"
  security_groups = ["${aws_security_group.web_access.name}"]

  tags = {
    Name = "tf-example"
  }
}

