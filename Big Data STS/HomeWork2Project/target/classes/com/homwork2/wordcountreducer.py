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

present_word = None
present_count = 0

for line in sys.stdin:
    line = line.strip()
    word, count = line.split('\t', 1)
    
    count = int(count)  
    
    if present_word == word:
        present_count += count
    else:
        if present_word:
            print("%s\t%s" % (present_word, present_count))
        present_word = word
        present_count = count
        
if present_word == word:
    print("%s\t%s" % (present_word, present_count))