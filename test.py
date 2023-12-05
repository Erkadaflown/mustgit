from collections import deque

def bfs(n, m, edges, s):

    graph = {i: set() for i in range(1, n+1)}
    for u, v in edges:
        graph[u].add(v)
        graph[v].add(u)
    
    distances = [-1] * (n + 1) 
    distances[s] = 0  

    q = deque([s])

    while q:
        current = q.popleft()
        for neighbor in graph[current]:
            if distances[neighbor] == -1: 
                distances[neighbor] = distances[current] + 6 
                q.append(neighbor)

    return distances[1:s] + distances[s+1:]
