package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/lib/pq"
)

var db *sql.DB

const (
	host     = "auth-service-auth-database-1"
	port     = 5432
	user     = "postgres"
	password = "Jaranie420"
	dbname   = "postgres"
)

func DbInit() {
	conn := fmt.Sprintf("host=%s port=%d user=%s password=%s dbname=%s sslmode=disable", host, port, user, password, dbname)
	init, err := sql.Open("postgres", conn)
	if err != nil {
		panic(err)
	}
	log.Printf("Database connection opened.")
	db = init
}

func DbClose() {
	err := db.Close()
	if err != nil {
		panic(err)
	}
}
