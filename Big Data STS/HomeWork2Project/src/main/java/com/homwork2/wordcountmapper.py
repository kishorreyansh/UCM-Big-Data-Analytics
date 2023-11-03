"""
/*
Group 10
------------------------------------------
Kishor Kumar Andekar - 700744713
Venkata Suraj Gamini - 700744962
------------------------------------------
*/
"""
#!/usr/bin/env python

import sys

for line in sys.stdin:
    line = line.strip()
    words = line.split()
    
    for word in words:
        print("%s\t%s" % (word, 1))