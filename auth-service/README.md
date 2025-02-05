# Auth Service
Simple service to manage user accounts, authentication and session handling.
This is learning project and is not meant to be used in production. In future
it might implement proper authentication and session handling according to
OAuth2 (RFC 6749).

## Build steps
docker build -t auth-service .

## Run
docker run -d -p 8084:8080 auth-service