#### PUC Minas - Arquitetura de Sistemas Distribuídos

### TCC - POC da api do módulo Gestão de Normas

- Gestão de Normas: este módulo visa manter as normas técnicas existentes nas áreas industrial,
  ambiental e outras aplicáveis. Devem fornecer toda a base para compliance, incluindo o planejamento de
  ações que envolvam o uso correto e eficiente e recursos, descarte de sub-produtos do processo industrial
  no meio-ambiente, monitoramento de riscos de acidentes e suas consequências, etc. O repositório de
  normas estará disponível em tecnologias de nuvem como um serviço ou função (SaaS/FaaS), sendo
  persistido em tecnologia de SGBD relacional, acessível por meio de microsserviços. Adicionalmente
  deverá ser previsto um web service que fará acesso às bases de dados externas de normas, com vistas a
  identificar e notificar sobre possíveis mudanças e evoluções.

#### Stack utilizada

- Java 11
- Spring Boot
- MapStruct
- Lombok
- Junit 5 + Mockito
- Postgres
- Docker

### Executando o projeto localmente

###Pré requisitos
- Gradle instalado
  https://gradle.org/install/

- Docker instalado
  https://docs.docker.com/engine/install/ubuntu/

- Docker compose instalado
  https://docs.docker.com/compose/install/

#####Rodando o banco de dados
`docker-compose up -d`

#####Gerando o build do projeto
`./gradlew build`

#####Gerando a imagem do docker
`docker build  -f Dockerfile-local -t contracts-api .`

#####Rodando a aplicação
`docker run -d -p 8081:8081 -t contracts-api`

###Acessando a aplicação

###Swagger

#####Local
http://localhost:8081/swagger-ui.html

#####Produção
https://tcc-contracts-api.herokuapp.com//swagger-ui.html
