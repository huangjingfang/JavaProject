package main

import (
	"fmt"
	"os"
	"bufio"
)

func main() {
	count_map := make(map[string]int)
	input := bufio.NewScanner(os.Stdin)
	//Ctrl C to stop input
	for input.Scan(){
		count_map[input.Text()]++
	}

	for key,value := range count_map{
		fmt.Printf("Key: %s,value: %d \n",key,value)
	}
}