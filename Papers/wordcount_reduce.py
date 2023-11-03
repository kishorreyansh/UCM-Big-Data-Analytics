#!/usr/bin/env python3
import sys

last_key, count = None, 0
for line in sys.stdin:
    key, val = line.strip().split('\t')
    if last_key and last_key != key:
        print(f"{last_key}\t{count}")
        last_key, count = key, int(val)
    else:
        last_key, count = key, count + int(val)

if last_key:
    print(f"{last_key}\t{count}")