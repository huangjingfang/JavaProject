package main

import (
	"os"
)

func main() {
	type inter interface{}

	user := os.Getenv("USER")
	if user == "" {
		panic("Unknown user: no value for $USER")
	}
}
