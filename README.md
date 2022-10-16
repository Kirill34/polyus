# polyus

## Наше веб-приложение развернуто на сервере

 Ссылка: http://vds104.server-1.biz:8090/

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

## Наполнение данными

В развернутом приложении будет пустая БД

Приведем примеры запросов для ее наполнения

Добавление типов техники:
![image](https://user-images.githubusercontent.com/46486489/196017515-850f5c33-720f-4763-80d9-b260b6615d15.png)


Добавление моделей техники:
![image](https://user-images.githubusercontent.com/46486489/196017527-7e1329ef-103f-4a98-a8d9-50cc86970e44.png)

Добавление диспетчера (менеджера):
![image](https://user-images.githubusercontent.com/46486489/196017541-976d1361-8c18-48ae-accd-2193424b5c9d.png)

Добавление экземпляра техники:
![image](https://user-images.githubusercontent.com/46486489/196017564-01983d24-16b4-49b7-aa7b-cfbc5fc91fb4.png)

Добавление заказчика:
![image](https://user-images.githubusercontent.com/46486489/196017578-e9a866d6-5b85-4ee2-a51e-461b49359509.png)

Добавление водителя:
![image](https://user-images.githubusercontent.com/46486489/196017586-9085538e-471a-479d-84e2-1413b5760611.png)

