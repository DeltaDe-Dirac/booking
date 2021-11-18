# CardLatch Interview

### Project description

This project is an important part of a big hotel management system.<br />
It implements some basic features available for staff with different roles,<br />
Like displayig the status of the hotel, including rooms and guests state.

### Instructions

You need to create a spring boot microservice that exposes several requested APIs.<br />
You will need to use spring data, spring security and twillo in this project.<br />
**Note: Please make sure to cover your code with tests.**

The database you're going to use is mongo, The connection SRV is:

> mongodb+srv://username:password@cluster0.ofaxg.mongodb.net/interview?retryWrites=true&w=majority

### Collections

- room:
  - \_id
  - number
  - capacity
- guest
  - \_id
  - name
  - room
- entry
  - \_id
  - room
  - time

### APIs

<details>
  <summary markdown="span">GET /rooms/available</summary>

return all available rooms ( rooms which are not occupied by guests )

> reception, manager, owner allowed

</details>

<details>
  <summary markdown="span">GET /rooms/couples</summary>

return all rooms occupied by exactly 2 guests, so the manager can send bottles of wine as a gift

> manager, owner allowed

</details>

<details>
  <summary markdown="span">GET /guests/families</summary>

return all guests who occupied a room with capacity greater than 2, grouped by rooms

> no restrictions

</details>

<details>
  <summary markdown="span">POST /status</summary>

sends SMS to the owner of the hotel reporting current percentage of occupied rooms (use twillo)

> only owner allowed

</details>

### Security

As you can see in the APIs section, you need three types of users:

- reception
- manager
- owner

### Local Installation, Spring Boot Starter and Test Execution

Spring Boot Starter command<br />

### `mvn spring-boot:run -Dspring-boot.run.arguments=--jasypt.encryptor.password=<use your secret phrase here>`

Install and Skip Tests command<br />

### `mvn clean install -U -T 10 -Djasypt.encryptor.password=<use your secret phrase here> -Dmaven.test.skip=true`

Execute All Tests command<br />

### `mvn clean test -Djasypt.encryptor.password=<use your secret phrase here>`

**Note: You can use online encrypter [here](https://www.devglan.com/online-tools/jasypt-online-encryption-decryption): with your secret phrase for encrypted.properties**
