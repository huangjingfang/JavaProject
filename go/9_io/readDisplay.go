package main

import (
	"bufio"
	"fmt"
	"os"
)

var p = new(Person)

type Person struct {
	firstName string
	lastName  string
}

func main() {
	read()
	readStdIn()
}

/**
read by fmt.Scan()
*/
func read() {
	fmt.Println("Please enter your full name:")
	fmt.Scanln(&p.firstName, &p.lastName)
	fmt.Printf("Hello %s \t %s\n", p.firstName, p.lastName)
}

func readStdIn() {
	inputReader := bufio.NewReader(os.Stdin)
	fmt.Println("Please enter an input:")
	input, err := inputReader.ReadString('\n')
	if err == nil {
		fmt.Println("The input was: %s \n", input)
	}
}
