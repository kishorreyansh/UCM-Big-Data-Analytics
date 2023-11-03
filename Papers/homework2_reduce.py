# GROUP 7
# Garipelly Pravalika Goud -
700744024
# Hari Vardhan Reddy Padamati-700745523
# Gangireddy Bhargava Reddy -700741262


#!/usr/bin/env python
  
from operator import itemgetter
import sys
  
current_word = None
current_count = 0
word = None
  
# read the entire line from STDIN
for line in sys.stdin:
    # eliminate the leading and trailing whitespaces.
    line = line.strip()
    # Data splitting based on the tab we've specified in mapper.py
    word, count = line.split('\t', 1)
    # change count from a string to an int.
    try:
        count = int(count)
    except ValueError:
        # count was not a number, so silently
        # ignore/discard this line
        continue
  
    # this IF-switch only works because Hadoop sorts map output
    # by key (here: word) before it is passed to the reducer
    if current_word == word:
        current_count += count
    else:
        if current_word:
            # write result to STDOUT
            opt = current_word + " " + str(current_count)
            print(opt)
        current_count = count
        current_word = word
  
# do not forget to output the last word if needed!
if current_word == word:
    opt = current_word + " " + str(current_count)
    print(opt)
