package main

import (
	"fmt"
	"math"
	"reflect"
)

type Point struct {
	x, y int
}

type Rectangle struct {
	p1, p2 Point
}

func (s *Rectangle) Area() int {
	return s.xLength() * s.yLength()
}

func (s *Rectangle) xLength() int {
	return int(math.Abs(float64(s.p2.x - s.p1.x)))
}
func (s *Rectangle) yLength() int {
	return int(math.Abs(float64(s.p2.y - s.p1.y)))
}

func main() {
	s := Rectangle{
		p1: Point{
			x: 1,
			y: 1,
		},
		p2: Point{
			x: 6,
			y: 6,
		},
	}

	t, v := reflect.TypeOf(s), reflect.ValueOf(s)

	fmt.Println(t, v)

	for i := 0; i < v.NumField(); i++ {
		fmt.Printf("field %d :%v", i, v.Field(i))
	}

	for i := 0; i < v.NumMethod(); i++ {
		fmt.Printf(" method %d:%v", i, v.Method(i))
	}
}
