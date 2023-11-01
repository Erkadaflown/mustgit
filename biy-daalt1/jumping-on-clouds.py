#!/bin/python3

import math
import os
import random
import re
import sys

#
# Complete the 'jumpingOnClouds' function below.
#
# The function is expected to return an INTEGER.
# The function accepts INTEGER_ARRAY c as parameter.
#

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

