package main

import (
	"database/sql"
	"log"
	"net/http"
	"strconv"
	"time"

	"github.com/gin-contrib/cors"
	"github.com/gin-gonic/gin"
	"golang.org/x/crypto/bcrypt"
)

func getAccount(c *gin.Context) {
	id, err := strconv.Atoi(c.Param("id"))
	if err != nil {
		c.IndentedJSON(http.StatusBadRequest, gin.H{})
		return
	}

	acc, err := DbGetAccount(id)

	if err == sql.ErrNoRows {
		log.Print(err.Error())
		c.IndentedJSON(http.StatusNotFound, gin.H{})
		return
	}

	if err != nil {
		log.Print(err.Error())
		c.IndentedJSON(http.StatusInternalServerError, gin.H{})
		return
	}

	response := getAccountSuccess{
		acc,
	}
	c.IndentedJSON(http.StatusOK, response)
}

func postAccount(c *gin.Context) {
	var request accountPost

	if err := c.BindJSON(&request); err != nil {
		log.Printf("Invalid request received in postAccount")
		c.IndentedJSON(http.StatusBadRequest, gin.H{})
		return
	}

	pass, _ := bcrypt.GenerateFromPassword([]byte(request.Password), bcrypt.DefaultCost)
	var account = account{
		Username: request.Username,
		Password: string(pass),
		Email:    request.Email,
		Created:  time.Now().Format("2006-01-02 15:04:05"),
		Updated:  time.Now().Format("2006-01-02 15:04:05"),
		Suspend: suspend{
			Active: false,
			Reason: "",
		},
	}

	rows, err := db.Query(
		"INSERT INTO users (username, password, email, created, updated, suspend_active) VALUES ($1, $2, $3, $4, $5, $6) RETURNING id",
		account.Username,
		account.Password,
		account.Email,
		account.Created,
		account.Updated,
		0,
	)

	if err != nil {
		log.Printf("An error occured while creating new user: %s", err.Error())
		c.IndentedJSON(http.StatusInternalServerError, gin.H{})
		return
	}

	var id int
	rows.Next()
	err = rows.Scan(&id)
	log.Printf("%d", id)

	if err != nil {
		log.Printf("Error while scanning for ID for created user: %s", err.Error())
		c.IndentedJSON(http.StatusCreated, gin.H{})
		return
	}

	var response = postAccountSuccess{
		ID:       id,
		Username: account.Username,
		Created:  account.Created,
	}

	c.IndentedJSON(http.StatusCreated, response)
}

func putAccount(c *gin.Context) {
	var request accountPut

	id, err := strconv.Atoi(c.Param("id"))

	if err != nil {
		c.IndentedJSON(http.StatusBadRequest, gin.H{})
		return
	}

	if err := c.BindJSON(&request); err != nil {
		c.IndentedJSON(http.StatusBadRequest, gin.H{})
		return
	}

	acc, err := DbGetAccount(id)

	if err == sql.ErrNoRows {
		// todo: new user logic
		c.IndentedJSON(http.StatusNotImplemented, gin.H{})
		return
	}

	var account = account{
		ID:       acc.ID,
		Username: request.Username,
		Password: request.Password,
		Email:    request.Email,
		Created:  acc.Created,
		Updated:  time.Now().Format("2006-01-02 15:04:05"),
		Suspend:  request.Suspend,
	}

	err = DbUpdateAccount(account)

	if err != nil {
		c.IndentedJSON(http.StatusInternalServerError, gin.H{})
		return
	}

	c.IndentedJSON(http.StatusOK, account)
}

func deleteAccount(c *gin.Context) {
	id, err := strconv.Atoi(c.Param("id"))

	if err != nil {
		c.IndentedJSON(http.StatusBadRequest, gin.H{})
		return
	}

	acc, err := DbGetAccount(id)

	if id != acc.ID {
		var response = errorResponse{
			Error:   true,
			Message: "There is no account with that ID: " + c.Param("id"),
		}
		c.IndentedJSON(http.StatusBadRequest, response)
		return
	}

	err = DbDeleteAccount(id)

	if err != nil {
		c.IndentedJSON(http.StatusInternalServerError, gin.H{})
		return
	}

	c.IndentedJSON(http.StatusOK, gin.H{})
}

func getSession(c *gin.Context) {
	var session = session{
		SessionID: "1a2b3c",
		AccountID: 1,
		IP:        "127.0.0.1",
		ValidTo:   "2025-12-12 00:00:00",
	}

	c.IndentedJSON(http.StatusOK, session)
}

func loginToAccount(c *gin.Context) {
	var request accountLogin

	if err := c.BindJSON(&request); err != nil {
		c.IndentedJSON(http.StatusBadRequest, gin.H{})
		return
	}

	acc, err := DbGetAccountByLogin(request.Username)

	if err == sql.ErrNoRows {
		var response = errorResponse{
			Error:   true,
			Message: "There is no account with that ID: " + c.Param("id"),
		}
		c.IndentedJSON(http.StatusBadRequest, response)
		return
	}

	if err != nil {
		c.IndentedJSON(http.StatusInternalServerError, gin.H{})
		return
	}

	if request.Username != acc.Username || bcrypt.CompareHashAndPassword([]byte(acc.Password), []byte(request.Password)) != nil {
		var response = errorResponse{
			Error:   true,
			Message: "Invalid credentials",
		}
		c.IndentedJSON(http.StatusUnauthorized, response)
		return
	}

	// todo: create new session
	var session = session{
		SessionID: "1a2b3c",
		AccountID: acc.ID,
		IP:        c.ClientIP(),
		ValidTo:   "2025-12-12 00:00:00",
	}

	c.IndentedJSON(http.StatusOK, session)
}

func main() {
	router := gin.Default()

	router.Use(cors.New(cors.Config{
		AllowAllOrigins: true,
		AllowMethods:    []string{"GET", "POST", "PUT", "PATCH", "DELETE"},
		AllowHeaders:    []string{"Origin", "Content-Length", "Content-Type", "Authorization", "access-control-allow-origin"},
	}))

	// Account
	router.GET("/account/:id", getAccount)
	router.POST("/account", postAccount)
	router.PUT("/account/:id", putAccount)
	router.DELETE("/account/:id", deleteAccount)
	//
	// Auth
	router.POST("/auth/login", loginToAccount)

	// Session
	router.GET("/session", getSession)

	DbInit()
	err := router.Run("0.0.0.0:8080")
	if err != nil {
		DbClose()
		panic(err)
	}
	DbClose()
}
