package main

import "fmt"

type Person struct {
	name string
	age  int

	greet     func()
	introduce func()
}

func main() {
	p := Person{
		name: "Kolgan",
		age:  25,
		//这种做法的坏处是无法进行参数传递
		greet: func() {
			fmt.Println("Greeting from Kolgan")
		},
		introduce: func() {
			fmt.Println("Hello Everyone, My name is Kolgan,and I'm 25")
		},
	}
	p.greet()
	p.introduce()
}
