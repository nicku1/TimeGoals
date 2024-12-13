package main

import (
	"database/sql"
	"fmt"
	_ "github.com/lib/pq"
	"log"
	"strconv"
)

var db *sql.DB

const (
	host     = "localhost"
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
	rows, err := db.Query(`SELECT "column" FROM "test"`)

	var test int
	rows.Next()
	err = rows.Scan(&test)
	if err != nil {
		panic(err)
	}
	log.Printf(strconv.Itoa(test))
}

func DbClose() {
	err := db.Close()
	if err != nil {
		panic(err)
	}
}
