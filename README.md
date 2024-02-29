# **Back-end for NoteKeeper application**

## **Used stack**
- Java 17
- API gateway pattern
- OAuth 2.0
- JWT
- SpringBoot
- Reactor
- Spring Flux
- SpringData
- SpringSecurity
- GitHubAction
- Postman OAuth 2.0
- Shell Script

Present realization of project is simplified. Needed checks for reality project aren't realized in it. 
Docker also isn't used. 
Postman tests are realized in quantity that enough for testing receiving JWT and using it for further requests.

The function of application is keeping of user's notes. 
It's supposed that user uses client application to work with server application. 
Server application has microservice structure:
- auth_client is responsible for client applications.
- auth_user is responsible for users
- token is responsible for tokens
- resource is responsible for user's notes
- router is entry point for incoming requests

It's supposed that both user and client application are already registered.
Example of request client application should send to receive JWT:
Credentials of client application are sent in Basic authorization header.
Credentials of user are sent in body request.

```
POST http://localhost:8080/auth/token
Request Headers
Content-Type: application/x-www-form-urlencoded
Authorization: Basic dGVzdGFwcDpwYXNzd29yZA==
Request Body
grant_type: "password"
username: "user"
password: "user"
```

Example of JWT:
```
{"type":"Bearer",
"access_token":"eyJraWQiOiIxMjMiLCJhbGci",
"refresh_token":"eyJraWQiOiIxMjMiLCJhbGc"}
```
Received JWT contains both access and refresh tokens.

Example of request for receiving of all notes of user with id 2.
Valid access token is sent in Bearer authorization header.

```
GET http://localhost:8080/notes/user/2
Request Headers
Authorization: Bearer access_token
```

For refreshing of JWT should send request with valid refresh token. 
Credentials of client application are sent in Basic authorization header.
Refresh token is sent in body request.

```
POST http://localhost:8080/auth/refresh
Request Headers
Content-Type: application/x-www-form-urlencoded
Authorization: Basic dGVzdGFwcDpwYXNzd29yZA==
Request Body
refresh_token: "eyJraWQiOiIxMjMiLCJhbGciOiJSUzI1Ni"
grant_type: "refresh_token"
```
