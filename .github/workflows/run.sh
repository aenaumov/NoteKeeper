#nohup mvn spring-boot:run -Dspring-boot.run.profiles=ci >> console.log 2>&1 &
nohup mvn spring-boot:run &>> console.log 2>&1 &