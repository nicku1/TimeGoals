package main

import (
	"github.com/gin-contrib/cors"
	"github.com/gin-gonic/gin"
	"golang.org/x/crypto/bcrypt"
	"net/http"
	"strconv"
	"time"
)

var pass, _ = bcrypt.GenerateFromPassword([]byte("Jaranie420"), bcrypt.DefaultCost)
var acc = account{
	ID:       1,
	Username: "john",
	Password: string(pass),
	Email:    "john@gmail.com",
	Created:  "2024-12-12 23:43:59",
	Updated:  "2024-12-13 13:54:12",
	Suspend: suspend{
		Active: false,
		Reason: "",
	},
}

func getAccount(c *gin.Context) {
	id, err := strconv.Atoi(c.Param("id"))
	if err != nil {
		c.IndentedJSON(http.StatusBadRequest, gin.H{})
		return
	}

	pass, _ := bcrypt.GenerateFromPassword([]byte("Jaranie420"), bcrypt.DefaultCost)

	var acc = getAccountSuccess{
		Account: account{
			ID:       id,
			Username: "john",
			Password: string(pass),
			Email:    "john@gmail.com",
			Created:  "2024-12-12 23:43:59",
			Updated:  "2024-12-13 13:54:12",
			Suspend: suspend{
				Active: false,
				Reason: "",
			},
		},
	}

	c.IndentedJSON(http.StatusOK, acc)
}

func postAccount(c *gin.Context) {
	var request accountPost

	if err := c.BindJSON(&request); err != nil {
		c.IndentedJSON(http.StatusBadRequest, gin.H{})
		return
	}

	pass, _ := bcrypt.GenerateFromPassword([]byte(request.Password), bcrypt.DefaultCost)
	var account = account{
		ID:       1, //todo: next id
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

	var response = postAccountSuccess{
		ID:       account.ID,
		Username: account.Username,
		Created:  account.Created,
	}

	//todo: insert to database

	//todo: do not return whole account, just response
	c.IndentedJSON(http.StatusOK, response)
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

	if id != acc.ID {
		var response = errorResponse{
			Error:   true,
			Message: "There is no account with that ID: " + c.Param("id"),
		}
		c.IndentedJSON(http.StatusBadRequest, response)
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

	c.IndentedJSON(http.StatusOK, account)
}

func deleteAccount(c *gin.Context) {
	id, err := strconv.Atoi(c.Param("id"))

	if err != nil {
		c.IndentedJSON(http.StatusBadRequest, gin.H{})
		return
	}

	if id != acc.ID {
		var response = errorResponse{
			Error:   true,
			Message: "There is no account with that ID: " + c.Param("id"),
		}
		c.IndentedJSON(http.StatusBadRequest, response)
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

	pass, _ := bcrypt.GenerateFromPassword([]byte(request.Password), bcrypt.DefaultCost)

	print(string(pass))
	print("\n")
	print(acc.Password)

	if request.Username != acc.Username || bcrypt.CompareHashAndPassword([]byte(acc.Password), []byte(request.Password)) != nil {
		var response = errorResponse{
			Error:   true,
			Message: "Invalid credentials",
		}
		c.IndentedJSON(http.StatusUnauthorized, response)
		return
	}

	var session = session{
		SessionID: "1a2b3c",
		AccountID: 1,
		IP:        "127.0.0.1",
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
