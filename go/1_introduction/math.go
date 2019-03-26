package main

import(
	"fmt"
	"math"
	"math/cmplx"
)

func main() {
	fmt.Println(cmplx.Exp(complex(0,math.Pi))+1)
	fmt.Println(math.Pi)
}