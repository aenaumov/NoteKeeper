cd auth
nohup mvn spring-boot:run &

cd ../resource
nohup mvn spring-boot:run &

cd ../router
nohup mvn spring-boot:run &
#mvn exec:java -Dexec.mainClass="ru.education.myproject1.MyProject1Application"

#mvn spring-boot:run -Dspring-boot.run.mainClass=resource.ru.education.myproject1.MyProject1Application