package main

import (
	"fmt"
	"math"
)

type Point struct {
	x int
	y int
}

func main() {
	o := newPoint(0,0)
	p := newPoint(3,4)
	fmt.Println(p.polar())
	fmt.Println(p.distance(o))
}

func newPoint(x,y int) *Point {
	return &Point{x,y}
}

func (p *Point) distance(p2 *Point) float64 {
	return math.Sqrt(math.Pow(math.Abs(float64(p2.x-p.x)),2)+math.Pow(math.Abs(float64(p2.y-p.y)),2));
}

func (p *Point) polar()(float64,float64){
	return math.Sqrt(math.Pow(float64(p.x),2)+math.Pow(float64(p.y),2)),math.Atan(float64(p.y/p.x))
}