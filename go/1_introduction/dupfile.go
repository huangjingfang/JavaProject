package main

import (
	"fmt"
	"os"
	"bufio"
)

func main() {
	count_map := make(map[string]int)

	files := os.Args[1:]
	if len(files) == 0{
		countLine(os.Stdin,count_map);
	}else{
		for _,arg := range files{
			f,err := os.Open(arg)
			if err != nil{
				fmt.Fprintf(os.Stderr,"dupFile: %v\n",err)
				continue
			}
			countLine(f,count_map)
			f.Close()
		}
	}

	for key,value := range count_map{
		fmt.Printf("Key: %s,value: %d \n",key,value)
	}
}

func countLine(f *os.File,counts map[string]int) {
	input := bufio.NewScanner(f)
	for input.Scan(){
		counts[input.Text()]++
	}
}