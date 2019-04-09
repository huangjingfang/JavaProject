package main

import (
	"errors"
	"fmt"
)

func main() {
	result, err := divide(6, 0)
	if err != nil {
		fmt.Println("error occured on dividing", err)
	} else {
		fmt.Println("result :", result)
	}

}

func divide(a, b float64) (float64, error) {
	if b == 0 {
		return 0, errors.New("math - divide by zero")
	}
	return a / b, nil
}
