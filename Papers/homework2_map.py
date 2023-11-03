# GROUP 7
# Garipelly Pravalika Goud -
700744024
# Hari Vardhan Reddy Padamati-700745523
# Gangireddy Bhargava Reddy -700741262
import sys

for line in sys.stdin:
    line = line.strip()
    words = line.split()
    for word in words:
        print("%s\t%s" % (word, 1))

