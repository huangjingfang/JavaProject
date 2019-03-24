package util

import (
	"time"
)
//实验结果证明stop并没有被争取赋值
func Timecompare(function func()) (time.Time,time.Time) {
	var start,stop time.Time
	trace(&start)
	defer trace(&stop)
	function()
	return start,stop;
}

func trace(result *time.Time) {
	*result = time.Now();
}