# polyus

## Инструкция по запуску и установке

1) Установить на сервер Java 11, Maven и  MySQL 8
2)  Запустить сервер MySQL 8 и создать базу данных с названием polyus
3) Склонировать этот репозиторий
```rb
git clone https://github.com/Kirill34/polyus
```
4) перейти в папку /polyus и скомпилировать проект
```rb
cd polyus
```

При необходимости изменить логин/пароль от базы данных в файле /src/main/resources/application.properties

![image](https://user-images.githubusercontent.com/46486489/196016526-dc445156-3990-4a5f-9acc-6598d0137ea7.png)

Скомпилировать проект


```rb
mvn install
```

5) Запустить исполняемый файл

```rb
cd target
```

```rb
java -jar polyus-0.0.1-SNAPSHOT.jar
```

Т.е. запустить  jar  файл из директории /target

6) Открыть в браузере 
```rb
http://localhost:8090
```

