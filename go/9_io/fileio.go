package main

import (
	"bufio"
	"fmt"
	"io"
	"io/ioutil"
	"os"
	"strings"
)

func main() {
	osFile("E:\\Git\\PracticeCode\\go\\9_io\\text.txt")
	ioutilRead("E:\\Git\\PracticeCode\\go\\9_io\\text.txt")
}

func osFile(path string) {
	inputFile, err := os.Open(path)
	if err != nil {
		fmt.Println("An Exception occured on opening the file!")
		return
	}
	defer inputFile.Close()

	inputReader := bufio.NewReader(inputFile)
	for {
		inputString, error := inputReader.ReadString('\n')
		strings := strings.Split(inputString, " ")
		fmt.Printf("City:%s,\tCountry:%s", strings[0], strings[1])
		if error == io.EOF {
			return
		}
	}
}

func ioutilRead(path string) {
	buf, err := ioutil.ReadFile(path)
	if err != nil {
		fmt.Fprintf(os.Stderr, "open File Error")
	}
	fmt.Println("value of text :\n", string(buf))
}
