#!/usr/bin/env python3
import re, sys

for line in sys.stdin:
    val = line.strip().lower()
    words = re.split("\W+", val)
    for word in words:
    	if len(word) != 0:
        	print(f"{word}\t{1}")