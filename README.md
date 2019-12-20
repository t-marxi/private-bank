# private-bank
Simple implementation of a private bank.

For test application need to run application on http://localhost:8080 after that run test Scenario test.

Available REST endpoints:
POST    http://localhost:8080/client + client json - create a new client
GET     http://localhost:8080/client - get all clients overview
GET     http://localhost:8080/client/{id} - get deep description of one client
DELETE  http://localhost:8080/client/{id} - delete client

POST    http://localhost:8080/account + account json - create a new account for a client
GET     http://localhost:8080/account - get all accounts overview
GET     http://localhost:8080/account/{id} - get deep description of one account
DELETE  http://localhost:8080/account/{id} - delete account

POST    http://localhost:8080/currency + currency json - create a new currency
GET     http://localhost:8080/currency - get all currencies overview

POST    http://localhost:8080/fxrate + fxrate json - create a new fxrate
GET     http://localhost:8080/fxrate/{from}/{to} - get all fxrate from one currency to another

POST    http://localhost:8080/transaction + transaction json - create a new transaction
GET     http://localhost:8080/transaction/{from}/{to} - get all transaction from one account to another