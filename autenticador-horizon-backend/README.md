# Horizon(java)
//link front desktop
https://github.com/SpinyOwl/legui

toastr _> noticação


Front end Tela login

https://morioh.com/p/7da7955083ea

autenticação

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 token

https://www.youtube.com/watch?v=QG9AZNOCj5I

https://www.youtube.com/watch?v=TNt3GHuayXs

## Atenticado Horizon

### Como iniciar :

execute o comando docker build -t oracle .

execute o comando : docker run -p 1521:1521 --name oracle_db oracle 

excute a aplicação: 
local : mvn clean spring-boot:run

em dev :
mvn clean spring-boot:run -Dspring-boot.run.profiles=dev -Dspring-boot.run.arguments=--jasypt.encryptor.password=h0r1z0n1@3


em hmg :
mvn clean spring-boot:run -Dspring-boot.run.profiles=hmg -Dspring-boot.run.arguments=--jasypt.encryptor.password=h0r1z0n1@3

em prod :
mvn clean spring-boot:run -Dspring-boot.run.profiles=prod -Dspring-boot.run.arguments=--jasypt.encryptor.password=h0r1z0n1@3


