import math
import os
import random
import re
import sys

def jumpingOnClouds(c):
    n = len(c)
    jumps = 0
    current_position = 0

    while current_position < n - 1:
        
        if current_position + 2 < n and c[current_position + 2] == 0:
            current_position += 2
        else:
            current_position += 1

        jumps += 1

    return jumps


n = int(input().strip())

c = list(map(int, input().rstrip().split()))

result = jumpingOnClouds(c)

print(result)

#12
#0 0 0 0 0 0 0 0 0 0 0 0