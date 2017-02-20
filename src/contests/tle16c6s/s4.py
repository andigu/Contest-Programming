import copy

import sys


def calculate_least_adjacent_cost(adjacency_list, i, v, cache):
    adjacent_nodes = adjacency_list[v]

    least_adjacent_cost = float("inf")
    for node in adjacent_nodes:
        adjacent_cost = cache[node["from"] - 1] + node["weight"]
        if adjacent_cost < least_adjacent_cost:
            least_adjacent_cost = adjacent_cost
    return least_adjacent_cost


def bellman_ford(vertices, edges):
    s = 0
    cache = [[] for j in range(vertices + 1)]
    cache[s] = 0

    for v in range(0, vertices):
        if v != s:
            cache[v] = float("inf")

    for i in range(1, vertices):
        for v in range(0, vertices):
            previous_cache = cache
            least_adjacent_cost = calculate_least_adjacent_cost(adjacency_list, i, v, previous_cache)
            cache[v] = min(previous_cache[v], least_adjacent_cost)

    # detecting negative cycles
    for v in range(0, vertices):
        previous_cache = copy.deepcopy(cache)
        least_adjacent_cost = calculate_least_adjacent_cost(adjacency_list, i, v, previous_cache)
        cache[v] = min(previous_cache[v], least_adjacent_cost)

    return cache

input = sys.stdin.readline
N, M, D = [int(i) for i in input().split()]

adjacency_list = [[] for k in range(N)]
for i in range(M):
    tail, head, weight = input().split(" ")
    adjacency_list[int(head) - 1].append({"from": int(tail), "weight": int(weight)})

for i in range(D):
    data = [int(i) for i in input().split()]
    for vertex in adjacency_list:
        for edge in vertex:
            edge["weight"] += data[0]
    print(bellman_ford(N, M)[data[1] - 1])
