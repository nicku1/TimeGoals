FROM golang:1.23 AS build
WORKDIR /usr/src/app
COPY go.mod go.sum ./
RUN go mod download && go mod verify
COPY . .
RUN go build -v -o /tmp/app ./...

FROM alpine:latest
RUN apk add --no-cache libc6-compat
COPY --from=build /tmp/app /usr/local/bin/app
EXPOSE 8080
ENTRYPOINT ["app"]