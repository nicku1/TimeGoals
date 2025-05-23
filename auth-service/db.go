package main

import (
	"database/sql"
	"errors"
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

func DbGetAccount(id int) (account, error) {
	acc := account{}

	var updated sql.NullString
	var suspend_reason sql.NullString

	row := db.QueryRow("SELECT * FROM users WHERE id = $1", id)
	err := row.Scan(&acc.ID, &acc.Username, &acc.Password, &acc.Email, &acc.Created, &updated, &acc.Suspend.Active, &suspend_reason)

	if updated.Valid {
		acc.Updated = updated.String
	}

	if suspend_reason.Valid {
		acc.Suspend.Reason = suspend_reason.String
	}

	return acc, err
}

func DbGetAccountByLogin(login string) (account, error) {
	acc := account{}

	var updated sql.NullString
	var suspend_reason sql.NullString

	row := db.QueryRow("SELECT * FROM users WHERE username = $1", login)
	err := row.Scan(&acc.ID, &acc.Username, &acc.Password, &acc.Email, &acc.Created, &updated, &acc.Suspend.Active, &suspend_reason)

	if updated.Valid {
		acc.Updated = updated.String
	}

	if suspend_reason.Valid {
		acc.Suspend.Reason = suspend_reason.String
	}

	return acc, err
}

func DbUpdateAccount(acc account) error {
	_, err := db.Exec(
		"UPDATE users SET username = $2, password = $3, email = $4, created = $5, updated = $6, suspend_active = $7, suspend_reason = $8 WHERE id = $1",
		acc.ID,
		acc.Username,
		acc.Password,
		acc.Email,
		acc.Created,
		acc.Updated,
		acc.Suspend.Active,
		acc.Suspend.Reason,
	)

	return err
}

func DbDeleteAccount(id int) error {
	result, err := db.Exec("DELETE FROM users WHERE id = $1", id)
	if err != nil {
		num_affected, err := result.RowsAffected()
		if err != nil {
			return err
		}

		if num_affected == 0 {
			log.Print("Executed delete user but no rows affected, this should never happen because validation check for user existance")
			return errors.New("delete without rows affected")
		}

		return nil
	}

	return err
}
