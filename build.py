import os
import sys
aws_uri = "need to know"
default_tag = "latest"
def script(version=default_tag):
    cwd = os.getcwd()
    print(cwd)
    os.system("sudo mvn package dockerfile:build -DskipTests")
    default_image=aws_uri+":"+default_tag
    if version != default_tag:
        os.system("sudo docker tag "+default_image + " "+aws_uri+":"+version)
        default_image = aws_uri+":"+version
    os.system("sudo docker push " + default_image)
script(sys.argv[1])
