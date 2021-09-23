FROM reg.tre-pa.jus.br/library/openjdk:8-jre-slim

ENV TZ America/Belem

RUN echo 'Acquire::http { Proxy "http://apt-proxy.tre-pa.jus.br:3142"; };' > /etc/apt/apt.conf.d/01proxy \
    && echo 'Acquire::https { Proxy "http://apt-proxy.tre-pa.jus.br:3142"; };' >> /etc/apt/apt.conf.d/01proxy

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN apt-get update && apt-get install -y locales && rm -rf /var/lib/apt/lists/* \
    && localedef -i pt_BR -c -f UTF-8 -A /usr/share/locale/locale.alias pt_BR.UTF-8

ENV LANG=pt_BR.UTF-8
ENV LANGUAGE=pt_BR:pt:en

COPY ./docker/tre-pa.jus.br.crt /usr/local/share/ca-certificates/tre-pa.jus.br.crt

RUN update-ca-certificates

RUN keytool -import -trustcacerts \
   -storepass changeit -noprompt \
   -file /usr/local/share/ca-certificates/tre-pa.jus.br.crt \
   -alias ca_alias \
   -keystore /usr/local/openjdk-8/lib/security/cacerts

RUN addgroup spring && useradd spring -g spring

USER spring:spring

COPY ./springboot-angular-dxdatagrid-lab-backend/target/dependency/BOOT-INF/lib /app/lib
COPY ./springboot-angular-dxdatagrid-lab-backend/target/dependency/META-INF /app/META-INF
COPY ./springboot-angular-dxdatagrid-lab-backend/target/dependency/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","br.jus.tre_pa.springboot-angular-dxdatagrid-lab.SpringBootApp"]
