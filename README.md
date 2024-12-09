# spool-emails

Demo project for Spring Boot Queue Email with Rabbitmq

## Requirements

1. Java - 11.x + 
2. Maven - 3.x +
3. RabbitMQ - 3.x +
4. Docker - 20.x +
5. IDE - IntelliJ IDEA or Eclipse
6. Lombok Plugin
7. Postman
8. SMTP Server

## RabbitMQ Setup

You can install RabbitMQ in windows by following this tutorial: [Install RabbitMQ in windows](

## SMTP Server Setup

You can use [MailZoho]('https://www.zoho.com/es-xl/mail/') to setup SMTP Server.

## Steps to Setup

**1. Clone the application**

```bash
git clone
```

**2. Build and run the app using maven**

```bash
mvn clean spring-boot:run
```

The app will start running at <http://localhost:8080>.

## Explore Rest APIs

The app defines following APIs.

```bash
curl -X 'POST' \
  'http://localhost:8086/api/v1/emails' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "to": "email@mail.com",
  "subject": "Esto es lo que es",
  "body": "Esto es un envio desde ZohoMail 1"
}'
```

## Learn more

You can find the tutorial for this application.

## Author

* **Raul Bolivar Navas** - [Raul Bolivar Navas]('https://github.com/raulrobinson/spool-emails')

## License

Released under the MIT License. See the [LICENSE]('https://rasysbox.com/license.txt') file.