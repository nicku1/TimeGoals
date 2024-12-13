package main

type account struct {
	ID       int     `json:"id"`
	Username string  `json:"username"`
	Password string  `json:"password"`
	Email    string  `json:"email"`
	Created  string  `json:"created"`
	Updated  string  `json:"updated"`
	Suspend  suspend `json:"suspend"`
}

type accountPost struct {
	Username string `json:"username"`
	Password string `json:"password"`
	Email    string `json:"email"`
}

type accountPut struct {
	Username string  `json:"u2sername"`
	Password string  `json:"password"`
	Email    string  `json:"email"`
	Suspend  suspend `json:"suspend"`
}

type accountLogin struct {
	Username string `json:"username"`
	Password string `json:"password"`
}

type suspend struct {
	Active bool   `json:"active"`
	Reason string `json:"reason"`
}

type session struct {
	SessionID string `json:"session_id"`
	AccountID int    `json:"account_id"`
	IP        string `json:"ip"`
	ValidTo   string `json:"valid_to"`
}

type sessionPost struct {
	AccountID int    `json:"account_id"`
	IP        string `json:"ip"`
}

type audit struct {
	ID        int    `json:"id"`
	AccountID int    `json:"account_id"`
	Date      string `json:"date"`
	ValueType int    `json:"value_type"`
	OldValue  int    `json:"old_value_type"`
	NewValue  int    `json:"new_value_type"`
}
