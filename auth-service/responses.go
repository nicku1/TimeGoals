package main

type errorResponse struct {
	Error   bool   `json:"error"`
	Message string `json:"message"`
}

// getAccount
type getAccountSuccess struct {
	Account account
}

// postAccount

type postAccountSuccess struct {
	ID       int    `json:"id"`
	Username string `json:"username"`
	Created  string `json:"created"`
}
